package com.example.demo.boxes;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class BoxController {

    private final BoxService boxService;

    @GetMapping("/count")
    public CountBoxesDto count() {
        return new CountBoxesDto(boxService.maxHeight());
    }

    @GetMapping("/max-pyramid")
    public List<Box> maxPyramid() {
        return boxService.findMaxHeightPyramid();
    }

    @GetMapping("/max-pyramid-opt")
    public List<Box> maxPyramidOpt() {
        return boxService.findMaxHeightPyramidOpt();
    }
}
