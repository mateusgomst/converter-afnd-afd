package org.example;

public class Transicao {
    private String origem;
    private String simbolo;
    private String destino;

    public Transicao(String origem, String simbolo, String destino) {
        this.origem = origem;
        this.simbolo = simbolo;
        this.destino = destino;
    }

    public String getOrigem() { return origem; }
    public String getSimbolo() { return simbolo; }
    public String getDestino() { return destino; }

    @Override
    public String toString() {
        return origem + " --" + simbolo + "--> " + destino;
    }
}
