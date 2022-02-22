package com.rv.breactive.webClient;

import com.rv.breactive.model.StockPrice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
public class StockWebClient {

    WebClient client = WebClient.create("http://localhost:8080");

    @GetMapping("/getStockPrice")
    public void getStockPrice(){
        Mono<StockPrice> stockPrice = client.get().uri("/getStock/{id}", 1)
                .retrieve().bodyToMono(StockPrice.class);
        stockPrice.subscribe(i-> System.out.println("Stock: "+ i.getSymbol() + ","+ i.getPrice()+","+ i.getTime()));
    }

    @GetMapping("/getStockPrices")
    public void getStockPrices(){
        Flux<StockPrice> stockPrices = client.get().uri("/getStocks")
                .retrieve().bodyToFlux(StockPrice.class);
        stockPrices.subscribe(i-> System.out.println("Stock ["+i.getSymbol() +","+ i.getPrice()+","+ i.getTime()+" ]"));
    }

}
