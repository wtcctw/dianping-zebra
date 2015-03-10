package com.dianping.zebra.monitor.util;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class SqlMonitorUtils {
    public final static int BIG_SQL = 102400;

    public static String getSqlLengthName(int length) {
        if (length <= 1024) {
            return "<= 1K";
        } else if (length <= 10240) {
            return "<= 10K";
        } else if (length <= 102400) {
            return "<= 100K";
        } else if (length <= 1024 * 1024) {
            return "<= 1M";
        } else if (length <= 1024 * 10240) {
            return "<= 10M";
        } else if (length <= 1024 * 102400) {
            return "<= 100M";
        } else {
            return "> 100M";
        }
    }
}
