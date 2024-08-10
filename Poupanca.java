package ProjetoAndrei;

import java.util.ArrayList;

public class Poupanca extends Conta implements Remunerada {



	public Poupanca(int numero, String nome, String cpf, double saldo) {
		super(numero, nome, cpf, saldo);
	}

	@Override
	public void aplicaTaxa(float porcentagem) {
		double acres = getSaldo() * porcentagem / 100;
		setSaldo(getSaldo() + acres);
		
		Operacao op = new Operacao(acres, "Correcao");
		ArrayList<Operacao> lista = getOperacoes();
		lista.add(op);
		setOperacoes(lista);
	}
}