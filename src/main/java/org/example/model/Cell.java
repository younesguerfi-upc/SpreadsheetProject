

package org.example.model;

public class Cell {

    private String content;

    public Cell() {
        this.content = "";
    }

    public void setContent(String content) {
        this.content = content == null ? "" : content;
    }

    public String getContent() {
        return content;
    }

    public boolean isEmpty() {
        return content.isEmpty();
    }
}

