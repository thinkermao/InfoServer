package net.hashcoding.code.scuinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.hashcoding.code.scuinfo.domain.mapper")
public class MainApplication {
    public static void main(String [] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
