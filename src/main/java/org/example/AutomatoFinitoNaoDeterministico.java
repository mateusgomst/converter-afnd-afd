package org.example;

import java.util.*;

public class AutomatoFinitoNaoDeterministico {
    private Set<String> alfabeto;
    private Set<String> estados;
    private String estadoInicial;
    private Set<String> estadosFinais;
    private List<Transicao> transicoes;

    public AutomatoFinitoNaoDeterministico(Set<String> alfabeto, Set<String> estados,
                                           String estadoInicial, Set<String> estadosFinais,
                                           List<Transicao> transicoes) {
        this.alfabeto = alfabeto;
        this.estados = estados;
        this.estadoInicial = estadoInicial;
        this.estadosFinais = estadosFinais;
        this.transicoes = transicoes;
    }

    public AutomatoFinitoDeterministico converterParaAfd() {
        Map<Set<String>, String> mapaNomesEstados = new HashMap<>();
        List<Transicao> novasTransicoes = new ArrayList<>();
        Set<Set<String>> novosEstados = new HashSet<>();
        Queue<Set<String>> fila = new LinkedList<>();

        // Estado inicial
        Set<String> estadoInicialSet = new HashSet<>();
        estadoInicialSet.add(estadoInicial);
        fila.add(estadoInicialSet);
        novosEstados.add(estadoInicialSet);
        mapaNomesEstados.put(estadoInicialSet, "Q0");

        int contador = 1;

        while (!fila.isEmpty()) {
            Set<String> atual = fila.poll();
            String nomeAtual = mapaNomesEstados.get(atual);

            for (String simbolo : alfabeto) {
                Set<String> destino = new HashSet<>();

                // Para cada estado do conjunto atual
                for (String e : atual) {
                    for (Transicao t : transicoes) {
                        if (t.getOrigem().equals(e) && t.getSimbolo().equals(simbolo)) {
                            // limpa chaves { } e espaços
                            String destinoLimpo = t.getDestino().replace("{", "").replace("}", "").trim();
                            String[] destinos = destinoLimpo.split(",");
                            for (String d : destinos) {
                                destino.add(d.trim());
                            }
                        }
                    }
                }

                if (!destino.isEmpty()) {
                    if (!mapaNomesEstados.containsKey(destino)) {
                        mapaNomesEstados.put(destino, "Q" + contador++);
                        fila.add(destino);
                        novosEstados.add(destino);
                    }

                    String nomeDestino = mapaNomesEstados.get(destino);
                    novasTransicoes.add(new Transicao(nomeAtual, simbolo, nomeDestino));
                }
            }
        }

        // Define estados finais do AFD
        Set<String> novosFinais = new HashSet<>();
        for (Set<String> conjunto : novosEstados) {
            for (String e : conjunto) {
                if (estadosFinais.contains(e)) {
                    novosFinais.add(mapaNomesEstados.get(conjunto));
                    break;
                }
            }
        }

        return new AutomatoFinitoDeterministico(
                alfabeto,
                new HashSet<>(mapaNomesEstados.values()),
                "Q0",
                novosFinais,
                novasTransicoes
        );
    }


    public List<Transicao> getTransicoes() { return transicoes; }
}
