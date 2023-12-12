package aplicacao;

import java.util.Scanner;

import modelagem.Clinica;
import modelagem.EAtendimentoNaoRegistradoExcecao;
import modelagem.Paciente;

public class AplicacaoClinica {

    public static void main(String[] args) {
        // Criar uma clínica
        Clinica senaiSaude = new Clinica("Senai Saúde", "Av Dendezeiros, 188, Bonfim", "123456789", 75.0, 150.0);

        // Criar um scanner para entrada do usuário
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOpções:");
            System.out.println("a. Realizar atendimento");
            System.out.println("b. Consultar a média de valor dos atendimentos realizados");
            System.out.println("c. Alterar o valor da consulta simples ou o adicional para exames");
            System.out.println("d. Sair");

            System.out.print("Escolha uma opção (a, b, c, d): ");
            String opcao = scanner.next();

            switch (opcao.toLowerCase()) {
                case "a" -> realizarAtendimento(senaiSaude, scanner);
                case "b" -> consultarMediaAtendimentos(senaiSaude);
                case "c" -> alterarValores(senaiSaude, scanner);
                case "d" -> {
                    System.out.println("Saindo da aplicação.");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void realizarAtendimento(Clinica clinica, Scanner scanner) {
        System.out.print("Digite o tipo de consulta (simples ou checkup): ");
        String tipoConsulta = scanner.next();

        System.out.print("Digite o CPF do paciente: ");
        String cpf = scanner.next();

        System.out.print("Digite o nome do paciente: ");
        String nome = scanner.next();

        System.out.print("Digite a data de nascimento do paciente (formato dd/MM/yyyy): ");
        String dataNascimento = scanner.next();

        System.out.print("Digite o plano de saúde do paciente: ");
        String planoSaude = scanner.next();

        Paciente paciente = new Paciente(cpf, nome, dataNascimento, planoSaude);

        try {
            double valorAtendimento = clinica.realizarAtendimento(tipoConsulta, paciente);
            System.out.println("Atendimento realizado com sucesso. Valor do atendimento: R$" + valorAtendimento);
        } catch (EAtendimentoNaoRegistradoExcecao e) {
            System.out.println("Não foi possível realizar o atendimento. Motivo: " + e.getMessage());
        }
    }

    private static void consultarMediaAtendimentos(Clinica clinica) {
        double valorTotal = clinica.getValorTotal();
        int numeroAtendimentos = clinica.getAtendimentosRealizados().size();

        if (numeroAtendimentos > 0) {
            double media = valorTotal / numeroAtendimentos;
            System.out.println("Média de valor dos atendimentos realizados: R$" + media);
        } else {
            System.out.println("Ainda não foram realizados atendimentos.");
        }
    }

    private static void alterarValores(Clinica clinica, Scanner scanner) {
        System.out.print("Digite 's' para alterar o valor da consulta simples, 'a' para alterar o adicional para exames: ");
        String escolha = scanner.next();

        if (escolha.equalsIgnoreCase("s")) {
            System.out.print("Digite o novo valor da consulta simples: ");
            double novoValor = scanner.nextDouble();

            try {
                clinica.setValorConsultaSimples(novoValor);
                System.out.println("Valor da consulta simples alterado com sucesso.");
            } catch (RuntimeException e) {
                System.out.println("Não foi possível alterar o valor. Motivo: " + e.getMessage());
            }
        } else if (escolha.equalsIgnoreCase("a")) {
            System.out.print("Digite o novo valor adicional para exames: ");
            double novoValor = scanner.nextDouble();

            try {
                clinica.setValorAdicionalCheckup(novoValor);
                System.out.println("Valor adicional para exames alterado com sucesso.");
            } catch (RuntimeException e) {
                System.out.println("Não foi possível alterar o valor. Motivo: " + e.getMessage());
            }
        } else {
            System.out.println("Escolha inválida.");
        }
    }
}
