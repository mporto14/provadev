package br.com.agibank.provadev.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import br.com.agibank.provadev.model.Cliente;
import br.com.agibank.provadev.model.ItemVenda;
import br.com.agibank.provadev.model.Venda;
import br.com.agibank.provadev.model.Vendedor;

@Service
@PropertySource("classpath:application.properties")
public class Relatorio {
	
	@Value("${entrada.caminho}")
	private String caminhoEntrada;
	
	@Value("${saida.caminho}")
	private String caminhoSaida;
	
	public void setCaminhoEntrada(String caminhoEntrada) {
		this.caminhoEntrada = caminhoEntrada;
	}
	
	public void setCaminhoSaida(String caminhoSaida) {
		this.caminhoSaida = caminhoSaida;
	}

	private static final Logger log = LogManager.getLogger(Relatorio.class);
	
	private List<ItemRelatorio> itens;

	public List<ItemRelatorio> getItens() {
		return itens;
	}

	public void setItens(List<ItemRelatorio> itens) {
		this.itens = itens;
	}

	private void processarArquivo(String arquivo) throws IOException {
		Stream<String> stream = Files.lines(Paths.get(arquivo));
		Iterator<String> it = stream.iterator();
		while (it.hasNext()) {
			String[] tokens = it.next().split("ç");
			switch (tokens[0]) {
			case "001":
				Vendedor v = new Vendedor(tokens[1], tokens[2], Double.parseDouble(tokens[3]));
				itens.forEach((pr) -> pr.processar(v));
				break;
			case "002":
				Cliente c = new Cliente(tokens[1], tokens[2], tokens[3]);
				itens.forEach((pr) -> pr.processar(c));
				break;
			case "003":
				Venda vd = new Venda(tokens[1], tokens[3]);
				String[] listaItens = tokens[2].substring(1, tokens[2].length() - 1).split(",");
				for (String itemVenda : listaItens) {
					String[] ivTokens = itemVenda.split("-");
					vd.incluirItemVenda(
							new ItemVenda(ivTokens[0], Integer.parseInt(ivTokens[1]), Double.parseDouble(ivTokens[2])));
				}
				itens.forEach((pr) -> pr.processar(vd));
			}
		}
		stream.close();
	}

	public void executar() throws IOException { 
		String basePath = System.getenv("HOMEPATH");
		Path dir = Paths.get(basePath + caminhoEntrada);
		DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.dat");
		for (Path arquivo : stream) { 
			try {
				processarArquivo( arquivo.toString() );
			} catch (Exception e) {
				log.error("Erro ao processar arquivo", arquivo.toString(), e);
			}
		}
		stream.close();
		
		String nomeArquivoSaida = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss___S").format(new Date());
		nomeArquivoSaida = basePath + caminhoSaida + "/" + nomeArquivoSaida + ".done.dat";
		BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivoSaida)); 
		for (ItemRelatorio p : itens) { 
			writer.write(p.getSaida());
			writer.newLine();
		}
		writer.close();
	}

}
