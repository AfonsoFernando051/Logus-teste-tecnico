package Models;

public class Combustivel {
	private String nomeCombustivel;
	private double precoCombustivel;
	
	public Combustivel(String nomeCombustivel, double precoCombustivel) {
		this.nomeCombustivel = nomeCombustivel;
		this.precoCombustivel = precoCombustivel;
	}

	public String getNomeCombustivel() {
		return nomeCombustivel;
	}
	
	public void getPrecoCombustivel() {
		System.out.println("O preco do litro do(a) " +  nomeCombustivel + " e R$" + precoCombustivel);
	}

	@Override
	public String toString() {
		return "Combustivel: " + nomeCombustivel;
	}
}
