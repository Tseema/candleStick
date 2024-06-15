package com.tradeRepublic.candleStick.candleStick.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteEvent {

    @JsonProperty("data")
    public QuoteEntity quote;
}
