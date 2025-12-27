package com.minilms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "progress",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id","chapter_id"})
    }
)
@Getter
@Setter
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id",nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "chapter_id",nullable = false)
    private Chapter chapter;

    private Boolean completed = false;
    private LocalDateTime completedAt;

}
