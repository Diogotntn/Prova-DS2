package modelagem;

import java.util.ArrayList;
import java.util.List;

public class Clinica {

    private String nome;
    private String endereco;
    private String cnpj;
    private double valorConsultaSimples;
    private double valorAdicionalCheckup;
    private List<Consulta> atendimentosRealizados;

    // Construtor
    public Clinica(String nome, String endereco, String cnpj, double valorConsultaSimples, double valorAdicionalCheckup) {
        if (valorConsultaSimples < 0 || valorAdicionalCheckup < 0) {
            throw new RuntimeException("Valores de consulta inválidos. Devem ser não negativos.");
        }

        this.nome = nome;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.valorConsultaSimples = valorConsultaSimples;
        this.valorAdicionalCheckup = valorAdicionalCheckup;
        this.atendimentosRealizados = new ArrayList<>();
    }

    // Métodos de consulta
    public double getValorConsultaSimples() {
        return valorConsultaSimples;
    }

    public double getValorAdicionalCheckup() {
        return valorAdicionalCheckup;
    }

    public List<Consulta> getAtendimentosRealizados() {
        return atendimentosRealizados;
    }

    // Método para alterar o valor da consulta simples
    public void setValorConsultaSimples(double novoValor) {
        if (novoValor < 0) {
            throw new RuntimeException("Novo valor de consulta simples inválido. Deve ser não negativo.");
        }

        this.valorConsultaSimples = novoValor;
    }

    // Método para alterar o valor do adicional para check-up
    public void setValorAdicionalCheckup(double novoValor) {
        // Validar o novo valor
        if (novoValor < 0) {
            throw new RuntimeException("Novo valor de adicional para check-up inválido. Deve ser não negativo.");
        }

        this.valorAdicionalCheckup = novoValor;
    }

    // Método para calcular o valor total dos atendimentos realizados
    public double getValorTotal() {
        double valorTotal = 0.0;
        for (Consulta consulta : atendimentosRealizados) {
            valorTotal += consulta.getValorConsulta();
        }
        return valorTotal;
    }

    // Método para realizar um atendimento
    public double realizarAtendimento(String tipoConsulta, Paciente paciente) throws EAtendimentoNaoRegistradoExcecao {
        Consulta atendimento;

        if (tipoConsulta.equalsIgnoreCase("simples")) {
            atendimento = new Consulta(paciente, new java.util.Date(), "Local Padrão", valorConsultaSimples);
        } else if (tipoConsulta.equalsIgnoreCase("checkup")) {
            atendimento = new Checkup(paciente, new java.util.Date(), "Local Padrão", valorConsultaSimples, valorAdicionalCheckup);
        } else {
            throw new EAtendimentoNaoRegistradoExcecao("Tipo de consulta não registrado: " + tipoConsulta);
        }

        atendimentosRealizados.add(atendimento);
        return atendimento.getValorConsulta();
    }

    public static void main(String[] args) {
        try {
            // Criar uma clínica
            Clinica clinica = new Clinica("Clinica XYZ", "Rua ABC", "123456789", 100.0, 50.0);

            // Criar um paciente
            Paciente paciente = new Paciente("12345678901", "João", "01/01/1990", "Plano de Saúde X");

            // Realizar um atendimento simples
            double valorAtendimentoSimples = clinica.realizarAtendimento("simples", paciente);
            System.out.println("Valor do Atendimento Simples: " + valorAtendimentoSimples);

            // Realizar um atendimento check-up
            double valorAtendimentoCheckup = clinica.realizarAtendimento("checkup", paciente);
            System.out.println("Valor do Atendimento Check-up: " + valorAtendimentoCheckup);

            // Consultar o valor total dos atendimentos
            System.out.println("Valor Total dos Atendimentos: " + clinica.getValorTotal());
        } catch (RuntimeException | EAtendimentoNaoRegistradoExcecao e) {
            // Capturar exceção
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
