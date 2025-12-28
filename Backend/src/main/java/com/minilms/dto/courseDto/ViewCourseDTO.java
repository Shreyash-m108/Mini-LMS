package com.minilms.dto.courseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.minilms.entity.Chapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewCourseDTO {
    private String title;
    private String description;
    private String mentorName;
    private int studentCount;
    private List<Chapter> chapters;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd:MM:yyyy")
    private LocalDateTime createdAt;
}
