package com.bwromero.cinescope.controller;

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
    public WatchlistItem add(@RequestBody WatchlistItem item) {
        return watchlistService.addToWatchlist(item);
    }

    @DeleteMapping("/{movieId}")
    public void remove(@PathVariable Long movieId) {
        watchlistService.removeFromWatchlist(movieId);
    }

}
