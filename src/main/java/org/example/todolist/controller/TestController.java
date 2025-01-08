package org.example.todolist.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test-possible-deployment")
public class TestController {

    @GetMapping("/is-deployed")
    public ResponseEntity<String> testSecureEndpoint() {
        String htmlContent = """
                <html>
                <body style="text-align:center; margin-top: 50px;">
                    <h1 style="color: green; font-weight: bold;">Possible Deployment!</h1>
                </body>
                </html>
                """;

        // Cau hinh headers de trinh duyet hieu day laf html
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);

        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
    }
}
