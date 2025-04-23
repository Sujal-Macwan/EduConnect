package com.example.EduConnect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long id;

    @NotBlank(message = "Title required")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @NotBlank(message = "Description required")
    private String description;

    @NotBlank(message = "Category required")
    private String category;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive number")
    private double price;
}
