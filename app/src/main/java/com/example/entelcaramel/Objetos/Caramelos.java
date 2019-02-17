package com.example.entelcaramel.Objetos;

public class Caramelos {

    String carameloId;
    String envoltorio;
    String sabor;

    public Caramelos(String carameloId, String envoltorio, String sabor) {
        this.carameloId = carameloId;
        this.envoltorio = envoltorio;
        this.sabor = sabor;
    }

    public String getCarameloId() {
        return carameloId;
    }

    public String getEnvoltorio() {
        return envoltorio;
    }

    public String getSabor() {
        return sabor;
    }

}
