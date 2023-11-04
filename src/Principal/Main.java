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

public class Main {

	public static void main(String[] args) {
		System.out.println("\nBem vindo ao sistema de abastecimento\n");

		Scanner ler = new Scanner(System.in);
		Posto postoCombustivel = new Posto();
		int option = 0;
        int posicaoNaFila = 0;
        int idNextBomba = 2;

		Combustivel gasolina = new Combustivel("GASOLINA", 2.90);
		Combustivel etanol = new Combustivel("ETANOL", 2.70);
		BombaDeCombustivel bombaDeGasolina = new BombaDeCombustivel(1, gasolina);
		BombaDeCombustivel bombaDeEtanol = new BombaDeCombustivel(2, etanol);

		postoCombustivel.cadastrarCombustivel(gasolina);
		postoCombustivel.cadastrarCombustivel(etanol);
		postoCombustivel.cadastrarBombaDeCombustivel(bombaDeGasolina);
		postoCombustivel.cadastrarBombaDeCombustivel(bombaDeEtanol);
		do {
			System.out.println("\nDigite uma opcao: \n"
					+ "\n1 - Cadastrar veiculos em fila para abastecimento via CSV;\n"
					+ "\n2 - Abastecer veiculos cadastrados;\n"
					+ "\n3 - Gerar relatorio geral;\n"
					+ "\n4 - Cadastrar veiculos em fila para abastecimento manualmente;\n"
					+ "\n5 - Cadastrar novo tipo de combustivel;\n"
					+ "\n6 - Abastecer veiculos na ordem inversa;\n"
					+ "\n7 - Sair;\n");
			option = ler.nextInt();
			
			switch (option) {
			case 1:
		        String modelosCSV = "/home/fernando-afonso/ArquivosJava/modelos.csv";
		        String veiculosCSV = "/home/fernando-afonso/ArquivosJava/veiculos.csv";

		        try (BufferedReader br = new BufferedReader(new FileReader(veiculosCSV))) {
		            String line;
		            boolean firstLine = true;
		            while ((line = br.readLine()) != null) {
		                if (firstLine) {
		                    firstLine = false;
		                    continue;
		                }
		                String[] values = line.split(";");

		                String placa = values[0];
		                String modelo  = values[1];

		                posicaoNaFila++;
		                  Veiculo veiculo = new Veiculo(posicaoNaFila, placa, modelo);
		                  postoCombustivel.cadastrarVeiculo(veiculo);
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        
		        try (BufferedReader br = new BufferedReader(new FileReader(modelosCSV))) {
		        	  String line;
		              boolean firstLine = true;
		              double consumoEtanol;
		              double consumoGasolina;
		              double capacidadeTanque;
		              while ((line = br.readLine()) != null) {
		            	  if (firstLine) {
		                      firstLine = false;
		                      continue;
		                  }
		            	  
		                  String[] values = line.split(",");
		                  String modeloNome = values[0];
		                  
		                  if(values[1] != "") {
		                	  consumoEtanol = Double.parseDouble(values[1]);		                	  
		                  }else {
		                	  consumoEtanol = 0;
		                  }
		                  
		                  consumoGasolina = Double.parseDouble(values[2]);
		                  capacidadeTanque = Double.parseDouble(values[3]);
		                  
		                  Modelo modelo = new Modelo(modeloNome, consumoEtanol, consumoGasolina, capacidadeTanque);
		                  postoCombustivel.cadastrarModelo(modelo);
		              }

		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        
		        System.out.println("\nVeiculos cadastrados com sucesso!\n");

				break;
			case 2:
				for(int i = 0; i < postoCombustivel.veiculosParaAbastecer(); i++) {
					postoCombustivel.abastecer(i);
				}
				break;
			case 3:
				System.out.println("Resultado da simulacao");
				System.out.println("----------------------");
				postoCombustivel.lerLogAbastecimento();
				
				System.out.println("\n");
	
				System.out.println("Resultado da simulacao");
				System.out.println("----------------------");
				bombaDeGasolina.getTotalAbastecido();
				bombaDeEtanol.getTotalAbastecido();
				postoCombustivel.getTotalCombustivelGasolina();
				postoCombustivel.getTotalCombustivelEtanol();
				System.out.println("\n");
				
				System.out.println("Informacoes adicionais");
				gasolina.getPrecoCombustivel();
				etanol.getPrecoCombustivel();
				
				System.out.println("\n");

				System.out.println("Velocidade de abastecimento da bomba de gasolina: 10 litros / minuto");
				System.out.println("Velocidade de abastecimento da bomba de álcool: 12 litros /minuto");

				break;
			case 4:
				System.out.println("\nPara adicionar um novo veiculo na base de dados, digite o modelo: \n");
				String nomeModelo = ler.next();
				String placa;
				
				do {					
					System.out.println("\nAgora digite a placa: (Modelo: AAA-1234 ou AAA1A23)\n");
					placa = ler.next();
					if(placaIncorreta(placa)) {
						System.out.println("\nFormatacao invalida, digite novamente.\n");
					}
				}while(placaIncorreta(placa));
				
                Veiculo veiculo = new Veiculo(posicaoNaFila+1, nomeModelo, placa.toUpperCase());
                postoCombustivel.cadastrarVeiculo(veiculo);			
                
				System.out.println("\nVeiculo cadastrado com sucesso, agora vamos cadastrar as especificacoes do modelo.\n");

				System.out.println("\nPara adicionar as especificacoes do modelo " + nomeModelo  +" na base de dados,"
						+ " digite o consumo em etanol:	(Digite 0 se o carro nao pode ser abastecido com etanol)\n");
				double consumoEtanol = ler.nextDouble();
				System.out.println("\nAgora digite o consumo em gasolina: \n");
				double consumoGasolina = ler.nextDouble();
				System.out.println("\nPor fim, por gentileza insira a capacidade de abastecimento do tanque: \n");
				double capacidadeTanque = ler.nextDouble();
                Modelo modelo = new Modelo(nomeModelo, consumoEtanol, consumoGasolina, capacidadeTanque);
                
                postoCombustivel.cadastrarModelo(modelo);
                postoCombustivel.lerVeiculos();
                postoCombustivel.lerModelos();
				break;
			case 5:
				System.out.println("\nPara adicionar um novo tipo de combustivel, por gentileza insira o nome do tipo: \n");
				String nomeCombustivel = ler.next();
				System.out.println("\nAgora insira o valor: .\n");
				double preco = ler.nextDouble();
				idNextBomba = idNextBomba++;

				Combustivel novoCombustivel = new Combustivel(nomeCombustivel.toUpperCase(), preco);
				postoCombustivel.cadastrarCombustivel(novoCombustivel);

				System.out.println("\nCombustivel e bomba cadastrados com sucesso.\n");
				BombaDeCombustivel novaBomba = new BombaDeCombustivel(idNextBomba, novoCombustivel);
				postoCombustivel.cadastrarBombaDeCombustivel(novaBomba);

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
	
	public static boolean placaIncorreta(String placa) {
        String placaAntiga= "^[A-Z]{3}-\\d{4}$";
        String placaMercosul = "^[A-Z]{3}\\d[A-Z]\\d{2}$";

        return !(placa.matches(placaAntiga) || placa.matches(placaMercosul));
	}
}
