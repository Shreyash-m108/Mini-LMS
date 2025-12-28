package com.minilms.dto.courseDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AssignCourseRequest {
    private Long courseId;
    private Long mentorId;
    List<Long> studentIds;
}
