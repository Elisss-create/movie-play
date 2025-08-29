package com.movie.play.persistence.crud;

import com.movie.play.persistence.entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudMovieEntity extends CrudRepository<MovieEntity,Long> {
    MovieEntity findFirstByTitulo(String titulo);
}
