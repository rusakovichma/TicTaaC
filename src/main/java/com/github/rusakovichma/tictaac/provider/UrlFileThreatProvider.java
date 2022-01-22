package com.github.rusakovichma.tictaac.provider;

import com.github.rusakovichma.tictaac.mapper.ThreatsLibraryMapper;
import com.github.rusakovichma.tictaac.model.ThreatsLibrary;
import com.github.rusakovichma.tictaac.parser.impl.NodeTreeParser;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

public class UrlFileThreatProvider implements ThreatProvider {

    private final String fileUrl;
    private String username;
    private char[] password;

    public UrlFileThreatProvider(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public ThreatsLibrary getThreatsLibrary() {
        try {
            URL fileURL = new URL(fileUrl);
            
            InputStream inputStream = null;
            if (username != null && password != null) {
                final String auth = String.format("%s:%s", username, String.valueOf(password));

                URLConnection uc = fileURL.openConnection();
                uc.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString(auth.getBytes()));

                inputStream = uc.getInputStream();
            } else {
                inputStream = fileURL.openStream();
            }

            NodeTree tree = new NodeTreeParser().getNodeTree(inputStream);
            return new ThreatsLibraryMapper(tree).getModel();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Cannot init URL file provider [" + fileUrl + "]", ex);
        } finally {
            //clear the password after usage
            clearPasswordIfNeeded();
        }
    }

    private void clearPasswordIfNeeded() {
        if (password != null) {
            for (int i = 0; i < password.length; i++) {
                password[i] = '\0';
            }
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}
