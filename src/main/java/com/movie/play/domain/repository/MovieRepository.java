package com.movie.play.domain.repository;

import com.movie.play.domain.dto.MovieDto;
import com.movie.play.domain.dto.UpdateMovieDto;

import java.util.List;

public interface MovieRepository {
    List<MovieDto>getAll();
    MovieDto getById(Long id);
    MovieDto save(MovieDto movieDto);
    MovieDto update(long id, UpdateMovieDto updateMovieDto);
    void deleteById(long id);

}
