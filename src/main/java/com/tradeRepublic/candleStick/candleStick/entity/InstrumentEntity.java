package com.tradeRepublic.candleStick.candleStick.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.Instant;
import java.time.LocalDateTime;

import static java.time.LocalTime.now;


@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "instruments",schema="cs")
public class InstrumentEntity {
    public InstrumentEntity(String isin, String description, LocalDateTime createdTimeStamp) {
        this.isin = isin;
        this.description = description;
        this.createdTimeStamp = createdTimeStamp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long instrument_id;

    private String isin;

    private String description;

    @Column(name = "created_at")
    //@CreationTimestamp(source = SourceType.DB)
    private LocalDateTime createdTimeStamp;

}


