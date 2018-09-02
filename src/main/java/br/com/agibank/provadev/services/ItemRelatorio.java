package br.com.agibank.provadev.services;

import br.com.agibank.provadev.model.Cliente;
import br.com.agibank.provadev.model.Venda;
import br.com.agibank.provadev.model.Vendedor;

public interface ItemRelatorio { 

	public void processar(Vendedor v);
	
	public void processar(Cliente c);
	
	public void processar(Venda v);
	
	public String getSaida();
	
}
