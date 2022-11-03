package fr.phonetastik;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import fr.phonetastik.utils.StorageService;

@SpringBootApplication
@ComponentScan("fr.phonetastik")
public class PhonetastikApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Resource
	StorageService storageService;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PhonetastikApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PhonetastikApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}

}
