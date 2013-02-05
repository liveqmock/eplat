DROP TABLE IF EXISTS mbill_detail;
CREATE TABLE mbill_detail(
    trade_no VARCHAR(128),
    out_trade_no VARCHAR(128),
    charge BIGINT,
    other_charge BIGINT,
    checked VARCHAR(1),
    UNIQUE(trade_no)
);
CREATE INDEX idx_mb_otn ON mbill_detail(out_trade_no);

INSERT INTO mbill_detail(trade_no, out_trade_no, charge, other_charge, checked) VALUES(?, ?, ?, ?, ?);
