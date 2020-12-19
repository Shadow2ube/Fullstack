package ca.unmined.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RequestManager {

    @GetMapping("/instructors/{username}/courses")
    public String getAllCourses(@PathVariable String username) {
        return "";
    }
}
