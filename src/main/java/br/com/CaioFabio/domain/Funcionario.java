package br.com.CaioFabio.domain;

import java.io.Serializable;
import java.text.NumberFormat;

public abstract class Funcionario implements Serializable {

    protected String id;
    protected long matricula;
    protected String nome;
    protected String cargo;
    protected String tipo;
    protected NumberFormat formatador = NumberFormat.getCurrencyInstance();

    public Funcionario() {}

    public Funcionario(long matricula, String nome, String cargo) {
        this.matricula = matricula;
        this.nome = nome;
        this.cargo = cargo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public abstract void calcularSalario();

    protected void mostrarCabecalho() {
        System.out.println("----- Contracheque -----");
        System.out.println("Nome............" + nome);
        System.out.println("Cargo..........." + cargo);
    }

    protected double calcularImposto(double valor) {
        double imposto = 0;
        if (valor > 2000) {
            imposto = valor * 0.15;
        } else if (valor > 3000) {
            imposto = valor * 0.25;
        } else if (valor > 5000) {
            imposto = valor * 0.30;
        }
        return imposto;
    }
}
