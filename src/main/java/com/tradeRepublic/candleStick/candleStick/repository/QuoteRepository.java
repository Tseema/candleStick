package com.tradeRepublic.candleStick.candleStick.repository;


import com.tradeRepublic.candleStick.candleStick.entity.Candlestick;
import com.tradeRepublic.candleStick.candleStick.entity.Candlesticks;
import com.tradeRepublic.candleStick.candleStick.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, String> {

    //List<QuoteEntity> findDistinctByIsinAndCreatedTimeStampBetween(String isin, Instant from, Instant to);

    @Override
    QuoteEntity save(QuoteEntity quoteEntity);

    Optional<List<QuoteEntity>> findByIsinAndCreatedTimeStampGreaterThanEqualAndCreatedTimeStampLessThanEqual(String isin, LocalDateTime from, LocalDateTime to);

   /* @Query(nativeQuery = true,
            value =
            "SELECT\n" +
                    "time_bucket_gapfill('1 minute', created_at) AS bucket,\n" +
                    "first(price,created_at) as openPrice,\n" +
                    "locf(last(price, created_at), (select price FROM cs.quotes q2 " +
                    "WHERE  q2.created_at < :to " +
                    "and q2.isin = q.isin ORDER BY q2.created_at DESC LIMIT 1)) as closingPrice,\n" +
                    "max(price) as highPrice,\n" +
                    "min(price) as lowPrice\n" +
                    "FROM cs.quotes q\n" +
                    "WHERE created_at >= :from AND created_at < :to \n" +
                    "and isin = :isin \n" +
                    "GROUP BY bucket, isin")*/
   //Optional<List<Candlesticks>> getCandlesticks(@Param("isin")String isin, @Param("from") Instant from, @Param("to") Instant to);


}

