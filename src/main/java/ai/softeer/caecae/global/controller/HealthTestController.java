package ai.softeer.caecae.global.controller;

import ai.softeer.caecae.global.dto.response.SuccessResponse;
import ai.softeer.caecae.global.enums.SuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/health")
public class HealthTestController {
    @GetMapping("")
    public ResponseEntity<SuccessResponse<String>> healthTest() {
        return SuccessResponse.of(SuccessCode.OK, "health test v1");

    }

}

