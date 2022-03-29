package com.github.rusakovichma.tictaac.util;

import com.github.rusakovichma.tictaac.model.threatmodel.Asset;
import com.github.rusakovichma.tictaac.model.threatmodel.asset.AssetSensitivity;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionUtilTest {

    @Test
    void getFields() {
        Asset asset1 = new Asset();
        asset1.setId("asset 1");
        asset1.setSensitivity(AssetSensitivity.sensitive);

        Map<String, Object> fields = ReflectionUtil.getFields(asset1, "asset");
        assertTrue(fields.size() == 3);
    }
}