package com.example.cloudDiplom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity(name = "files")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileEnt {

    @Id
    @Column(unique = true)
    private String fileName;

    @Column(nullable = false)
    private LocalDateTime date;
    @Lob
    private byte[] file;
    private Long size;

    @ManyToOne
    private User user;
}
