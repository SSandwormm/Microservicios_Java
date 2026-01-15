package com.microservice.course.client;

import com.microservice.course.dto.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-student")
public interface StudentClient {

    @GetMapping("/api/student/search-by-course/{idCourse}")
    List<StudentDTO> findAllStudentByCourse(@PathVariable("idCourse") Long idCourse);
}
