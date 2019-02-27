package cn.wxj.face.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wxjason
 */
@EnableAsync
@EnableEurekaClient
@ComponentScan(basePackages={"cn.wxj"})
@MapperScan("cn.wxj.**.mapper")
@SpringBootApplication
public class FaceAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaceAdminApplication.class, args);
	}

}
