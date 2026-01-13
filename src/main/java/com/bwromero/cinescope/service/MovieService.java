package com.bwromero.cinescope.service;


import com.bwromero.cinescope.dto.GenreDto;
import com.bwromero.cinescope.dto.GenreResponse;
import com.bwromero.cinescope.dto.MovieDto;
import com.bwromero.cinescope.dto.TmdbResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MovieService {

    private final WebClient tmdbWebClient;

    public MovieService(WebClient tmdbWebClient) {
        this.tmdbWebClient = tmdbWebClient;
    }

    public Mono<List<GenreDto>> getGenres() {
        return tmdbWebClient.get()
                .uri("/genre/movie/list")
                .retrieve()
                .bodyToMono(GenreResponse.class)
                .map(GenreResponse::genres);
    }

    public Mono<List<MovieDto>> getNowPlayingMovies(int page) {
        return fetchMovieList("/movie/now-playing", page);
    }

    public Mono<List<MovieDto>> getUpcomingMovies(int page) {
        return fetchMovieList("/movie/upcoming", page);
    }

    public Mono<List<MovieDto>> getTopRatedMovies(int page) {
        return fetchMovieList("/movie/top-rated", page);
    }

    public Mono<List<MovieDto>> getPopularMovies(int page) {
        return fetchMovieList("/movie/popular", page);
    }

    public Mono<List<MovieDto>> searchMovies(String query) {
        return tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("query", query)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<TmdbResponse<MovieDto>>() {})
                .map(TmdbResponse::results);
    }

    public Mono<MovieDto> getMovieDetails(Long id) {
        return tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{id}")
                        .queryParam("append_to_response", "videos") // Get trailer in one request!
                        .build(id))
                .retrieve()
                .bodyToMono(MovieDto.class);
    }

    public Mono<List<MovieDto>> getTrendingMovies() {
        return tmdbWebClient.get()
                .uri("/trending/movie/week")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<TmdbResponse<MovieDto>>() {})
                .map(TmdbResponse::results);
    }

    private Mono<List<MovieDto>> fetchMovieList(String path, int page) {
        return tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<TmdbResponse<MovieDto>>() {})
                .map(TmdbResponse::results);
    }
}
