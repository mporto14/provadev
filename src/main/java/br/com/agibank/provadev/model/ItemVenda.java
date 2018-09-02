package br.com.agibank.provadev.model;

public class ItemVenda {

	private String id;
	
	private int quantidade;
	
	private double preco;
	
	public ItemVenda(String id, int quantidade, double preco) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public double getValorItem() { 
		return getPreco() * getQuantidade();
	}
	
}
