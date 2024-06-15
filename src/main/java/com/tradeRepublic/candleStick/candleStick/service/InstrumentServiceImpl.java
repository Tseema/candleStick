package com.tradeRepublic.candleStick.candleStick.service;

import com.tradeRepublic.candleStick.candleStick.entity.InstrumentEntity;
import com.tradeRepublic.candleStick.candleStick.entity.InstrumentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tradeRepublic.candleStick.candleStick.repository.InstrumentRepository;

import java.time.LocalDateTime;

@Service
public class InstrumentServiceImpl {

    private final InstrumentRepository instrumentRepository;

    @Autowired
    public InstrumentServiceImpl(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    public void processInstrumentEvent(InstrumentEvent instrumentEvent) {

        switch (instrumentEvent.getType()) {
            case  ADD:
                InstrumentEntity instrumentEntity = new InstrumentEntity(instrumentEvent.getInstrument().getIsin(),
                        instrumentEvent.getInstrument().getDescription(), LocalDateTime.now() );
                instrumentRepository.save(instrumentEntity);
                break;
            case DELETE :
                    instrumentRepository.deleteById(instrumentEvent.getInstrument().getIsin());

                break;
            default:
                // Handle unknown event type
                break;
        }
    }
}

