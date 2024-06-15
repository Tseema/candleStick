package com.tradeRepublic.candleStick.candleStick.repository;


import com.tradeRepublic.candleStick.candleStick.entity.InstrumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentRepository extends JpaRepository<InstrumentEntity, String> {
     int findByIsin(String isin);

    @Override
    InstrumentEntity save(InstrumentEntity instrument);
    void deleteById(String isin);

}

