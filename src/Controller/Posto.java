package Controller;

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
	private double precoGasolina = 2.90;
	private double precoEtanol= 2.70;
	
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

	public void getPrecoEtanol() {
		System.out.println("O preco do litro do ETANOL e R$" + precoEtanol);
	}

	public void setTotalCombustivelGasolina(String nomeCombustivel, double qtd) {
	    for (BombaDeCombustivel bomba : bombasDeCombustivel) {
	    	if(nomesSaoIguais(bomba.getCombustivel().getNomeCombustivel(), nomeCombustivel)) {
	    		this.totalAbastecidoBombasGasolina += qtd; 
	    	}
	    }
	}
	
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
			
			return tipoCombustivel.toUpperCase();
			
		}while(validarTipoCombustivel(tipoCombustivel)); 
	}
	
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
	
}
