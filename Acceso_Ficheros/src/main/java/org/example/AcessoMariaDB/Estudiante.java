package org.example.AcessoMariaDB;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
@Getter @AllArgsConstructor @ToString
public class Estudiante {
    private int nia;
    private String nombre;
    private Date fecha_nacimiento;
}
