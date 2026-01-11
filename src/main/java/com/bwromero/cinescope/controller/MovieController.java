package com.bwromero.cinescope.controller;

import com.bwromero.cinescope.dto.GenreDto;
import com.bwromero.cinescope.dto.MovieDto;
import com.bwromero.cinescope.service.MovieService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "http://localhost:4200")  // Add this!
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/trending")
    public Mono<List<MovieDto>> getTrending(){
        return movieService.getTrendingMovies();
    }

    @GetMapping("/popular")
    public Mono<List<MovieDto>> getPopular(@RequestParam(defaultValue = "1") int page) {
        return movieService.getPopularMovies(page);
    }

    @GetMapping("/search")
    public Mono<List<MovieDto>> search(@RequestParam String query) {
        return movieService.searchMovies(query);
    }

    @GetMapping("/{id}")
    public Mono<MovieDto> getDetails(@PathVariable Long id) {
        return movieService.getMovieDetails(id);
    }

    @GetMapping("/genres")
    public Mono<List<GenreDto>> getGenres() {
        return movieService.getGenres();
    }
}
