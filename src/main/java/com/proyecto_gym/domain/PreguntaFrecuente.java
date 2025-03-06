package com.proyecto_gym.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "preguntas_frecuentes") //table name from the db
public class PreguntaFrecuente {

    private static final long serialVersionUID = 1L; //getter autoincremental id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPreguntaFrecuente;
    private String pregunta;
    private String respuesta;

    public int getIdPreguntaFrecuente() {
        return idPreguntaFrecuente;
    }

    public void setIdPreguntaFrecuente(int idPreguntaFrecuente) {
        this.idPreguntaFrecuente = idPreguntaFrecuente;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

}//end class
