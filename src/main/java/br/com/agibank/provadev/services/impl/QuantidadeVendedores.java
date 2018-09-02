package br.com.agibank.provadev.services.impl;

import br.com.agibank.provadev.model.Cliente;
import br.com.agibank.provadev.model.Venda;
import br.com.agibank.provadev.model.Vendedor;
import br.com.agibank.provadev.services.ItemRelatorio;

public class QuantidadeVendedores implements ItemRelatorio {

	private int qtde = 0;
	
	@Override
	public void processar(Vendedor v) {
		qtde++;
	}

	@Override
	public void processar(Cliente c) {
		
	}

	@Override
	public void processar(Venda v) {
			
	}

	@Override
	public String getSaida() {
		return "Vendedores: " + qtde;
	}

	
	
}
