package com.movie.play.domain.dto;

import com.movie.play.domain.Genre;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record MovieDto(
        Long id,
        @NotBlank(message = "El título es Obligatorio")
        String title,
        @Positive(message = "El valor de la duracion debe ser un numero positivo")
        Integer duration,
        Genre genre,
        @PastOrPresent(message = "La fecha de lanzamiento debe ser anterior o igual a la actual")
        LocalDate releaseDate,
        @Min(value=0, message = "El rating no puede ser menor que 0")
        @Max(value = 5, message = "El rating no debe ser mayor a 5")
        Double rating,
        @NotNull (message = "El campo 'disponible' es obligatorio")
        Boolean state
) {


}
