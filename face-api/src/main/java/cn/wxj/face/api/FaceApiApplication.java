package cn.wxj.face.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wxjason
 */
@ComponentScan(basePackages={"cn.wxj"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FaceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaceApiApplication.class, args);
	}

}
