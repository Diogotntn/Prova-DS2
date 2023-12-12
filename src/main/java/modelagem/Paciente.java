package modelagem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Paciente {

    private String cpf;
    private String nome;
    private Date dataNascimento;
    private final String planoSaude;

    public Paciente(String cpf, String nome, String dataNascimento, String planoSaude) {
        this.cpf = cpf;
        this.nome = nome;

        // Tentar converter a data de nascimento
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.dataNascimento = sdf.parse(dataNascimento);
        } catch (ParseException e) {
            // Gerar exceção não monitorada se a data de nascimento for inválida
            throw new RuntimeException("Data de nascimento inválida. Use o formato dd/MM/yyyy.");
        }

        this.planoSaude = planoSaude;
    }

    // Métodos de consulta (getters)
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return new Date(dataNascimento.getTime());  // Retorna uma cópia da data para evitar alterações externas
    }

    public String getPlanoSaude() {
        return planoSaude;
    }

    // Exemplo de utilização
    public static void main(String[] args) {
        try {
            // Criar um paciente
            Paciente paciente = new Paciente("12345678901", "João", "01/01/1990", "Plano de Saúde X");

            // Consultar informações
            System.out.println("CPF: " + paciente.getCpf());
            System.out.println("Nome: " + paciente.getNome());
            System.out.println("Data de Nascimento: " + paciente.getDataNascimento());
            System.out.println("Plano de Saúde: " + paciente.getPlanoSaude());
        } catch (RuntimeException e) {
            // Capturar exceção não monitorada (Data de Nascimento inválida)
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
