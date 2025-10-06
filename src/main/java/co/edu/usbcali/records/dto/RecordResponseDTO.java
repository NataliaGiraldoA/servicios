package co.edu.usbcali.records.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class RecordResponseDTO {
    private Integer recordId;
    private String title;
    private String artist;
    private String genre;
    private Integer releaseYear;
    private Float price;
    private Integer stockQuantity;
    private String description;
    private String image;
    private Date createdAt;
    private Date updatedAt;
    private String categoryName;
    private Integer categoryId;
}
