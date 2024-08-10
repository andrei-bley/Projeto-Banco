package ProjetoAndrei;

import java.util.ArrayList;

public class Corrente extends Conta implements Pix {
	//declarando atributos
	private boolean pixAtivo;

	//metodos construtores
	public Corrente() {
		pixAtivo = false;
	}

	public Corrente(int numero, String nome, String cpf, double saldo) {
		super(numero, nome, cpf, saldo);
		pixAtivo = false;
	}
	
	//metodos getters e setters
	public boolean getPixAtivo() {
		return pixAtivo;
	}
	
	public void setPixAtivo(boolean pixAtivo) {
		this.pixAtivo = pixAtivo;
	}
	
	//metodos da interface Pix
	@Override
	public void cadastraPix() {
		pixAtivo = true;
	}

	@Override
	public void efetuaPix(double valor) {
		if(pixAtivo) {
			setSaldo(getSaldo() - valor);
		}
		Operacao op = new Operacao(valor, "Pix out");
		ArrayList<Operacao> lista = getOperacoes();
		lista.add(op);
		setOperacoes(lista);
	}

	@Override
	public void recebePix(double valor) {
		if(pixAtivo) {
			setSaldo(getSaldo() + valor);
		}
		Operacao op = new Operacao(valor, "Pix in");
		ArrayList<Operacao> lista = getOperacoes();
		lista.add(op);
		setOperacoes(lista);
	}
}