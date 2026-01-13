package com.bwromero.cinescope.controller;

import com.bwromero.cinescope.dto.MovieDto;
import com.bwromero.cinescope.model.WatchlistItem;
import com.bwromero.cinescope.service.WatchlistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/watchlist")
public class WatchListController {

    private final WatchlistService watchlistService;

    public WatchListController(WatchlistService watchlistService){
        this.watchlistService = watchlistService;
    }

    @GetMapping
    public List<WatchlistItem> getWatchList(){
        return watchlistService.getWatchList();
    }
    
    @PostMapping
    public WatchlistItem add(@RequestBody MovieDto movieDto) {
        return watchlistService.addToWatchlist(movieDto);
    }

    @DeleteMapping("/{movieId}")
    public void remove(@PathVariable Long movieId) {
        watchlistService.removeFromWatchlist(movieId);
    }

}
