package com.movie.play.web.controller;

import com.movie.play.domain.dto.MovieDto;
import com.movie.play.domain.dto.SuggestRequestDto;
import com.movie.play.domain.dto.UpdateMovieDto;
import com.movie.play.domain.service.MoviePlayAiService;
import com.movie.play.domain.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name="Movies", description= "Operations about movies of PlatziPlay")
public class MovieController {
    private final MovieService movieService;
    private final MoviePlayAiService aiService;

    public MovieController(MovieService movieService, MoviePlayAiService moviePlayAiService, MoviePlayAiService aiService) {
        this.movieService = movieService;
        this.aiService = aiService;
    }
    @GetMapping
    public ResponseEntity <List<MovieDto>> getAll(){
        return ResponseEntity.ok(this.movieService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(
                summary = "Obtener una película por su identificador",
                description = "Retorna la película que coincida con el identificador enviado",
                responses={
                        @ApiResponse(responseCode = "200",description = "Pelicula encontrada"),
                        @ApiResponse(responseCode = "404", description = "Película No encontrada",content = @Content)
                 }
            )
    public ResponseEntity<MovieDto> getById(@Parameter(description ="Identificador de la película a recuperar",example = "9") @PathVariable Long id){

        MovieDto movieDto=this.movieService.getById(id);
        if(movieDto==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieDto);
    }
    @PostMapping("/suggest")
    public  ResponseEntity<String>generateMoviesSuggestion(@RequestBody SuggestRequestDto suggestRequestDto){
    return ResponseEntity.ok(this.aiService.generateMoviesSuggestion(suggestRequestDto.userPreferences()));
    }
    @PostMapping
    public ResponseEntity <MovieDto> add(@RequestBody @Valid MovieDto movieDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.movieService.add(movieDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable long id,@RequestBody @Valid UpdateMovieDto updateMovieDto){
        return ResponseEntity.ok(this.movieService.update(id,updateMovieDto));
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteById(@PathVariable long id){
        this.movieService.deleteById(id);
        //return ResponseEntity.ok("Movie with ID " + id + " was deleted successfully.");
        return ResponseEntity.ok().build();
    }
}

