package com.tradeRepublic.candleStick.candleStick.controller;

import com.tradeRepublic.candleStick.candleStick.config.DBConfig;
import com.tradeRepublic.candleStick.candleStick.entity.Candlestick;
import com.tradeRepublic.candleStick.candleStick.entity.CandlesticksResponseBody;

import com.tradeRepublic.candleStick.candleStick.service.CandlestickService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/candlestick")
public class CandleStickController {

    @Autowired
    public final DBConfig dbConfig;

    private final  CandlestickService candlestickService;

    public CandleStickController(DBConfig dbConfig, CandlestickService candlestickService) {
        this.dbConfig = dbConfig;
        this.candlestickService = candlestickService;
    }


    @SneakyThrows
    @GetMapping
    public ResponseEntity<String> getCandlesticks(@RequestParam(value = "isin", required = true) String isin)
    {
        if (isin == null || isin.trim().isEmpty()) {
            return ResponseEntity.badRequest().eTag("missing ISIN (Input)").build();
        }


        List<Candlestick> candlesticks = candlestickService.getCandlestickslist(isin);

        if (candlesticks.isEmpty()) {
            return ResponseEntity.notFound().eTag("'no_data_for_isin'").build();
        }
        else{
            CandlesticksResponseBody candlesticksResponseBody = new CandlesticksResponseBody(candlesticks);
             return ResponseEntity.ok().body(convertToJson(candlesticksResponseBody));

        }

    }

        public String convertToJson(Object obj) throws IOException {
            return dbConfig.objectMapper().writeValueAsString(obj);
    }

}
