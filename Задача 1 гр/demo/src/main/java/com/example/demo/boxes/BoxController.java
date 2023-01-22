package com.example.demo.boxes;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoxController {

    private final BoxService boxService;

    @GetMapping("/count")
    public CountBoxesDto count() {
        return new CountBoxesDto(boxService.countHeight());
    }
}
