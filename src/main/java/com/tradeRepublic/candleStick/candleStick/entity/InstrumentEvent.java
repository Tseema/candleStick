package com.tradeRepublic.candleStick.candleStick.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class InstrumentEvent {

    @JsonProperty("data")
    public InstrumentEntity instrument;

    @JsonProperty("type")
    public Type type;
   public enum Type{
       ADD,
       DELETE
   }

}
