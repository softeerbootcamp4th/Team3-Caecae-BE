package ai.softeer.caecae.global.controller;

import ai.softeer.caecae.global.dto.response.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/health")
public class HealthTestController {
    @GetMapping("")
    public ResponseEntity<SuccessResponse<String>> healthTest() {
        return ResponseEntity.ok(new SuccessResponse<>(1000,"api health test에 성공했습니다.","health test v1"));
    }

}

