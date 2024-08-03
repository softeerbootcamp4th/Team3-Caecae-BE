package ai.softeer.caecae.global.controller;

import ai.softeer.caecae.global.dto.response.SuccessResponse;
import ai.softeer.caecae.global.enums.SuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthTestController {
    @GetMapping("/api/health")
    public ResponseEntity<SuccessResponse<String>> healthTest() {
        return SuccessResponse.of(SuccessCode.OK, "health test v1");

    }

}

