package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Definindo o alfabeto
        Set<String> alfabeto = new HashSet<>(Arrays.asList("0", "1"));

        // Definindo estados
        Set<String> estados = new HashSet<>(Arrays.asList("q0", "q1"));

        // Estado inicial
        String inicial = "q0";

        // Estado final
        Set<String> finais = new HashSet<>(Arrays.asList("q1"));

        // Transições do AFND
        List<Transicoes> transicoes = new ArrayList<>();
        transicoes.add(new Transicoes("q0", "0", "q0,q1"));
        transicoes.add(new Transicoes("q0", "1", "q0"));
        transicoes.add(new Transicoes("q1", "1", "q1"));

        // Cria AFND
        AutomatoFinitoNaoDeterministico afnd = new AutomatoFinitoNaoDeterministico(
                alfabeto, estados, inicial, finais, transicoes
        );

        // Converte (só completando transições que faltam)
        List<Transicoes> afdTransicoes = afnd.converterParaAfd(afnd);

        // Mostra resultado
        System.out.println("Transições do AFD:");
        for (Transicoes t : afdTransicoes) {
            System.out.println(t.getEstadoOrigem() + " -" + t.getSimbolo() + "-> " + t.getEstadoDestino());
        }
    }
}
