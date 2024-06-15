package com.tradeRepublic.candleStick.candleStick.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;

public interface Candlesticks {
   BigDecimal getOpenPrice();

    BigDecimal getClosingPrice();

    BigDecimal getHighPrice();

    BigDecimal getLowPrice();

     Instant getOpenTimestamp();

     Instant getCloseTimestamp();
}
