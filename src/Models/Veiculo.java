package Models;

public class Veiculo{
	private int id;
	private String placa;
	private String nomeModelo;

	public Veiculo(int id, String nomeModelo, String placa) {
		this.id = id;
		this.nomeModelo = nomeModelo;
		this.placa = placa;
	}

	public String getNomeModelo() {
		return nomeModelo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setNomeModelo(String nomeModelo) {
		this.nomeModelo = nomeModelo;
	}
	@Override
	public String toString() {
		return "Posicao do Veiculo na fila:  " + id + ", modelo: " + nomeModelo + ", placa: " + placa  + ".";
	}
}
