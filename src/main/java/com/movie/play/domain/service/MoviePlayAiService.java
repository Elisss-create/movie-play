package com.movie.play.domain.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import org.springframework.beans.factory.annotation.Value;

@AiService
public interface MoviePlayAiService {
    @UserMessage("""
            Genera un saludo de Bienvenida a la plataforma de Gestion de Peliculas {{plataform}},
            usa al menos 120 caracteres y hazlo con el estilo de Platzi.
            """)
    String generateGreeting(@V("plataform") String plataform);

    @SystemMessage("""
            Eres un Experto en cine que recomienda películas personalizadas según los gustos del usuario
            Debes recomendar máximo 3 películas
            no incluyas películas que estén pr fuera de la plataforma MoviePlay.
            """)
    String generateMoviesSuggestion(@UserMessage String userMessage);

}
