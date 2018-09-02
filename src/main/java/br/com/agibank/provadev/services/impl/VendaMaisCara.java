package br.com.agibank.provadev.services.impl;

import br.com.agibank.provadev.model.Cliente;
import br.com.agibank.provadev.model.Venda;
import br.com.agibank.provadev.model.Vendedor;
import br.com.agibank.provadev.services.ItemRelatorio;

public class VendaMaisCara implements ItemRelatorio {

	private Venda maisCara;
	
	@Override
	public void processar(Vendedor v) {
		
	}

	@Override
	public void processar(Cliente c) {
		
	}

	@Override
	public void processar(Venda v) {
		if (maisCara == null) { 
			maisCara = v;
		} else { 
			if (v.getTotal() > maisCara.getTotal() ) { 
				maisCara = v;
			}
		}
	}

	@Override
	public String getSaida() {
		if (maisCara == null) { 
			return "Sem registro de vendas";
		}
		return "Mais cara: " + maisCara.getId();
	}

	
}
