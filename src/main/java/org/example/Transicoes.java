// Pacote e imports
package org.example;

import java.util.*;

class Transicoes {
    private String estadoOrigem;
    private String simbolo;
    private String estadoDestino;

    public Transicoes(String estadoOrigem, String simbolo, String estadoDestino) {
        this.estadoOrigem = estadoOrigem;
        this.simbolo = simbolo;
        this.estadoDestino = estadoDestino;
    }

    public String getEstadoOrigem() { return estadoOrigem; }
    public String getSimbolo() { return simbolo; }
    public String getEstadoDestino() { return estadoDestino; }

    @Override
    public String toString() {
        return String.format("(%s, %s) -> %s", estadoOrigem, simbolo, estadoDestino);
    }
}