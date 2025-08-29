package com.movie.play.domain.service;

import com.movie.play.domain.dto.MovieDto;
import com.movie.play.domain.dto.UpdateMovieDto;
import com.movie.play.domain.repository.MovieRepository;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    @Tool("Busca todas las películas que existan dentro de la plataforma")
    public List<MovieDto>getAll(){
        return this.movieRepository.getAll();
    }
    public MovieDto getById(Long id){
        return this.movieRepository.getById(id);
    }
    public  MovieDto add(MovieDto movieDto){
        return this.movieRepository.save(movieDto);
    }
    public MovieDto update(long id, UpdateMovieDto updateMovieDto){
        return this.movieRepository.update(id,updateMovieDto);
    }
    public void deleteById(long id){
        this.movieRepository.deleteById(id);
    }
}
