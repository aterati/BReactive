package com.rv.breactive.controller;

import com.rv.breactive.model.StockPrice;
import com.rv.breactive.rerpository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
public class BigServerController {

    @Autowired
    StockRepository stockRepository;

    @GetMapping(value="/getStocks", produces=(MediaType.TEXT_EVENT_STREAM_VALUE))
    public Flux<StockPrice> getStockPrices(){
        Flux<Long> interval =  Flux.interval(Duration.ofSeconds(1));
        return Flux.zip(interval,Flux.fromStream(stockRepository.findAll().stream())).map((t)->t.getT2());
    }

    @GetMapping("/getStock/{id}")
    public Mono<StockPrice> getStockPrice(@PathVariable("id") String id){
        return Mono.just(stockRepository.findById(1L).get());
    }

}
