//Referencias:
// - https://www.devmedia.com.br/a-classe-stringbuilder-em-java/25609
// - https://www.w3schools.com/java/java_date.asp
// - https://www.w3schools.com/java/java_arraylist.asp
// - https://stackoverflow.com/questions/5480615/how-to-build-a-formatted-string-in-java
// - https://www.w3schools.com/java/java_interface.asp
// - https://www.w3schools.com/java/java_abstract.asp
// - https://docs.oracle.com/javase%2Ftutorial%2F/java/data/numberformat.html
// - https://stackoverflow.com/questions/5887709/getting-random-numbers-in-java
// - https://www.baeldung.com/java-instanceof

package ProjetoAndrei;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Banco {
	
	//array list que ira armazenar as contas bancarias
	private static ArrayList<Conta> contas = new ArrayList<Conta>();

	public static void main(String[] args) {
		//criando uma conta de exemplo
		Corrente teste = new Corrente(123, "Andrei", "512", 0.0);
		teste.depositar(1000);
		teste.sacar(500);
		teste.cadastraPix();
		contas.add(teste);
		
		//instanciando objeto da classe scanner
		Scanner sc = new Scanner(System.in);
		
		//declarando variaveis auxiliares
		int menu = 0;
		
		do {
			exibeMenu();
			menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
				case 1:
					criaContaCorrente(sc);
					break;
				case 2:
					criaContaPoupanca(sc);
					break;
				case 3:
					efetuaDeposito(sc);
					break;
				case 4:
					efetuaSaque(sc);
					break;
				case 5: 
					aplicaTaxa(sc);
					break;
				case 6:
					cadastraPix(sc);
					break;
				case 7:
					efetuaPix(sc);
					break;
				case 8:
					consultaExtrato(sc);
					break;
				default:
					
			}
			
		} while(menu != 9);
		
		sc.close();
	}
	
	public static void exibeMenu() {
		System.out.print("""
				--------- MENU ---------
				1 - Criar conta corrente
				2 - Criar conta poupança
				3 - Efetuar deposito
				4 - Efetuar saque
				5 - Aplicar correcao
				6 - Cadastrar PIX
				7 - Efetuar PIX
				8 - Consultar saldo
				9 - Sair
				
				Digite uma opcao: """);
	}
	
	public static Conta encontraNumeroConta(int numero) {
		Conta contaEncontrada = null;
		
		//percorrando o array
		for(Conta i: contas) {
			if(i.getNumero() == numero)
				contaEncontrada = i;
		}
		
		return contaEncontrada;
	}
	
	public static Conta encontraCpfConta(String cpf) {
		Conta contaEncontrada = null;
		
		//percorrendo o array
		for(Conta i: contas) {
			if(i.getCpf().equals(cpf))
				contaEncontrada = i;
		}
		
		return contaEncontrada;
	}
	
	public static void criaContaCorrente(Scanner sc) {
		//gerando um numero para a conta
		Random rand = new Random();
		int numero = rand.nextInt(1000000);
		System.out.println("Número da Conta: " + numero);
		//lendo o nome
		System.out.println("Nome: ");
		String nome = sc.nextLine();
		//lendo o CPF
		System.out.println("CPF: ");
		String cpf = sc.nextLine();
		
		//criando um objeto da classe corrente
		Corrente novaConta = new Corrente(numero, nome, cpf, 0.0);
		contas.add(novaConta);
	}
	
	public static void criaContaPoupanca(Scanner sc) {
		//gerando um numero para a conta
		Random rand = new Random();
		int numero = rand.nextInt(1000000);
		System.out.println("Número da Conta: " + numero);
		//lendo o nome
		System.out.println("Nome: ");
		String nome = sc.nextLine();
		//lendo o CPF
		System.out.println("CPF: ");
		String cpf = sc.nextLine();
		
		//criando um objeto da classe corrente
		Poupanca novaConta = new Poupanca(numero, nome, cpf, 0.0);
		contas.add(novaConta);
	}
	
	public static void efetuaDeposito(Scanner sc) {
		//lendo numero da conta
		System.out.print("Digite o numero da conta: ");
		int numeroConta = sc.nextInt();
		//procurando a conta pelo numero
		Conta contaEncontrada = encontraNumeroConta(numeroConta);
		//caso nao encontre
		if(contaEncontrada == null) {
			System.out.println("Nao foi possivel encontrar uma conta com o numero informado.");
			return;
		}
		//caso encontre, ler valor do deposito
		System.out.print("Entre com o valor do deposito: ");
		double valor = sc.nextDouble();
		
		//realizando o deposito
		contaEncontrada.depositar(valor);
		
		System.out.println("Deposito realizado com sucesso!");
	}
	
	public static void efetuaSaque(Scanner sc) {
		//lendo numero da conta
		System.out.print("Digite o numero da conta: ");
		int numeroConta = sc.nextInt();
		//procurando a conta pelo numero
		Conta contaEncontrada = encontraNumeroConta(numeroConta);
		//caso nao encontre
		if(contaEncontrada == null) {
			System.out.println("Nao foi possivel encontrar uma conta com o numero informado.");
			return;
		}
		//caso encontre, ler valor do saque
		System.out.print("Entre com o valor do saque: ");
		double valor = sc.nextDouble();
		
		//verificando o saldo
		if(contaEncontrada.getSaldo() < valor) {
			System.out.println("Saldo insuficiente!");
		} else {
			contaEncontrada.sacar(valor);
			System.out.println("Saque realizado com sucesso!");
		}
	}
	
	public static void aplicaTaxa(Scanner sc) {
		//lendo taxa de correcao
		System.out.println("Informe a taxa de correcao (em porcentagem): ");
		float taxa = sc.nextFloat();
		
		//percorrendo o array list em busca de todas as contas poupanca
		for(Conta i: contas) {
			if(i instanceof Poupanca)
				((Poupanca) i).aplicaTaxa(taxa);
		}
		System.out.println("Taxa aplicada em todas as contas poupanca!");
	}
	
	public static void cadastraPix(Scanner sc) {
		//lendo o CPF do usuario
		System.out.println("Entre com CPF: ");
		String cpf = sc.nextLine();
		
		//procurando a conta pelo cpf
		Conta contaEncontrada = encontraCpfConta(cpf);
		
		//verifica se existe uma conta com o cpf informado
		if(contaEncontrada instanceof Corrente) {
			((Corrente) contaEncontrada).cadastraPix();
			System.out.println("PIX cadastrado com sucesso!");
		}

		else if(contaEncontrada == null){
			System.out.println("Não foi encontrada nenhuma conta com este Cpf");
		}

		 else {
			System.out.println("Contas poupanca nao podem realizar PIX");
		}
	}
	
	public static void efetuaPix(Scanner sc) {
		//lendo o numero da conta de origem
		System.out.println("Digite o numero da sua conta: ");
		int numero = sc.nextInt();
		sc.nextLine();
		
		//procurando a conta pelo numero
		Conta contaOrigem = encontraNumeroConta(numero);
		//caso nao encontre
		if(contaOrigem == null) {
			System.out.println("Nao foi possivel encontrar uma conta com o numero informado.");
			return;
		}
		
		//verificando se a conta de origem pode efetuar PIX
		if(contaOrigem instanceof Corrente) {
			if(((Corrente)contaOrigem).getPixAtivo() == false) {
				System.out.println("Sua conta precisa ativar transacoes por PIX!");
				System.out.println("Utilize a opção 6 no menu.");
				return;
			}
		}
		else {
			System.out.println("Somente contas corrente conseguem efetuar PIX.");
			return;
		}
		
		//lendo o cpf da conta destino
		System.out.println("Entre com o CPF da conta destino: ");
		String cpf = sc.nextLine();
		
		//procurando a conta destino pelo CPF
		Conta contaDestino = encontraCpfConta(cpf);
		//caso nao encontre
		if(contaDestino == null) {
			System.out.println("Nao foi possivel encontrar uma conta com o CPF informado.");
			return;
		}
		
		//verificando se a conta destino pode receber PIX
		if(contaDestino instanceof Corrente) {
			if(((Corrente)contaDestino).getPixAtivo() == false) {
				System.out.println("A conta destino nao tem PIX ativo.");
				return;
			}
		}
		else {
			System.out.println("Somente contas corrente conseguem receber PIX.");
			return;
		}
		
		//lendo o valor que o usuario ira transferir
		System.out.println("Valor a ser transferido: ");
		double valor = sc.nextDouble();
		
		//verificando se o valor eh menor que o saldo
		if(valor > contaOrigem.getSaldo()) {
			System.out.println("Saldo insuficiente!");
			return;
		}
		
		((Corrente)contaOrigem).efetuaPix(valor);
		((Corrente)contaDestino).recebePix(valor);
		System.out.println("PIX efetuado com sucesso!");
	}
	
	public static void consultaExtrato(Scanner sc) {
		//lendo numero da conta
		System.out.print("Digite o numero da conta: ");
		int numeroConta = sc.nextInt();
		//procurando a conta pelo numero
		Conta contaEncontrada = encontraNumeroConta(numeroConta);
		//caso nao encontre
		if(contaEncontrada == null) {
			System.out.println("Nao foi possivel encontrar uma conta com o numero informado.");
			return;
		}
		//caso encontre, exibir extrato
		System.out.println(contaEncontrada.consultaExtrato());
	}
}