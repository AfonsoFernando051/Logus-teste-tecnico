package Principal;

import java.util.Scanner;
import Controller.Posto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Models.Veiculo;
import Models.BombaDeCombustivel;
import Models.Combustivel;
import Models.Modelo;

/**
 * Classe principal que inicia o sistema de abastecimento de um posto de combustível.
 */
public class Main {
    /**
     * Método principal que inicia o programa.
     *
     * @param args Os argumentos da linha de comando (não utilizados neste programa).
     */
	public static void main(String[] args) {
		menuPrincipal();
	}
	
    /**
     * Exibe o menu principal e controla o fluxo do programa.
     */
	public static void menuPrincipal() {
		System.out.println("\nBem vindo ao sistema de abastecimento\n");

		Scanner ler = new Scanner(System.in);
		Posto postoCombustivel = new Posto();
		int option = 0;

		Combustivel gasolina = new Combustivel("GASOLINA", 2.90);
		Combustivel etanol = new Combustivel("ETANOL", 2.70);
		BombaDeCombustivel bombaDeGasolina = new BombaDeCombustivel(1, gasolina);
		BombaDeCombustivel bombaDeEtanol = new BombaDeCombustivel(2, etanol);

		postoCombustivel.cadastrarCombustivel(gasolina);
		postoCombustivel.cadastrarCombustivel(etanol);
		postoCombustivel.cadastrarBombaDeCombustivel(bombaDeGasolina);
		postoCombustivel.cadastrarBombaDeCombustivel(bombaDeEtanol);
		
		do {
			menu();
			option = ler.nextInt();
			
			switch (option) {
			case 1:
				postoCombustivel.lerViaCsv();
				break;
			case 2:
				for(int i = 0; i < postoCombustivel.veiculosParaAbastecer(); i++) {
					postoCombustivel.abastecer(i);
				}
				break;
			case 3:				
				postoCombustivel.informacoes();
				break;
			case 4:
				postoCombustivel.cadastrarVeiculoIndividualmente();
				break;
			case 5:
				postoCombustivel.cadastrarCombustivelEBomba();
				break;
			case 6:
			    int posicaoFinal = postoCombustivel.veiculosParaAbastecer() - 1;
			    
			    for (int i = posicaoFinal; i > 0; i--) {
			        postoCombustivel.abastecer(i);
			    }
				break;
			case 7:
				System.out.println("\nSaindo...\n");
				option++;
				break;
			default:
				System.out.println("\nOpcao invalida, tente novamente.\n");
				option = 9;
				break;
			}
		}while(option != 8 || option == 9);
	}
	
    /**
     * Exibe o menu de opções para o usuário.
     */
	public static void menu() {
		System.out.println("\nDigite uma opcao: \n"
				+ "\n1 - Cadastrar veiculos em fila para abastecimento via CSV;\n"
				+ "\n2 - Abastecer veiculos cadastrados;\n"
				+ "\n3 - Gerar relatorio geral;\n"
				+ "\n4 - Cadastrar veiculos em fila para abastecimento manualmente;\n"
				+ "\n5 - Cadastrar novo tipo de combustivel;\n"
				+ "\n6 - Abastecer veiculos na ordem inversa;\n"
				+ "\n7 - Sair;\n");
	}
}