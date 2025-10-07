package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<String> alfabeto = new HashSet<>(Arrays.asList("0", "1"));
        Set<String> estados = new HashSet<>(Arrays.asList("q1", "q2"));
        String inicial = "q1";
        Set<String> finais = new HashSet<>(Collections.singletonList("q2"));

        List<Transicao> transicoes = Arrays.asList(
                new Transicao("q1", "1", "q1"),
                new Transicao("q1", "1", "q2"),
                new Transicao("q2", "1", "q2")
        );

        AutomatoFinitoNaoDeterministico afnd = new AutomatoFinitoNaoDeterministico(
                alfabeto, estados, inicial, finais, transicoes
        );

        AutomatoFinitoDeterministico afd = afnd.converterParaAfd();
        afd.imprimir();
    }
}