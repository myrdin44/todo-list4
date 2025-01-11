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
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>Possible Deployment</title>
                        <style>
                            body {
                                background-color: #f0f8ff;
                                font-family: 'Arial', sans-serif;
                                margin: 0;
                                padding: 0;
                            }
                
                            h1 {
                                color: #4CAF50; /* Green */
                                font-weight: bold;
                                font-size: 48px;
                                text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.2);
                                margin-top: 100px;
                                animation: fadeIn 2s ease-out;
                            }
                
                            @keyframes fadeIn {
                                0% {
                                    opacity: 0;
                                    transform: translateY(-20px);
                                }
                                100% {
                                    opacity: 1;
                                    transform: translateY(0);
                                }
                            }
                        </style>
                    </head>
                    <body>
                        <h1>Possible Deployment!</h1>
                    </body>
                </html>
                """;

        // Cau hinh headers de trinh duyet hieu day laf html
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);

        return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
    }
}
