package com.example.cloudDiplom.service;

import com.example.cloudDiplom.dto.request.FileNameUpdateDto;
import com.example.cloudDiplom.entity.FileEnt;
import com.example.cloudDiplom.excteption.AppException;
import com.example.cloudDiplom.repository.FileRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class FileServiceTest {

    @InjectMocks
    FileService fileService;
    @Mock
    FileRepo fileRepo;

    @Test
    void getFileWithoutProblem() {
        String fileName = "TEST";
        byte[] bytes = "test".getBytes();
        FileEnt ent = new FileEnt("TEST", LocalDateTime.now(), bytes, 1L, null);
        when(fileRepo.findByFileName(fileName)).thenReturn(Optional.of(ent));

        byte[] file = fileService.getFile(fileName);

        verify(fileRepo, times(1)).findByFileName(fileName);
        assertEquals(bytes, file);
    }

    @Test
    void getFileWithException() {
        String fileName = "TEST";
        when(fileRepo.findByFileName(fileName)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> fileService.getFile(fileName)).isInstanceOf(AppException.class);
        verify(fileRepo, times(1)).findByFileName(fileName);
    }

    /* TODO @Test
    void deleteFile() {
        String fileName = "TEST";
        fileService.deleteFile(fileName);
        verify(fileRepo, times(1)).deleteByFileName(fileName);
    }

    @Test
    void updateFilename() {
        String fileName = "TEST";
        String newName = "NEW";
        byte[] bytes = "test".getBytes();
        FileEnt ent = new FileEnt("TEST", LocalDateTime.now(), bytes, 1L, null);
        when(fileRepo.updateFileName(fileName, newName)).thenReturn(Optional.of(ent));

        Optional<FileEnt> fileEnt = fileService.updateFileName(fileName, new FileNameUpdateDto(newName));

        verify(fileRepo, times(1)).updateFileName(fileName, newName);
        assertEquals(ent, fileEnt.get());
    }



    @Test
    void updateFilenameWithExc() {
        String fileName = "TEST";
        String newName = "NEW";
        when(fileRepo.updateFileName(fileName, newName)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> fileService.updateFileName(fileName, new FileNameUpdateDto(newName))).isInstanceOf(AppException.class);
        verify(fileRepo, times(1)).updateFileName(fileName, newName);
    }

*/
    @Test
    void getListShouldThrowException() {
        int limit = 3;
        assertThatThrownBy(() -> fileService.getList("token", limit)).isInstanceOf(AppException.class);
    }
}
