package com.tradeRepublic.candleStick.candleStick.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CandlesticksResponseBody {
    public CandlesticksResponseBody(List<Candlestick> candlestickList) {
        this.candlestickList = candlestickList;
    }

    @JsonProperty("Candlesticks")
    List<Candlestick> candlestickList;
}
