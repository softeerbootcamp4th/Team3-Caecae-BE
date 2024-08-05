package ai.softeer.caecae.global.controller;

import ai.softeer.caecae.global.dto.response.SuccessResponse;
import ai.softeer.caecae.global.enums.SuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HealthTestController {
    @GetMapping("/api/health")
    public ResponseEntity<SuccessResponse<String>> healthTest() {
        return SuccessResponse.of(SuccessCode.OK, "Caecae Spring Server Health Test ~! with cors");
    }
}

