package com.movie.play.domain.exception;

public class MovieNotExistException extends RuntimeException {
    public MovieNotExistException(Long id){
        super("La película con Id "+ id+" No existe..");
    }
}
