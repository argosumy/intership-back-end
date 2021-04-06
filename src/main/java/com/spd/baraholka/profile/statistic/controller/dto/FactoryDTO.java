package com.spd.baraholka.profile.statistic.controller.dto;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class FactoryDTO {
    private final List<StatisticDTO> list;

    FactoryDTO(List<StatisticDTO> list) {
        this.list = list;
    }

    public StatisticDTO getDTO(String type) {
        StatisticDTO statisticDTO = null;
        for (StatisticDTO statistic : list) {
            if (type.equals(statistic.getTypeDTO())) {
                statisticDTO = statistic;
                break;
            }
        }
        return statisticDTO;
    }
}