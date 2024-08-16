package org.aloyolaa.springcloud.msvc.courses.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.aloyolaa.springcloud.msvc.courses.validator.annotation.UniqueNameDB;

@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @UniqueNameDB
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}