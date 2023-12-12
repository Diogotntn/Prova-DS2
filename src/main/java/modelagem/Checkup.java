/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelagem;

import java.util.Date;

public class Checkup extends Consulta {

    private double valorAdicional;

    public Checkup(Paciente paciente, Date dataAtendimento, String localAtendimento, double valorConsulta, double valorAdicional) {
        super(paciente, dataAtendimento, localAtendimento, valorConsulta);

        if (valorAdicional < 0) {
            throw new RuntimeException("Valor adicional do check-up não pode ser negativo.");
        }

        this.valorAdicional = valorAdicional;
    }

    public Checkup(Paciente paciente, double valorConsultaSimples, double valorAdicionalCheckup) {
    super(paciente, new Date(), "Local Padrão", valorConsultaSimples);

    // Validar o valor adicional
    if (valorAdicionalCheckup < 0) {
        throw new RuntimeException("Valor adicional do check-up não pode ser negativo.");
    }

    this.valorAdicional = valorAdicionalCheckup;  // Supondo que 'valorAdicional' seja o nome do atributo em 'Checkup'
}


    @Override
    public double getValorConsulta() {
        // Verificar se o paciente tem plano de saúde PlanSenai para aplicar o desconto de 15% no valor adicional
        if (getPaciente().getPlanoSaude().equalsIgnoreCase("PlanSenai")) {
            return super.getValorConsulta() + valorAdicional * 0.85; // Aplicar desconto de 15%
        } else {
            return super.getValorConsulta() + valorAdicional;
        }
    }

    // Método para consultar o valor adicional do check-up
    public double getValorAdicional() {
        return valorAdicional;
    }

    public static void main(String[] args) {
        try {
            // Criar um paciente
            Paciente paciente = new Paciente("12345678901", "João", "01/01/1990", "PlanSenai");

            // Criar uma consulta check-up
            Checkup checkup = new Checkup(paciente, new Date(), "Hospital B", 100.0, 50.0);

            // Consultar informações
            System.out.println("Paciente: " + checkup.getPaciente().getNome());
            System.out.println("Data de Atendimento: " + checkup.getDataAtendimento());
            System.out.println("Local de Atendimento: " + checkup.getLocalAtendimento());
            System.out.println("Valor da Consulta: " + checkup.getValorConsulta());
            System.out.println("Valor Adicional do Check-up: " + checkup.getValorAdicional());
        } catch (RuntimeException e) {
            // Capturar exceção não monitorada
            System.out.println("Erro: " + e.getMessage());
        }
    }
}

