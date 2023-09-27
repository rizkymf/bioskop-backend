package org.binaracademy.bioskopbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie") // OPTIONAL, bisa digunakan untuk memberikan nama table yg berbeda nama class
public class Movie {

    @Id
    private String id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "image_url")
    private String image;

    @Column(name = "schedule")
    private Date schedule;

    private String seat;

    private String synopsis;

}
