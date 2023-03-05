package com.example.cloudDiplom.service;


import com.example.cloudDiplom.dto.request.FileDto;
import com.example.cloudDiplom.dto.request.FileNameUpdateDto;
import com.example.cloudDiplom.dto.response.FileListResponseDto;
import com.example.cloudDiplom.entity.FileEnt;
import com.example.cloudDiplom.entity.User;
import com.example.cloudDiplom.excteption.AppException;
import com.example.cloudDiplom.repository.AuthenticationRepo;
import com.example.cloudDiplom.repository.FileRepo;
import com.example.cloudDiplom.repository.UsersRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {
    private final FileRepo fileRepo;
    private final UsersRepo userRepo;
    private final AuthenticationRepo authenticationRepo;


    public boolean save(String authToken,String fileName, FileDto dto) {
        MultipartFile file = dto.getFile();
        final User user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Загрузка файла: Нет разрешения");
            throw new AppException("Загрузка файла: Нет разрешения");
        }

        try {
            fileRepo.save(new FileEnt(fileName,
                    LocalDateTime.now(),
                    file.getBytes(),
                    file.getSize(),
                    user));
            log.info("Файл загружен. Пользователь {}", user.getUsername());
            return true;
        } catch (IOException e) {
            log.error("Загрузка файла: Введены некорректные данные");
            throw new AppException("Загрузка файла : Введены некорректные данные");
        }
    }

    public byte[] getFile(String fileName) {
        Optional<FileEnt> entity = fileRepo.findByFileName(fileName);
        if (entity.isPresent()) {
            FileEnt fileEnt = entity.get();
            return fileEnt.getFile();
        } else {
            throw new AppException("Файл с таким названием отсутствует");
        }
    }
    /*
    @Transactional
    public void deleteFile(String fileName) {
        fileRepo.deleteByFileName(fileName);
    }

    @Transactional
    public Optional<FileEnt> updateFileName(String fileName, FileNameUpdateDto dto) {
        Optional<FileEnt> fileEnt = fileRepo.updateFileName(fileName, dto.getFileName());
        if (fileEnt.isPresent()) {
            return fileEnt;
        } else {
            throw new AppException("Файл с таким названием отсутствует");
        }

    }

     */

    public List<FileListResponseDto> getList(String authToken, Integer limit) {
        final User user = getUserByAuthToken(authToken);
        if (user == null) {
            log.error("Вывести все файлы: Нет разрешения");
            throw new AppException("Вывести все файлы: Нет разрешения");
        }
        log.info("Success get all files. User {}", user.getUsername());
        return fileRepo.findAllByUser(user).stream()
                .map(o -> new FileListResponseDto(o.getFileName(), o.getSize()))
                .collect(Collectors.toList());
    }

    private List<FileListResponseDto> convertToDto(Page<FileEnt> entityPage) {
        return entityPage.stream()
                .map(o -> new FileListResponseDto(o.getFileName(), o.getSize()))
                .collect(Collectors.toList());
    }

    private User getUserByAuthToken(String authToken) {
        if (authToken.startsWith("Заявитель ")) {
            final String authTokenWithoutBearer = authToken.split(" ")[1];
            final String username = authenticationRepo.getUserNameByToken(authTokenWithoutBearer);
            return userRepo.findByUserName(username);
        }
        return null;
    }
}
