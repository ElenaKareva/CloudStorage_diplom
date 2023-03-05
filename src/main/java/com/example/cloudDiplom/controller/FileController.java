package com.example.cloudDiplom.controller;

import com.example.cloudDiplom.dto.request.FileDto;
import com.example.cloudDiplom.dto.request.FileNameUpdateDto;
import com.example.cloudDiplom.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController()
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping()
    public ResponseEntity<Resource> getFile(@RequestHeader("auth-token") String authToken, @RequestParam String fileName) {
        byte[] file = fileService.getFile(fileName);
        return ResponseEntity.ok().body(new ByteArrayResource(file));
    }

    @PostMapping(
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity uploadFile(@RequestHeader("auth-token") String authToken, @RequestParam String fileName, FileDto file) {
        fileService.save(authToken, fileName, file);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateFile(@RequestHeader("auth-token") String authToken,
                                     @RequestParam String fileName, @RequestBody FileNameUpdateDto dto) {
        fileService.updateFileName(fileName, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteFile(@RequestHeader("auth-token") String authToken, @RequestParam @NotNull String fileName) {
        fileService.deleteFile(fileName);
        return ResponseEntity.ok().build();
    }
}