package com.github.rusakovichma.tictaac.validation;

import com.github.rusakovichma.tictaac.mapper.ThreatModelMapper;
import com.github.rusakovichma.tictaac.model.ThreatModel;
import com.github.rusakovichma.tictaac.parser.impl.NodeTreeParser;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;
import com.github.rusakovichma.tictaac.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorImplTest {

    @Test
    void validateNotPassed() throws Exception {
        InputStream fileInputStream = FileUtil.fileToInputStream("src/test/resources/threat-model-validation-test.yml");

        NodeTree tree = new NodeTreeParser().getNodeTree(fileInputStream);
        ThreatModelMapper mapper = new ThreatModelMapper(tree);

        ThreatModel threatModel = mapper.getModel();

        Exception exception = assertThrows(ValidationException.class, () -> {
            Validator validator = new ValidatorImpl();
            validator.validate(threatModel);
        });
    }

    @Test
    void validatePassed() throws Exception {
        InputStream fileInputStream = FileUtil.fileToInputStream("src/test/resources/threat-model-test.yml");

        NodeTree tree = new NodeTreeParser().getNodeTree(fileInputStream);
        ThreatModelMapper mapper = new ThreatModelMapper(tree);

        ThreatModel threatModel = mapper.getModel();

        Validator validator = new ValidatorImpl();
        validator.validate(threatModel);
    }

    @Test
    void validateMinimized() throws Exception {
        InputStream fileInputStream = FileUtil.fileToInputStream("src/test/resources/threat-model-minimized-validation-test.yml");

        NodeTree tree = new NodeTreeParser().getNodeTree(fileInputStream);
        ThreatModelMapper mapper = new ThreatModelMapper(tree);

        ThreatModel threatModel = mapper.getModel();

        Validator validator = new ValidatorImpl();
        validator.validate(threatModel);
    }
}