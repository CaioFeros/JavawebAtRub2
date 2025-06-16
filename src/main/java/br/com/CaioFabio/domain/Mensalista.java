package br.com.CaioFabio.domain;

public class Mensalista extends Funcionario {

    protected double salario;

    public Mensalista() {}

    public Mensalista(long matricula, String nome, String cargo, double salario) {
        super(matricula, nome, cargo);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public void calcularSalario() {
        super.mostrarCabecalho();
        System.out.println("Salário Bruto..." + formatador.format(salario));
        double imposto = calcularImposto(this.salario);
        double salarioLiq = this.salario - imposto;
        System.out.println("Imposto..........." + formatador.format(imposto));
        System.out.println("Salário Liquido." + formatador.format(salarioLiq));
    }

}
