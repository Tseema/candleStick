package com.tradeRepublic.candleStick.candleStick;

import com.tradeRepublic.candleStick.candleStick.config.InstrumentStream;
import com.tradeRepublic.candleStick.candleStick.config.QuoteStream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final InstrumentStream instrumentStream;
    private final QuoteStream quoteStream;

    public StartupRunner(InstrumentStream instrumentStream,QuoteStream quoteStream) {
        this.instrumentStream = instrumentStream;
        this.quoteStream = quoteStream;
    }

    @Override
    public void run(String... args) throws Exception {
        instrumentStream.getInstrumentStream();
        quoteStream.getQuoteStream();
    }
}

