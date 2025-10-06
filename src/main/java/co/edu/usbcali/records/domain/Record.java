package co.edu.usbcali.records.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "records")

public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer recordId;
    @Column(name = "title", length = 255, unique = false)
    private String title;
    @Column(name = "artist", length = 255, unique = false)
    private String artist;
    @Column(name = "genre", length = 255, unique = false)
    private String genre;
    @Column(name = "release_year", length = 255, unique = false)
    private int releaseYear;
    @Column(name = "price", nullable = false)
    private float price;
    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;
    @Column(name = "description", length = 255, unique = false)
    private String description;
    @Column(name = "cover_image_url",length = 255, unique = false)
    private String image;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;
}
