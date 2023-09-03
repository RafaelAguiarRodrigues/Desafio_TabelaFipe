package br.com.alura.tabelaFip.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo (@JsonAlias("Valor") String valor,
                       @JsonAlias("Marca") String marca,
                       @JsonAlias("Modelo") String modelo,
                       @JsonAlias("AnoModelo") Integer ano,
                       @JsonAlias("Combustivel") String combustivel) {

    @Override
    public String toString() {
        return "Veiculo{" +
                "valor= R$" + valor +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", combustivel='" + combustivel + '\'' +
                '}';
    }
}
