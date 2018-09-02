package br.com.agibank.provadev.model;

import java.util.ArrayList;
import java.util.List;

public class Venda {

	private String id;
	
	private List<ItemVenda> itens;
	
	private String nomeVendedor;
	
	public Venda(String id, List<ItemVenda> itens, String nomeVendedor) {
		super();
		this.id = id;
		this.itens = itens;
		this.nomeVendedor = nomeVendedor;
	}
	
	public Venda(String id, String nomeVendedor) {
		super();
		this.id = id;
		this.nomeVendedor = nomeVendedor;
	}



	public void incluirItemVenda(ItemVenda item) { 
		if (itens == null) { 
			itens = new ArrayList<ItemVenda>();
		}
		itens.add(item);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public double getTotal() { 
		double ret = 0.0;
		for (ItemVenda it : getItens()) { 
			ret += it.getValorItem();
		}
		return ret;
	}
	
}
