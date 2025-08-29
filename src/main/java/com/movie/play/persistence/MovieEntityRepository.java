package com.movie.play.persistence;

import com.movie.play.domain.dto.MovieDto;
import com.movie.play.domain.dto.UpdateMovieDto;
import com.movie.play.domain.exception.MovieAlreadyExistsException;
import com.movie.play.domain.exception.MovieNotExistException;
import com.movie.play.domain.repository.MovieRepository;
import com.movie.play.persistence.crud.CrudMovieEntity;
import com.movie.play.persistence.entity.MovieEntity;
import com.movie.play.persistence.mapper.MovieMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class MovieEntityRepository implements MovieRepository {
    private final CrudMovieEntity crudMovieEntity;
    private final MovieMapper movieMapper ;

    public MovieEntityRepository(CrudMovieEntity crudMovieEntity, MovieMapper movieMapper) {
        this.crudMovieEntity = crudMovieEntity;
        this.movieMapper = movieMapper;
    }

    @Override
    public List<MovieDto> getAll() {
        return this.movieMapper.toDto(this.crudMovieEntity.findAll());
    }

    @Override
    public MovieDto getById(Long id) {
        MovieEntity movieEntity=this.crudMovieEntity.findById(id).orElse(null);
        return this.movieMapper.toDto(movieEntity);
    }

   @Override
    public MovieDto save(MovieDto movieDto) {
       if (this.crudMovieEntity.findFirstByTitulo(movieDto.title()) != null) {
           throw new MovieAlreadyExistsException(movieDto.title());
       }
           MovieEntity movieEntity = this.movieMapper.toEntity(movieDto);

            return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));
    }

    @Override
    public MovieDto update(long id, UpdateMovieDto updateMovieDto) {
        MovieEntity movieEntity=this.crudMovieEntity.findById(id).orElse(null);

        if(movieEntity==null){
            throw new MovieNotExistException(id);
        }

        this.movieMapper.updateEntityFromDto(updateMovieDto,movieEntity);

        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));
    }

    @Override
    public void deleteById(long id) {
       //this.crudMovieEntity.findById(id).ifPresent(crudMovieEntity::delete);
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);

        if (movieEntity == null) {
            throw new MovieNotExistException(id);
        }
        this.crudMovieEntity.deleteById(id);
    }

}
