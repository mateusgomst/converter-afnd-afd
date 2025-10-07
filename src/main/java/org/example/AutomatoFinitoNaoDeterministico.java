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
        mapaNomesEstados.put(estadoInicialSet, gerarNomeEstado(estadoInicialSet));

        int contador = 0;

        while (!fila.isEmpty()) {
            Set<String> estadoAtual = fila.poll();
            String nomeEstadoAtual = mapaNomesEstados.get(estadoAtual);

            for (String simbolo : alfabeto) {
                if (simbolo.equals("ε")) continue;

                Set<String> proximoEstado = new HashSet<>();

                // Para cada estado no conjunto atual, encontrar todos os destinos
                for (String estado : estadoAtual) {
                    for (Transicao transicao : transicoes) {
                        if (transicao.getOrigem().equals(estado) &&
                                transicao.getSimbolo().equals(simbolo)) {
                            proximoEstado.add(transicao.getDestino());
                        }
                    }
                }

                // Se não há transição, vai para estado morto
                if (proximoEstado.isEmpty()) {
                    proximoEstado.add("vazio");
                }

                // Se é um novo estado, adicionar na fila
                if (!novosEstados.contains(proximoEstado)) {
                    novosEstados.add(proximoEstado);
                    mapaNomesEstados.put(proximoEstado, gerarNomeEstado(proximoEstado));
                    fila.add(proximoEstado);
                }

                String nomeProximoEstado = mapaNomesEstados.get(proximoEstado);
                novasTransicoes.add(new Transicao(nomeEstadoAtual, simbolo, nomeProximoEstado));
            }
        }

        // Definir estados finais
        Set<String> novosEstadosFinais = new HashSet<>();
        for (Set<String> conjuntoEstado : novosEstados) {
            for (String estado : conjuntoEstado) {
                if (estadosFinais.contains(estado) && !estado.equals("vazio")) {
                    novosEstadosFinais.add(mapaNomesEstados.get(conjuntoEstado));
                    break;
                }
            }
        }

        // Coletar todos os nomes de estados do AFD
        Set<String> nomesEstadosAfd = new HashSet<>(mapaNomesEstados.values());

        return new AutomatoFinitoDeterministico(
                alfabeto,
                nomesEstadosAfd,
                mapaNomesEstados.get(Collections.singleton(estadoInicial)),
                novosEstadosFinais,
                novasTransicoes
        );
    }

    private String gerarNomeEstado(Set<String> estados) {
        if (estados.contains("vazio")) {
            return "vazio";
        }

        List<String> estadosOrdenados = new ArrayList<>(estados);
        Collections.sort(estadosOrdenados);

        if (estadosOrdenados.size() == 1) {
            return "{" + estadosOrdenados.get(0) + "}";
        } else {
            return "{" + String.join(",", estadosOrdenados) + "}";
        }
    }
}