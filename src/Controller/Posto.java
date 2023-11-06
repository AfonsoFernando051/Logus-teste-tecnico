package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Models.BombaDeCombustivel;
import Models.Combustivel;
import Models.LogInformacoesAbastecimento;
import Models.Modelo;
import Models.Veiculo;

public class Posto {
	
	Scanner ler = new Scanner(System.in);
	
	private double totalAbastecidoBombasGasolina = 0;
	private double totalAbastecidoBombasEtanol = 0;
	private ArrayList<Modelo> modelos = new ArrayList<Modelo>();
	private ArrayList<Veiculo> veiculoAbastecer = new ArrayList<Veiculo>();
	private ArrayList<LogInformacoesAbastecimento> veiculosAbastecidos = new ArrayList<LogInformacoesAbastecimento>();
	private ArrayList<Combustivel> combustiveis = new ArrayList<Combustivel>();
	private ArrayList<BombaDeCombustivel> bombasDeCombustivel = new ArrayList<BombaDeCombustivel>();
    private int idNextBomba = 2;

    /**
     * Lê informações de veículos e modelos a partir de arquivos CSV.
     */
	public void lerViaCsv() {
        String modelosCSV = "/home/fernando-afonso/ArquivosJava/modelos.csv";
        String veiculosCSV = "/home/fernando-afonso/ArquivosJava/veiculos.csv";
        int posicaoNaFila = 0;

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
                  cadastrarVeiculo(veiculo);
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
                  cadastrarModelo(modelo);
              }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nVeiculos cadastrados com sucesso!\n");
        lerVeiculos();
	}
	
	public void cadastrarModelo(Modelo modelo) {
		modelos.add(modelo);
	}
	
	public void cadastrarVeiculo(Veiculo veiculo) {
		veiculoAbastecer.add(veiculo);
	}
	
	public void cadastrarCombustivel(Combustivel nomeCombustivel) {
		combustiveis.add(nomeCombustivel);
	}
	
	public void cadastrarBombaDeCombustivel(BombaDeCombustivel nomeBomba) {
		bombasDeCombustivel.add(nomeBomba);
	}
	
	public void lerModelos() {
	    for (Modelo modelo : modelos) {
	        System.out.println(modelo);
	    }
	}
	
	public void lerVeiculos() {
	    for (Veiculo carro : veiculoAbastecer) {
	        System.out.println(carro);
	    }
	}
	
	public void lerCombustiveis() {
	    for (Combustivel combustivel : combustiveis) {
	        System.out.println(combustivel.getNomeCombustivel()+";");
	    }
	}
	
	public void lerBombas() {
	    for (BombaDeCombustivel bomba : bombasDeCombustivel) {
	        System.out.println(bomba.getCombustivel()+";");
	    }
	}
	
	public int veiculosParaAbastecer() {
		return veiculoAbastecer.size();
	}

    /**
     * Define o total abastecido para gasolina.
     *
     * @param nomeCombustivel O nome do combustível.
     * @param qtd A quantidade de combustível abastecido.
     */
	public void setTotalCombustivelGasolina(String nomeCombustivel, double qtd) {
	    for (BombaDeCombustivel bomba : bombasDeCombustivel) {
	    	if(nomesSaoIguais(bomba.getCombustivel().getNomeCombustivel(), nomeCombustivel)) {
	    		this.totalAbastecidoBombasGasolina += qtd; 
	    	}
	    }
	}
	
    /**
     * Define o total abastecido para etanol.
     *
     * @param nomeCombustivel O nome do combustível.
     * @param qtd A quantidade de combustível abastecido.
     */
	public void setTotalCombustivelEtanol(String nomeCombustivel, double qtd) {
	    for (BombaDeCombustivel bomba : bombasDeCombustivel) {
	    	if(nomesSaoIguais(bomba.getCombustivel().getNomeCombustivel(), nomeCombustivel)) {
	    		this.totalAbastecidoBombasEtanol += qtd; 
	    	}
	    }
	}
	
	public void getTotalCombustivelGasolina() {
		System.out.println("Total geral abastecido de GASOLINA e " + this.totalAbastecidoBombasGasolina + " litros");
	}
	
	public void getTotalCombustivelEtanol() {
		System.out.println("Total geral abastecido de ETANOL e " + this.totalAbastecidoBombasEtanol+ " litros");
	}
	
    /**
     * Realiza o abastecimento de um veículo.
     *
     * @param posicao A posição do veículo na fila de abastecimento.
     */
	public void abastecer(int posicao) {
		LogInformacoesAbastecimento modeloAbastecimento = new LogInformacoesAbastecimento();
		Veiculo carro = veiculoAbastecer.get(posicao);
		String tipoCombustivel = "";
	
		System.out.println("\n--Abastecimento--\n");
		System.out.println("Modelo: "+ carro.getNomeModelo());
		System.out.println("Placa: "+ carro.getPlaca());
		
	   	 for (Modelo modelo : modelos) {	
			 if(nomesSaoIguais(modelo.getModelo(), carro.getNomeModelo())) {				 
				 modeloAbastecimento.setNomeVeiculo(carro.getNomeModelo());
				 modeloAbastecimento.setPlaca(carro.getPlaca());
				 modeloAbastecimento.setQtdAbastecida(modelo.getCapacidadeTanque());

				 if(modelo.getConsumoEtanol() == 0) {
					 tipoCombustivel = "GASOLINA";
				 }else{
					 tipoCombustivel = definirCombustivel();
				 }
				 
				 modeloAbastecimento.setTipoCombustivel(tipoCombustivel);

				 if(isGasoline(tipoCombustivel)) {
					 BombaDeCombustivel gasolina = bombasDeCombustivel.get(0);
					 gasolina.setTotalAbastecido(modelo.getCapacidadeTanque());
					 setTotalCombustivelGasolina(tipoCombustivel, modelo.getCapacidadeTanque());
					 veiculosAbastecidos.add(modeloAbastecimento);
					 informacoes();
					 continue;
				 }else {
					 BombaDeCombustivel etanol = bombasDeCombustivel.get(1);
					 etanol.setTotalAbastecido(modelo.getCapacidadeTanque());
					 setTotalCombustivelEtanol(tipoCombustivel, modelo.getCapacidadeTanque());
					 veiculosAbastecidos.add(modeloAbastecimento);
					 informacoes();
					 continue;
				 }
			 }
	    }
	}
	
	public void lerLogAbastecimento() {
	    for (LogInformacoesAbastecimento informacao: veiculosAbastecidos) {
	        System.out.println(informacao);
	    }
	}	
	
	public boolean validarTipoCombustivel(String tipoCombustivel) {
		return !tipoCombustivel.equalsIgnoreCase("gasolina") && !tipoCombustivel.equalsIgnoreCase("etanol");
	}
	
	public boolean nomesSaoIguais(String nameOne, String nameTwo) {
		return nameOne.equalsIgnoreCase(nameTwo);
	}
	
	public boolean isGasoline(String tipoCombustivel){
		return tipoCombustivel.equalsIgnoreCase("gasolina");
	}
	
    /**
     * Define o tipo de combustível e valida antes de retornar.
     *
    */
	public String definirCombustivel() {
		String tipoCombustivel;
		do{
			System.out.println("\nDeseja inserir qual tipo de combustivel?");
			System.out.println("\nOpcoes:\n");
			lerCombustiveis();
			
			tipoCombustivel = ler.next();
			
			if(validarTipoCombustivel(tipoCombustivel)){
				System.out.println("\nCombustivel invalido, tente novamente.\n");
			}			
			
		}while(validarTipoCombustivel(tipoCombustivel)); 
		
		return tipoCombustivel.toUpperCase();				

	}
	
    /**
     * Exibe informações sobre os veículos e sobre abastecimento.
     *
    */
	public void informacoes() {
		lerLogAbastecimento();
		BombaDeCombustivel bombaDeGasolina = bombasDeCombustivel.get(0);
		BombaDeCombustivel bombaDeEtanol = bombasDeCombustivel.get(1);
		
		System.out.println("\n");

		System.out.println("Resultado da simulacao");
		System.out.println("----------------------");
		bombaDeGasolina.getTotalAbastecido();
		bombaDeEtanol.getTotalAbastecido();
		getTotalCombustivelGasolina();
		getTotalCombustivelEtanol();
		
		System.out.println("\n");
		
		System.out.println("Informacoes adicionais");
		Combustivel gasolina = combustiveis.get(0);
		Combustivel etanol = combustiveis.get(1);
		gasolina.getPrecoCombustivel();
		etanol.getPrecoCombustivel();
		
		System.out.println("\n");

		System.out.println("Velocidade de abastecimento da bomba de gasolina: 10 litros / minuto");
		System.out.println("Velocidade de abastecimento da bomba de álcool: 12 litros /minuto");
	}

    /**
     * Cadastra um veículo individualmente e o insere na fila de abastecimento.
     *
    */
	public void cadastrarVeiculoIndividualmente(){
		System.out.println("\nPara adicionar um novo veiculo na base de dados, digite o modelo: \n");
		String nomeModelo = ler.next();
		String placa;
		int posicaoNaFila = veiculosParaAbastecer();
		
		do {					
			System.out.println("\nAgora digite a placa: (Modelo: AAA-1234 ou AAA1A23)\n");
			placa = ler.next();
			if(placaIncorreta(placa)) {
				System.out.println("\nFormatacao invalida, digite novamente.\n");
			}
		}while(placaIncorreta(placa));
		
        Veiculo veiculo = new Veiculo(posicaoNaFila+1, nomeModelo, placa.toUpperCase());
        cadastrarVeiculo(veiculo);			
        
		System.out.println("\nVeiculo cadastrado com sucesso, agora vamos cadastrar as especificacoes do modelo.\n");

		System.out.println("\nPara adicionar as especificacoes do modelo " + nomeModelo  +" na base de dados,"
				+ " digite o consumo em etanol:	(Digite 0 se o carro nao pode ser abastecido com etanol)\n");
		double consumoEtanol = ler.nextDouble();
		System.out.println("\nAgora digite o consumo em gasolina: \n");
		double consumoGasolina = ler.nextDouble();
		System.out.println("\nPor fim, por gentileza insira a capacidade de abastecimento do tanque: \n");
		double capacidadeTanque = ler.nextDouble();
        Modelo modelo = new Modelo(nomeModelo, consumoEtanol, consumoGasolina, capacidadeTanque);
        
        cadastrarModelo(modelo);
        lerVeiculos();
        lerModelos();
	}
	
    /**
     * Valida se a placa segue os padrões corretos.
     *
    */
	public boolean placaIncorreta(String placa) {
        String placaAntiga= "^[A-Z]{3}-\\d{4}$";
        String placaMercosul = "^[A-Z]{3}\\d[A-Z]\\d{2}$";

        return !(placa.matches(placaAntiga) || placa.matches(placaMercosul));
	}
	
    /**
     * Cadastra um novo tipo de combustível e bomba, além de inserí-los nas respectivas listas.
     *
    */
	public void cadastrarCombustivelEBomba() {
		System.out.println("\nPara adicionar um novo tipo de combustivel, por gentileza insira o nome do tipo: \n");
		String nomeCombustivel = ler.next();
		System.out.println("\nAgora insira o valor: .\n");
		double preco = ler.nextDouble();

		Combustivel novoCombustivel = new Combustivel(nomeCombustivel.toUpperCase(), preco);
		cadastrarCombustivel(novoCombustivel);

		System.out.println("\nCombustivel e bomba cadastrados com sucesso.\n");
		BombaDeCombustivel novaBomba = new BombaDeCombustivel(this.idNextBomba++, novoCombustivel);
		cadastrarBombaDeCombustivel(novaBomba);
	}
}
