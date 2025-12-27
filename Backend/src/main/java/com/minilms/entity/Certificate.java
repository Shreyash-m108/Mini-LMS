package com.minilms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "certificates",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id","course_id"})
    }
)
@Getter
@Setter
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id",nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "course_id",nullable = false)
    private Course course;
    private LocalDateTime issuedAt = LocalDateTime.now();
}
