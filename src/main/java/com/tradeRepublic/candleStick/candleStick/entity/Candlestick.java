package com.tradeRepublic.candleStick.candleStick.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Candlestick {

    @JsonProperty("open_price")
    private BigDecimal openPrice;

    @JsonProperty("closing_price")
    private BigDecimal closingPrice;

    @JsonProperty("high_price")
    private BigDecimal highPrice;

    @JsonProperty("low_price")
    private BigDecimal lowPrice;

    @JsonProperty("open_timestamp")
    private LocalDateTime openTimestamp;

    @JsonProperty("close_timestamp")
    private LocalDateTime closeTimestamp;

}

