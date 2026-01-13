package com.bwromero.cinescope.service;

import com.bwromero.cinescope.dto.GenreDto;
import com.bwromero.cinescope.dto.VideoDto;
import com.bwromero.cinescope.model.WatchlistItem;
import com.bwromero.cinescope.repository.WatchlistRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.bwromero.cinescope.dto.MovieDto;

@Service
public class WatchlistService {

    private final WatchlistRepository repository;

    public WatchlistService(WatchlistRepository watchlistRepository) {
        this.repository = watchlistRepository;
    }

    public List<WatchlistItem> getWatchList() {
        return repository.findAll();
    }

    public WatchlistItem addToWatchlist(MovieDto dto) {
        if(repository.existsByMovieId(dto.id())){
            throw new RuntimeException("Movie already in watchlist");
        }
        
        String genreNames = (dto.genres() != null) 
            ? dto.genres().stream().map(GenreDto::name).collect(Collectors.joining(", ")) 
            : "";

        String trailerKey = (dto.videos() != null && dto.videos().results() != null)
                ? dto.videos().results().stream()
                .filter(v -> "YouTube".equals(v.site()) && "Trailer".equals(v.type()))
                .map(VideoDto::key)
                .findFirst()
                .orElse(null)
                : null;

        WatchlistItem item = WatchlistItem.builder()
                .movieId(dto.id())
                .title(dto.title())
                // ... other fields ...
                .youtubeTrailerKey(trailerKey)
                .genres(genreNames)
                .build();

        return repository.save(item);
    }

    @Transactional
    public void removeFromWatchlist(Long movieId) {
        repository.deleteByMovieId(movieId);
    }
}
