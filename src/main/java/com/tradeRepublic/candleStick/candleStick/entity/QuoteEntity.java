package com.tradeRepublic.candleStick.candleStick.entity;


import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.domain.Persistable;


import java.math.BigDecimal;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
/*@SqlResultSetMapping(name="Mapping.Candlestick",
        classes = {
                @ConstructorResult(targetClass = Candlestick.class,
                        columns = {@ColumnResult(name="openPrice"), @ColumnResult(name="closingPrice"),
                                @ColumnResult(name="highPrice"),@ColumnResult(name="lowPrice"),
                                @ColumnResult(name="openTimestamp"),@ColumnResult(name="closeTimestamp")
                        }
                )})*/
@Table(name = "quotes",schema="cs")
public class QuoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long quoteId;

    public QuoteEntity(String isin, BigDecimal price, LocalDateTime createdTimeStamp) {
        this.isin = isin;
        this.price = price;
        this.createdTimeStamp = createdTimeStamp;
    }

    private String isin;

    private BigDecimal price;

    @Column(name = "created_at")
    //@CreationTimestamp(source = SourceType.DB)
    private LocalDateTime createdTimeStamp;

   /* @Override
    public Long getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return true;
    }*/
}


