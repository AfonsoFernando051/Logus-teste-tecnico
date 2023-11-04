package Models;

public class BombaDeCombustivel {
	
	private int idBomba;
	private Combustivel combustivel;
	private double totalAbastecido = 0;

	public BombaDeCombustivel(int idBomba, Combustivel combustivel) {
		this.idBomba = idBomba;
		this.combustivel = combustivel;
	}
	
	public void setTotalAbastecido(double totalAbastecido) {
		this.totalAbastecido += totalAbastecido;
	}

	public void getTotalAbastecido() {
		System.out.println("Total abastecido na bomba " + idBomba + " (" + combustivel.getNomeCombustivel() + "): " + this.totalAbastecido + " litros");
	}

	public int getIdBomba() {
		return idBomba;
	}

	public Combustivel getCombustivel() {
		return combustivel;
	}

}
