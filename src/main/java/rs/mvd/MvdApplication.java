package rs.mvd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MvdApplication {//extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MvdApplication.class, args);
//		new MvdApplication().configure(new SpringApplicationBuilder(MvdApplication.class)).run(args);
	}
}
