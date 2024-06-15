package com.tradeRepublic.candleStick.candleStick.service;

import com.tradeRepublic.candleStick.candleStick.entity.QuoteEntity;
import com.tradeRepublic.candleStick.candleStick.entity.QuoteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tradeRepublic.candleStick.candleStick.repository.InstrumentRepository;
import com.tradeRepublic.candleStick.candleStick.repository.QuoteRepository;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class QuoteServiceImpl {

    private final QuoteRepository quoteRepository;

    private Clock clock = Clock.systemUTC();

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public void processQuoteEvent(QuoteEvent event){
            QuoteEntity quote = new QuoteEntity(event.getQuote().getIsin(),
                    event.getQuote().getPrice(), LocalDateTime.now());
            quoteRepository.save(quote);
    }



}

