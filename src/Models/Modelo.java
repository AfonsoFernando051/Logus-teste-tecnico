package Models;

public class Modelo {
	protected String modelo;
	protected double consumoEtanol;
	protected double consumoGasolina;
	protected double capacidadeTanque;
	
	public Modelo(String modelo, double consumoEtanol, double consumoGasolina, double capacidadeTanque) {
		this.modelo = modelo;
		this.consumoEtanol = consumoEtanol;
		this.consumoGasolina = consumoGasolina;
		this.capacidadeTanque = capacidadeTanque;
	}
	
	public double getConsumoEtanol() {
		return consumoEtanol;
	}

	public String getModelo() {
		return modelo;
	}

	public double getCapacidadeTanque() {
		return capacidadeTanque;
	}

	@Override
	public String toString() {
		return "Modelo: : " + modelo + ", consumo Etanol: " + consumoEtanol + ", consumo Gasolina: " + consumoGasolina
				+ ", capacidade Tanque: " + capacidadeTanque + ".";
	}
	
	
}
