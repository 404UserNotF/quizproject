package com.beaconfire.quizsystem.utils;

public class Constants {
    public static final long TEST_DURATION = 30000;
    public static final int NUMBER_OF_QUESTION = 10;
    /**
         1. by default, sort by startTime
         2. sort by username
         3. sort by category
     */
    public static final Integer SORTED_BY_STARTTIME = 1;
    public static final Integer SORTED_BY_USERNAME = 2;
    public static final Integer SORTED_BY_QUIZTYPE = 3;
    public static final Integer NO_QUIZTYPE_ASSIGNED = -1;
    public static final Integer DEFAULT_LIMIT_OF_PAGE = 3;
}
