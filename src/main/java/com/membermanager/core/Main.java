package com.membermanager.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@EnableJpaRepositories
public class Main{


	public static void main(String[] args) {

		SpringApplication.run(Main.class, args);


	}



}
