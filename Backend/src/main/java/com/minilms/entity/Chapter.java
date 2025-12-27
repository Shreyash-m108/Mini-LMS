package com.minilms.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "chapters")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "course_id",nullable = false)
    @JsonBackReference
    private Course course;

    @Column(columnDefinition = "TEXT")
    private String description;


    private String imageUrl;
    private String videoUrl;
    @Column(nullable = false)
    private Integer sequenceOrder;
}
