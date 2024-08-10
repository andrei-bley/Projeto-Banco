package ProjetoAndrei;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Operacao {
	private LocalDate data; 
	private double valor;
	private String tipo;
	
	//metodos construtores
	public Operacao() {
		data = LocalDate.now();
		valor = 0.0;
		tipo = "";
	}
	
	public Operacao(double valor, String tipo) {
		this.data = LocalDate.now();
		this.valor = valor;
		this.tipo = tipo;
	}
	
	public Operacao(LocalDate data, double valor, String tipo) {
		this.data = data;
		this.valor = valor;
		this.tipo = tipo;
	}
	
	//getters e setters
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setEhPositivo(String tipo) {
		this.tipo = tipo;
	}
	
	//metodo toString
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		sb.append(data.format(dataFormatada));
		sb.append("   ");
		sb.append(String.format("%-8s", tipo));
		sb.append("   ");
		sb.append(String.format("%8.2f", valor));
				
		return sb.toString();
	}
}
