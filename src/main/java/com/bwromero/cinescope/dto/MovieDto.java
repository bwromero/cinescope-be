package com.bwromero.cinescope.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MovieDto(
    Long id,
    String title,
    @JsonProperty("overview") String synopsis,
    @JsonProperty("poster_path") String posterPath,
    @JsonProperty("release_date") String releaseDate,
    @JsonProperty("vote_average") Double rating
) {}
