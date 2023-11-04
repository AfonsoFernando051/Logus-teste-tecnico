package Models;

public class LogInformacoesAbastecimento {
	
	private String nomeVeiculo;
	private String placa;
	private double qtdAbastecida;
	private String tipoCombustivel;

	public String getNomeVeiculo() {
		return nomeVeiculo;
	}

	public void setNomeVeiculo(String nomeVeiculo) {
		this.nomeVeiculo = nomeVeiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public double getQtdAbastecida() {
		return qtdAbastecida;
	}

	public void setQtdAbastecida(double qtdAbastecida) {
		this.qtdAbastecida = qtdAbastecida;
	}

	public String getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(String tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}
	
	@Override
	public String toString() {
		return "Veiculo modelo " + nomeVeiculo + ", placa "
				+ placa + " foi abastecido com " + qtdAbastecida + " litros de " + tipoCombustivel + ".";
	}
}
