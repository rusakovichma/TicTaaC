package com.github.rusakovichma.tictaac.model.mitigation;

import com.github.rusakovichma.tictaac.model.threatmodel.annotation.Id;

public class ThreatRef {

    @Id
    private String id;
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
