package com.github.AleksandrSpencer.mtb.service;

import com.github.AleksandrSpencer.mtb.javarushclient.dto.StatisticDTO;

/**
 * Service for getting bot statistics.
 */
public interface StatisticsService {
    StatisticDTO countBotStatistic();
}
