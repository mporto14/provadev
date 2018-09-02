package br.com.agibank.provadev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import br.com.agibank.provadev.services.Relatorio;

@SpringBootApplication
@ImportResource({"classpath:relatorio.xml"})
public class App implements CommandLineRunner {

	@Autowired
	private Relatorio relatorio;
	
	@Override
	public void run(String... args) throws Exception { 
		relatorio.executar();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
