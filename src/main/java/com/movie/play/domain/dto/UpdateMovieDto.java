package com.movie.play.domain.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UpdateMovieDto(
        @NotBlank(message = "El título es Obligatorio")
        String title,
        @PastOrPresent(message="La fecha de lanzamiento debe ser anterior a la fecha actual.")
        LocalDate releaseDate,
        @Min(value=0, message = "El rating no puede ser menor que 0")
        @Max(value = 5, message = "El rating no debe ser mayor a 5")
        Double rating,
        @NotNull (message = "El campo 'disponible' es obligatorio")
        Boolean state

) {


}
