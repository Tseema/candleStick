package com.tradeRepublic.candleStick.candleStick.service;

import com.tradeRepublic.candleStick.candleStick.entity.Candlestick;
import com.tradeRepublic.candleStick.candleStick.entity.Candlesticks;
import com.tradeRepublic.candleStick.candleStick.entity.QuoteEntity;
import com.tradeRepublic.candleStick.candleStick.repository.QuoteRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import java.util.stream.Collectors;

@Service
public class CandlestickService {

    private final QuoteRepository quoteRepository;


    @Autowired public CandlestickService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }


    public List<Candlestick> getCandlestickslist(String isin){
        List<Candlestick> candlesticks = new ArrayList<>();
        //List<Candlesticks> list = new ArrayList<>();
        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = to.minus(30, ChronoUnit.MINUTES);
        //Instant backfillUntil = to.minus(2, ChronoUnit.DAYS);



        Optional<List<QuoteEntity>> quotes = quoteRepository.findByIsinAndCreatedTimeStampGreaterThanEqualAndCreatedTimeStampLessThanEqual(isin, from, to);
        Map<LocalDateTime, List<QuoteEntity>> groupedQuotes = quotes.get().stream()
                .collect(Collectors.groupingBy(quoteEntity -> quoteEntity.getCreatedTimeStamp().truncatedTo(ChronoUnit.MINUTES)));

                /*.collect(Collectors.groupingBy(q-> q.getCreatedTimeStamp()
                        truncatedTo(ChronoUnit.MINUTES)));*/


        candlesticks = groupedQuotes.entrySet().stream()
                .map(quote -> {
                    LocalDateTime bucket = quote.getKey();
                    List<QuoteEntity> bucketQuotes  = quote.getValue();

                    Optional<QuoteEntity> maybeOpen = bucketQuotes.stream()
                            .max(Comparator.comparing(QuoteEntity::getCreatedTimeStamp));
                    Optional<QuoteEntity> maybeClose = bucketQuotes.stream()
                            .min(Comparator.comparing(QuoteEntity::getCreatedTimeStamp));
                    Optional<BigDecimal> maybeHigh = bucketQuotes.stream()
                            .map(QuoteEntity::getPrice)
                            .max(BigDecimal::compareTo);
                    Optional<BigDecimal> maybeLow = bucketQuotes.stream()
                            .map(QuoteEntity::getPrice)
                            .max(BigDecimal::compareTo);

                    BigDecimal closePrice = maybeClose.map(QuoteEntity::getPrice)
                            .orElseGet(() -> {
                                // Fallback logic to find the last price before 'from' date
                                return bucketQuotes.stream()
                                        .max(Comparator.comparing(QuoteEntity::getCreatedTimeStamp))
                                        .map(QuoteEntity::getPrice)
                                        .orElse(BigDecimal.valueOf(0.0)); // or some default value
                            });

                    return new Candlestick(maybeOpen.map(QuoteEntity::getPrice).orElse(BigDecimal.valueOf(0.0)),
                            closePrice,
                            maybeHigh.orElse(BigDecimal.valueOf(0.0)),
                            maybeLow.orElse(BigDecimal.valueOf(0.0)),
                            maybeOpen.get().getCreatedTimeStamp(),
                            maybeClose.get().getCreatedTimeStamp()
                            );

                })
                .collect(Collectors.toList());




       /* working

       Optional<List<Candlesticks>> candlesticks1 = quoteRepository.getCandlesticks(isin, from, to);
        list = candlesticks1.get();

        candlesticks = covertToList(list);
*/

        //getCandlesticksBetween(isin, from, to,backfillUntil);
                //quoteRepository.getCandlesticksBetween(isin,from,to,backfillUntil);


        return candlesticks;
    }

    private List<Candlestick> covertToList(List<Candlesticks> list) {
        List<Candlestick> candlesticks = new ArrayList<>();


        ModelMapper modelMapper = new ModelMapper();
        list.stream().forEach(cd->{
            Candlestick newcd =  modelMapper.map(cd,Candlestick.class);
            candlesticks.add(newcd);
        });

        return candlesticks;
    }

   /* private List<Candlestick> getCandlesticksBetween(String isin, Instant from, Instant to, Instant backfillUntil)
    {
        EntityManager entityManager;
        try (EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence")) {
            entityManager = entityManagerFactory.createEntityManager();
        }

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        //CriteriaQuery<PostComment> criteria = builder.createQuery(PostComment.class);
        return new ArrayList<>();

    }*/
}