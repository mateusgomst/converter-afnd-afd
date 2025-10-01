package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutomatoFinitoNaoDeterministico {
    private Set<String> alfabeto;
    private Set<String> estados;
    private String estadoInicial;
    private Set<String> estadosFinais;
    private List<Transicoes> transicoes;


    public AutomatoFinitoNaoDeterministico(Set<String> alfabeto, Set<String> estados, String estadoInicial, Set<String> estadosFinais, List<Transicoes> transicoes) {
        this.alfabeto = alfabeto;
        this.estados = estados;
        this.estadoInicial = estadoInicial;
        this.estadosFinais = estadosFinais;
        this.transicoes = transicoes;
    }

    public List<Transicoes> converterParaAfd(AutomatoFinitoNaoDeterministico afnd) {

        Set<String> estados = afnd.getEstados();
        List<Transicoes> transicoes = afnd.getTransicoes();
        Set<String> alfabeto = afnd.getAlfabeto();

        //Descobre todos os estados que já são origem
        Set<String> origens = new HashSet<>();
        for (Transicoes t : transicoes) {
            origens.add(t.getEstadoOrigem());
        }

        //Descobre todos os estados que são destino
        Set<String> destinos = new HashSet<>();
        for (Transicoes t : transicoes) {
            destinos.add(t.getEstadoDestino());
        }

        //Para cada estado que é destino mas não é origem, cria novas transições
        List<Transicoes> novas = new ArrayList<>();
        for (String d : destinos) {
            if (!origens.contains(d)) {
                for (String a : alfabeto) {
                    novas.add(new Transicoes(d, a, null));
                }
            }
        }

        transicoes.addAll(novas);


        return transicoes;
    }

    public List<Transicoes> getTransicoes() {
        return transicoes;
    }

    public Set<String> getAlfabeto() {
        return alfabeto;
    }

    public Set<String> getEstados() {
        return estados;
    }

    public Set<String> getEstadosFinais() {
        return estadosFinais;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }
}

