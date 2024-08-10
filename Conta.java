package ProjetoAndrei;

import java.util.ArrayList;

public abstract class Conta {
	private int numero;
	private String nome;
	private String cpf;
	private double saldo;
	private ArrayList<Operacao> operacoes;
	
	//metodos construtores
	public Conta() {
		setNumero(0);
		setNome("");
		setCpf("");
		setSaldo(0.0);
		operacoes = new ArrayList<Operacao>();
	}
	
	public Conta(int numero, String nome, String cpf, double saldo) {
		this.setNumero(numero);
		this.setNome(nome);
		this.setCpf(cpf);
		this.setSaldo(saldo);
		operacoes = new ArrayList<Operacao>();
	}
	
	//operacoes da conta
	public void depositar(double valor) {
		setSaldo(getSaldo() + valor);
		Operacao op = new Operacao(valor, "Deposito");
		operacoes.add(op);
	}
	
	public void sacar(double valor) {
		setSaldo(getSaldo() - valor);
		Operacao op = new Operacao(valor, "Saque");
		operacoes.add(op);
	}
	
	public String consultaExtrato() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<operacoes.size(); i++) {
			sb.append(operacoes.get(i).toString());
			sb.append("\n");
		}
		sb.append("Saldo: " + saldo + "\n");
		
		return sb.toString();
	}

	//getters e setters
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public ArrayList<Operacao> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(ArrayList<Operacao> operacoes) {
		this.operacoes = operacoes;
	}
	
	//metodo toString
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Numero: " + numero + "\n");
		sb.append("Nome: " + nome + "\n");
		sb.append("CPF: " + cpf + "\n");
		sb.append(consultaExtrato());
		
		return sb.toString();
	}
}