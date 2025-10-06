package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<String> alfabeto = new HashSet<>(Arrays.asList("0", "1"));
        Set<String> estados = new HashSet<>(Arrays.asList("q0", "q1"));
        String inicial = "q0";
        Set<String> finais = new HashSet<>(Collections.singletonList("q1"));

        List<Transicao> transicoes = List.of(
                new Transicao("q0", "0", "{q0,q1}"),
                new Transicao("q0", "1", "q0"),
                new Transicao("q1", "1", "q1")
        );

        AutomatoFinitoNaoDeterministico afnd = new AutomatoFinitoNaoDeterministico(
                alfabeto, estados, inicial, finais, transicoes
        );

        AutomatoFinitoDeterministico afd = afnd.converterParaAfd();
        afd.imprimir();
    }
}
