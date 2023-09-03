package br.com.alura.tabelaFip;

import br.com.alura.tabelaFip.main.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabelaFipApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TabelaFipApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		var principal = new Principal();
		principal.exibirMenu();
	}
}
