package com.proyecto_gym.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sobre_nosotros") //table name from the db
public class SobreNosotros {

    private static final long serialVersionUID = 1L; //getter autoincremental id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInfo;
    private String mision;
    private String vision;
    private String historia;
    private String valores;

    // Getter y Setter para idInfo
    public int getIdInfo() {
        return idInfo;
    }

    public void setIdInfo(int idInfo) {
        this.idInfo = idInfo;
    }

    // Getter y Setter para mision
    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    // Getter y Setter para vision
    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    // Getter y Setter para historia
    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    // Getter y Setter para valores
    public String getValores() {
        return valores;
    }

    public void setValores(String valores) {
        this.valores = valores;
    }
}
