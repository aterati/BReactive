package com.rv.breactive;

import com.rv.breactive.model.StockPrice;
import com.rv.breactive.rerpository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class BReactiveServerApplication {

	@Autowired
	StockRepository stockRepository;

	public static void main(String[] args) {
		SpringApplication.run(BReactiveServerApplication.class, args);
	}

	@Bean
	public void loadData(){
		stockRepository.save(new StockPrice("Symbo", 200.0, LocalDateTime.now()));
		stockRepository.save(new StockPrice("Symbol", 200.0, LocalDateTime.now()));
		stockRepository.save(new StockPrice("Symbols", 200.0, LocalDateTime.now()));
	}

}
