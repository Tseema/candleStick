CREATE SCHEMA IF NOT EXISTS cs;

CREATE TABLE IF NOT EXISTS cs.instruments
(
    instrument_id SERIAL NOT NULL
        CONSTRAINT instrument_pk
            PRIMARY KEY,
    isin           TEXT NOT NULL UNIQUE,
    description    TEXT,
    created_at     TIMESTAMPTZ NOT NULL
);

CREATE TABLE IF NOT EXISTS cs.quotes
(
    quotes_id SERIAL NOT NULL
            CONSTRAINT quotes_pk
                PRIMARY KEY,
    isin TEXT NOT NULL
        CONSTRAINT instrument_fk
            REFERENCES cs.instruments(isin) ON DELETE CASCADE,
    price         NUMERIC(15,4) NOT NULL,
    created_at    TIMESTAMPTZ NOT NULL
);

CREATE INDEX IF NOT EXISTS quotes_isin_created_at_idx
    ON cs.quotes (isin, created_at);

SELECT create_hypertable('cs.quotes','created_at');
