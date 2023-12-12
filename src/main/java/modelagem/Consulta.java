package modelagem;

import java.util.Date;

public class Consulta {

    private Paciente paciente;
    private Date dataAtendimento;
    private String localAtendimento;
    private double valorConsulta;

    public Consulta(Paciente paciente, Date dataAtendimento, String localAtendimento, double valorConsulta) {
        // Validar a data
        if (dataAtendimento == null || dataAtendimento.after(new Date())) {
            throw new RuntimeException("Data de atendimento inválida.");
        }

        // Validar o valor
        if (valorConsulta < 0) {
            throw new RuntimeException("Valor da consulta não pode ser negativo.");
        }

        this.paciente = paciente;
        this.dataAtendimento = new Date(dataAtendimento.getTime()); // Criar cópia da data para evitar alterações externas
        this.localAtendimento = localAtendimento;
        this.valorConsulta = valorConsulta;
    }

    Consulta(Paciente paciente, double valorConsultaSimples) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Métodos de consulta (getters)
    public Paciente getPaciente() {
        return paciente;
    }

    public Date getDataAtendimento() {
        return new Date(dataAtendimento.getTime());
    }

    public String getLocalAtendimento() {
        return localAtendimento;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    // Método principal com um usuario teste
    public static void main(String[] args) {
        try {
            // Criar um paciente
            Paciente paciente = new Paciente("12345678901", "João", "01/01/1990", "Plano de Saúde X");

            // Criar uma consulta
            Consulta consulta = new Consulta(paciente, new Date(), "Hospital A", 150.0);

            // Consultar informações
            System.out.println("Paciente: " + consulta.getPaciente().getNome());
            System.out.println("Data de Atendimento: " + consulta.getDataAtendimento());
            System.out.println("Local de Atendimento: " + consulta.getLocalAtendimento());
            System.out.println("Valor da Consulta: " + consulta.getValorConsulta());
        } catch (RuntimeException e) {
            // Capturar exceção não monitorada
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
