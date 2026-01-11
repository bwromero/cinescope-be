package com.bwromero.cinescope.service;

import com.bwromero.cinescope.model.WatchlistItem;
import com.bwromero.cinescope.repository.WatchlistRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {

    private final WatchlistRepository repository;

    public WatchlistService(WatchlistRepository watchlistRepository) {
        this.repository = watchlistRepository;
    }

    public List<WatchlistItem> getWatchList() {
        return repository.findAll();
    }
    
    public WatchlistItem addToWatchlist(WatchlistItem item) {
        if(repository.existsByMovieId(item.getMovieId())){
            throw new RuntimeException("Movie already in watchlist");
        }
        return repository.save(item);
    }

    @Transactional
    public void removeFromWatchlist(Long movieId) {
        repository.deleteByMovieId(movieId);
    }
}
