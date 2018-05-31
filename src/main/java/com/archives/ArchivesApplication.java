package com.archives;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.px.archives.*.dao")
public class ArchivesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArchivesApplication.class, args);
	}
}
