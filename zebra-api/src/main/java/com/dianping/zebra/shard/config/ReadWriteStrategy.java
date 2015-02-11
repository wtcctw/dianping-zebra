package com.dianping.zebra.shard.config;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public enum ReadWriteStrategy {
    UNKNOWN(0), NEW_ONLY(1), OLD_ONLY(2), NEW_FIRST(3), OLD_FIRST(4);

    private int strategy;

    private ReadWriteStrategy(int strategy) {
        this.strategy = strategy;
    }

    public ReadWriteStrategy fromInt(int strategy) {
        switch (strategy) {
            case 1:
                return ReadWriteStrategy.NEW_ONLY;
            case 2:
                return ReadWriteStrategy.OLD_ONLY;
            case 3:
                return ReadWriteStrategy.NEW_FIRST;
            case 4:
                return ReadWriteStrategy.OLD_FIRST;
            default:
                return ReadWriteStrategy.UNKNOWN;
        }
    }
}
