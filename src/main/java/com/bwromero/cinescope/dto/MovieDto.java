package com.bwromero.cinescope.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record MovieDto(
    Long id,
    String title,
    @JsonAlias("overview") String synopsis,
    @JsonAlias("poster_path") String posterPath,
    @JsonAlias("backdrop_path") String backdropPath,
    @JsonAlias("release_date") String releaseDate,
    @JsonAlias("vote_average") Double rating,
    @JsonAlias("vote_count") Integer voteCount,
    Integer runtime,
    String tagline,
    String status,
    List<GenreDto> genres,
    @JsonAlias("genre_ids") List<Integer> genreIds, // Add this to catch IDs from the list endpoints
    VideoResponse videos
) {
    public record VideoResponse(List<VideoDto> results) {}

}
