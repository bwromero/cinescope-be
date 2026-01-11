package com.bwromero.cinescope.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public record MovieDto(
    Long id,
    String title,
    @JsonAlias("overview") String synopsis,
    @JsonAlias("poster_path") String posterPath,
    @JsonAlias("release_date") String releaseDate,
    @JsonAlias("vote_average") Double rating
) {}
