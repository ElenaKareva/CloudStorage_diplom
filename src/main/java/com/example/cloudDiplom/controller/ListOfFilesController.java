package com.example.cloudDiplom.controller;


import com.example.cloudDiplom.dto.response.FileListResponseDto;
import com.example.cloudDiplom.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/list")
@RequiredArgsConstructor
public class ListOfFilesController {
    private final FileService fileService;

    @GetMapping
    public ResponseEntity<List<FileListResponseDto>> getList(@RequestHeader("auth-token") String authToken, @RequestParam Integer limit) {

        List<FileListResponseDto> dtos = fileService.getList(authToken, limit);

        return ResponseEntity.ok().body(dtos);
    }

}
