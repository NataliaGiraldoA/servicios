package co.edu.usbcali.records.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordRequestDTO {
    private String title;
    private String artist;
    private String genre;
    private Integer releaseYear;
    private Float price;
    private Integer stockQuantity;
    private String description;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer categoryId;
}
