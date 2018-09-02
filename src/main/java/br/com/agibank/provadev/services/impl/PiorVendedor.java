package br.com.agibank.provadev.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.agibank.provadev.model.Cliente;
import br.com.agibank.provadev.model.Venda;
import br.com.agibank.provadev.model.Vendedor;
import br.com.agibank.provadev.services.ItemRelatorio;

public class PiorVendedor implements ItemRelatorio {

	private List<Vendedor> vendedores = new ArrayList<>();
	
	private Map<String, Double> vendasPorVendedor = new HashMap<String, Double>();
	
	@Override
	public void processar(Vendedor v) {
		vendedores.add(v);
	}

	@Override
	public void processar(Cliente c) {
		
	}

	@Override
	public void processar(Venda v) { 
		vendasPorVendedor.merge(v.getNomeVendedor(), v.getTotal(), Double::sum);
	}

	@Override
	public String getSaida() { 
		if (vendasPorVendedor.size() == 0) { 
			return "Sem registro de vendas";
		}
		String vendedor = vendasPorVendedor.entrySet().stream().min((a, b) -> Double.compare(a.getValue(), b.getValue())).map(Map.Entry::getKey).get();
		Vendedor v = vendedores.stream().filter( el -> el.getNome().equals(vendedor) ).findFirst().orElseGet(() -> new Vendedor("[NÃO LOCALIZADO]", vendedor, 0.0));
		return "Pior Vendedor: " + v.getNome() + " (" + v.getCpf() + ")";
	}

}
