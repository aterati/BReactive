package com.rv.breactive.rerpository;

import com.rv.breactive.model.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockPrice, Long> {
}
