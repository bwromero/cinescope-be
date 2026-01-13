package com.bwromero.cinescope.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "watchlist")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WatchlistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private Long movieId;

    private String title;
    private String posterPath;
    private String backdropPath;
    
    @Column(length = 2000)
    private String synopsis;
    
    private Double rating;
    private Integer voteCount;
    private Integer runtime;
    private String tagline;
    private String releaseDate;

    private LocalDateTime addedAt;


    @PrePersist
    protected void onCreate() {
        addedAt = LocalDateTime.now();
    }
}
