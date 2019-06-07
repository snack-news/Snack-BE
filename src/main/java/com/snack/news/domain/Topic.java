package com.snack.news.domain;

import lombok.Builder;

import javax.persistence.*;

/**
 * @author delf
 */
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String image;

    @Enumerated(EnumType.STRING)
    private TopicType type;

    @Builder
    public Topic(Long id, String name, String image, TopicType type) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.type = type;
    }
}
