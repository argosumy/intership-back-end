package com.spd.baraholka.profile.statistic.persistance.repository;

@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public final class SQLQueries {
    public static final String GET_COUNT_CATEGORY_VIEW_FULL = "SELECT ad.category, count(*) "
            + "FROM advertisements AS ad INNER JOIN history_of_views AS history ON ad.id = history.ad_id "
            + "GROUP BY category";
    public static final String GET_COUNT_CATEGORY_BY_GROUP_FOR_PERIOD_NEW = "SELECT category, count(*) "
            + "FROM advertisements "
            + "WHERE publication_date >= ? AND publication_date < ? "
            + "GROUP BY category";
    public static final String GET_COUNT_CATEGORY_VIEW_FOR_PERIOD = "SELECT ad.category, count(*) "
            + "FROM advertisements AS ad INNER JOIN history_of_views AS history ON ad.id = history.ad_id "
            + "WHERE history.viewed_at >= ? AND history.viewed_at < ? "
            + "GROUP BY category";
}