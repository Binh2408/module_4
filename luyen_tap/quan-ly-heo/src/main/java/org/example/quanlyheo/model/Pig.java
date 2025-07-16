package org.example.quanlyheo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pig {//heo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inputDate;
    private double inputWeight;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate outputDate;
    private double outputWeight;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Origin origin;
}
