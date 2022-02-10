package com.github.rusakovichma.tictaac.engine.el.parser;

import com.github.rusakovichma.tictaac.engine.el.expression.Expression;
import com.github.rusakovichma.tictaac.model.threatmodel.Asset;
import com.github.rusakovichma.tictaac.model.threatmodel.asset.AssetSensitivity;
import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DefaultExpressionParserTest {

    private SimpleExternalContext externalContext = new SimpleExternalContext();

    @AfterEach
    void teardown() {
        externalContext.clear();
    }

    @Test
    public void testParseSingleExression() throws Exception {
        externalContext.addParameter("source.type", "proxy-server");
        externalContext.addParameter("target.type", "database");

        String expressionToParse = "source.type ==database";
        ExpressionParser parser = new DefaultExpressionParser(externalContext);
        Expression<Boolean> expression = parser.parse(expressionToParse);

        assertFalse(expression.getEvaluationResult());
    }

    @Test
    public void testParseStrings() throws Exception {
        externalContext.addParameter("source.type", "database");
        externalContext.addParameter("target.type", "proxy");

        String expressionToParse = "( source.type ==database) or (target.type==database)";
        ExpressionParser parser = new DefaultExpressionParser(externalContext);
        Expression<Boolean> expression = parser.parse(expressionToParse);

        assertTrue(expression.getEvaluationResult());
    }

    @Test
    public void testParseEnums() throws Exception {
        externalContext.addParameter("source.type", ElementType.proxyServer);
        externalContext.addParameter("target.type", ElementType.database);

        String expressionToParse = "(source.type==proxy-server ) or (target.type == proxy-server)";
        ExpressionParser parser = new DefaultExpressionParser(externalContext);
        Expression<Boolean> expression = parser.parse(expressionToParse);

        assertTrue(expression.getEvaluationResult());
    }

    @Test
    public void testParseBigStrings() throws Exception {
        externalContext.addParameter("source.name", "source difficult name");
        externalContext.addParameter("target.name", "target name name name xxx");

        String expressionToParse = "(source.name=='asdfas sfasdfasd asdf' ) or (target.name == 'target name name name xxx')";
        ExpressionParser parser = new DefaultExpressionParser(externalContext);
        Expression<Boolean> expression = parser.parse(expressionToParse);

        assertTrue(expression.getEvaluationResult());
    }


    @Test
    public void testParseContain() throws Exception {
        LinkedList<Asset> transferredAssets = new LinkedList<Asset>();

        Asset asset1 = new Asset();
        asset1.setId("asset 1");
        asset1.setSensitivity(AssetSensitivity.sensitive);
        transferredAssets.add(asset1);

        Asset asset2 = new Asset();
        asset2.setId("asset 2");
        asset2.setSensitivity(AssetSensitivity.nonSensitive);
        transferredAssets.add(asset2);

        externalContext.addParameter("flow.transferredAssets", transferredAssets);

        String expressionToParse = "flow.transferredAssets contain non-sensitive";
        ExpressionParser parser = new DefaultExpressionParser(externalContext);
        Expression<Boolean> expression = parser.parse(expressionToParse);

        assertTrue(expression.getEvaluationResult());
    }

    @Test
    public void testParseContain2() throws Exception {
        LinkedList<Asset> transferredAssets = new LinkedList<Asset>();

        Asset asset1 = new Asset();
        asset1.setId("asset 1");
        asset1.setSensitivity(AssetSensitivity.sensitive);
        transferredAssets.add(asset1);

        Asset asset2 = new Asset();
        asset2.setId("asset 2");
        asset2.setSensitivity(AssetSensitivity.sensitive);
        transferredAssets.add(asset2);

        externalContext.addParameter("flow.transferredAssets", transferredAssets);
        externalContext.addParameter("source.name", "sourceX");

        String expressionToParse = "(flow.transferredAssets contain sensitive) and (source.name=='sourceX')";
        ExpressionParser parser = new DefaultExpressionParser(externalContext);
        Expression<Boolean> expression = parser.parse(expressionToParse);

        assertTrue(expression.getEvaluationResult());
    }
}