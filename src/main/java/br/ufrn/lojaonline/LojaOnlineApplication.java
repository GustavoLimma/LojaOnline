package br.ufrn.lojaonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
public class LojaOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaOnlineApplication.class, args);
	}

}
