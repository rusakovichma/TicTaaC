package com.github.rusakovichma.tictaac.provider.mitigation;

import com.github.rusakovichma.tictaac.model.mitigation.MitigationsLibrary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StandardMitigationProviderTest {

    @Test
    void getMitigations() {
        String path = "src/test/resources/mitigations-for-tests.yml";

        StandardMitigationProvider provider = new StandardMitigationProvider(path);
        MitigationsLibrary library = provider.getMitigations();
        assertTrue(library != null);

        assertTrue(library.getMitigated().size() == 3);
        assertTrue(library.getAvoided().size() == 2);

        assertTrue(library.getMitigated().get(0).getComment().equals("already mitigated"));
    }
}