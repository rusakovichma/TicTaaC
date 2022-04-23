package com.github.rusakovichma.tictaac.correction;

import com.github.rusakovichma.tictaac.model.threatmodel.Boundary;
import com.github.rusakovichma.tictaac.model.threatmodel.boundary.BoundaryCategory;

import java.util.Arrays;
import java.util.List;

public class BoundaryGuesser implements Guesser<Boundary> {

    private final static List<String> GLOBAL_BOUNDARY_SIGNS = Arrays.asList(new String[]{
            "internet", "global", "external"});

    private final static List<String> DMZ_BOUNDARY_SIGNS = Arrays.asList(new String[]{
            "dmz", "demilitarized"});

    private final static List<String> CORPORATE_BOUNDARY_SIGNS = Arrays.asList(new String[]{
            "company", "corp", "corporate", "internal", "wlan"});

    private final static List<String> CLOSED_BOUNDARY_SIGNS = Arrays.asList(new String[]{
            "closed", "vlan", "vpc", "sub-net", "subnet"});

    private boolean findSign(List<String> signs, Boundary boundary) {
        for (String sign : signs) {
            if (boundary.getId().toLowerCase().contains(sign.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean tryToCorrect(Boundary boundary) {
        if (boundary.getCategory() == null || boundary.getId() == null) {
            return false;
        }

        if (findSign(GLOBAL_BOUNDARY_SIGNS, boundary)) {
            boundary.setCategory(BoundaryCategory.globalNetwork);
            return true;
        }

        if (findSign(DMZ_BOUNDARY_SIGNS, boundary)) {
            boundary.setCategory(BoundaryCategory.demilitarizedZone);
            return true;
        }

        if (findSign(CORPORATE_BOUNDARY_SIGNS, boundary)) {
            boundary.setCategory(BoundaryCategory.corporateNetwork);
            return true;
        }

        if (findSign(CLOSED_BOUNDARY_SIGNS, boundary)) {
            boundary.setCategory(BoundaryCategory.closedPerimeter);
            return true;
        }


        boundary.setCategory(BoundaryCategory.undefined);
        return false;
    }
}
