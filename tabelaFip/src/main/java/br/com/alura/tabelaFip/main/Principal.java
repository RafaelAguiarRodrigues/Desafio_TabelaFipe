package br.com.alura.tabelaFip.main;

import br.com.alura.tabelaFip.model.DadosModelo;
import br.com.alura.tabelaFip.model.DadosMarca;
import br.com.alura.tabelaFip.model.Veiculo;
import br.com.alura.tabelaFip.service.ConsumoAPI;
import br.com.alura.tabelaFip.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO_BASE = "https://parallelum.com.br/fipe/api/v1/";
    public void exibirMenu () {
        String opcoes = """
                *** Opções ***
                Carro
                Moto
                Caminhão
                
                Digite uma das opções para consultar valores:
                """;
        System.out.println(opcoes);
        var veiculo = scanner.nextLine();
        String enderecoFinal;

        if (veiculo.toLowerCase().contains("car")) {
            enderecoFinal = ENDERECO_BASE + "carros/marcas";
        } else if (veiculo.toLowerCase().contains("mot")) {
            enderecoFinal = ENDERECO_BASE + "motos/marcas";
        } else {
            enderecoFinal = ENDERECO_BASE + "caminhoes/marcas";
        }

        var json = consumoAPI.obterDados(enderecoFinal);
        // System.out.println(json);
        var marcas = conversor.obterLista(json, DadosMarca.class);
        marcas.stream()
                .sorted(Comparator.comparing(DadosMarca::codigo))
                .forEach(System.out::println);

        System.out.println("\nInforme o código da marca para consulta: ");

        var codigoModelo = scanner.nextLine();
        enderecoFinal = enderecoFinal + "/" + codigoModelo + "/modelos";
        json = consumoAPI.obterDados(enderecoFinal);
        var modelosLista = conversor.obterDados(json, DadosModelo.class);

        System.out.println("\nInforme o Modelo dessa marca: ");
        modelosLista.modelos().stream()
                .sorted(Comparator.comparing(DadosMarca::codigo))
                .forEach(System.out::println);

        System.out.println("\nInforme o nome do Carro: ");
        var nomeVeiculo = scanner.nextLine();

        modelosLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .forEach(System.out::println);

        System.out.println("\nDigite o Código do carro escolhido: ");
        var codigoCarro = scanner.nextLine();

        enderecoFinal = enderecoFinal + "/" + codigoCarro + "/anos";
        json = consumoAPI.obterDados(enderecoFinal);
        List<DadosMarca> anos = conversor.obterLista(json, DadosMarca.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecoAnos = enderecoFinal + "/" + anos.get(i).codigo();
            json = consumoAPI.obterDados(enderecoAnos);
            Veiculo veiculoAtual = conversor.obterDados(json, Veiculo.class);
            veiculos.add(veiculoAtual);
        }
        System.out.println("\nLista de veiculos por ano:");
        veiculos.forEach(System.out::println);
    }
}
