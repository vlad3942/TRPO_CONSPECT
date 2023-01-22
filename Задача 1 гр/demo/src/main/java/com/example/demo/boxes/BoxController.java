package com.example.demo.boxes;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class BoxController {

    private final BoxService boxService;

    @GetMapping("/count")
    public CountBoxesDto count() {
        return new CountBoxesDto(boxService.maxHeight());
    }
}
