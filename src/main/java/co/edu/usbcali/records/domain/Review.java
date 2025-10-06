package co.edu.usbcali.records.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private Integer reviewId;
    private int rating;
    private String review;
    private Date createdAt;
}
