package com.snack.news.domain;

public class Corporation {
    private Long id;
    private String name;
    private String imageLink;

    public void setId (Long id) { this.id = id; }
    public void setName (String name) { this.name = name; }
    public void setImageLink (String imageLink) { this.imageLink = imageLink; }

    public Long getId () { return this.id; }
    public String getName () { return this.name; }
    public String getImageLink () { return this.imageLink; }
}
