package entidades;

public class Gerente extends Funcionario {

    public Gerente(int matricula, String nome, double salario) {
        this.matricula = matricula;
        this.nome = nome;
        this.salario = salario;
    }

    public double calcularSalario(double proventos,double comissao, double descontos) {
        return (salario + proventos + comissao - descontos);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DADOS PARA O GERENTE\n\n");
        sb.append("Matrícula: "+matricula+"\n");
        sb.append("Nome: "+nome+"\n");
        sb.append("Salário base: R$ "+String.format("%.2f", salario));
        return sb.toString();
    }
}
