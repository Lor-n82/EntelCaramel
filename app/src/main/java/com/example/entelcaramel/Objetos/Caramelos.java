package com.example.entelcaramel.Objetos;

import java.io.Serializable;

public class Caramelos implements Serializable {

    String envoltorio;
    String sabor;

    public Caramelos(){

    }

    public Caramelos(String envoltorio, String sabor) {
        this.envoltorio = envoltorio;
        this.sabor = sabor;
    }

    public String getEnvoltorio() {
        return envoltorio;
    }

    public void setEnvoltorio(String envoltorio) {
        this.envoltorio = envoltorio;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }
}
