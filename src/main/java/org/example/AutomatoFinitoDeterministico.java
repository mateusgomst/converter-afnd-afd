package org.example;

import java.util.List;
import java.util.Set;

public class AutomatoFinitoDeterministico {
    private Set<String> alfabeto;
    private Set<String> estados;
    private String estadoInicial;
    private Set<String> estadosFinais;
    private List<Transicao> transicoes;

    public AutomatoFinitoDeterministico(Set<String> alfabeto, Set<String> estados,
                                        String estadoInicial, Set<String> estadosFinais,
                                        List<Transicao> transicoes) {
        this.alfabeto = alfabeto;
        this.estados = estados;
        this.estadoInicial = estadoInicial;
        this.estadosFinais = estadosFinais;
        this.transicoes = transicoes;
    }

    public void imprimir() {
        System.out.println("Estados: " + estados);
        System.out.println("Inicial: " + estadoInicial);
        System.out.println("Finais: " + estadosFinais);
        System.out.println("Alfabeto: " + alfabeto);

        System.out.println("Transições:");
        for (Transicao t : transicoes) {
            System.out.println("  " + t);
        }
    }
}
