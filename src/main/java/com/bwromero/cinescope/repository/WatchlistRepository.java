package com.bwromero.cinescope.repository;

import com.bwromero.cinescope.model.WatchlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface WatchlistRepository extends JpaRepository<WatchlistItem, Long> {
    Optional<WatchlistItem> findByMovieId(Long movieId);
    void deleteByMovieId(Long movieId);
    boolean existsByMovieId(Long movieId);
}
