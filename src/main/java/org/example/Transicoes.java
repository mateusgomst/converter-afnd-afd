package org.example;

public class Transicoes {
    private String estadoOrigem;
    private String simbolo;
    private String estadoDestino;

    public Transicoes(String estadoOrigem, String simbolo, String estadoDestino) {
        this.estadoOrigem = estadoOrigem;
        this.simbolo = simbolo;
        this.estadoDestino = estadoDestino;
    }
    public String getSimbolo() {
        return simbolo;
    }
    public String getEstadoOrigem() {
        return estadoOrigem;
    }
    public String getEstadoDestino() {
        return estadoDestino;
    }

}
