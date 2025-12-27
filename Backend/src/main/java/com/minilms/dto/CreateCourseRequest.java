package com.minilms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCourseRequest {
    private String title;
    private String description;
    private Long mentorId;

}
