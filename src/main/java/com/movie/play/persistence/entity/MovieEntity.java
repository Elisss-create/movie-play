package com.movie.play.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import org.apache.juli.logging.Log;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Data
@Table(name="movie_play")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 150,unique = true)
    private String titulo;

    @Column(nullable = false,precision = 3)
    private Integer duracion;

    @Column(nullable = false,length = 40)
    private String genero;

    @Column(name="fecha_estreno")
    private LocalDate fechaEstreno;

    @Column(precision = 3,scale = 2)
    private BigDecimal clasificacion;

    @Column(nullable = false,length = 1)
    private String estado;
}
