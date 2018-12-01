package com.rosinante.penebusanresepdokter.models;

import lombok.Data;

@Data
public class CountTimeModel {
    private long endTime;
    private long timeRemaining;

    public CountTimeModel(long endTime) {
        this.endTime = endTime;
        timeRemaining = endTime - System.currentTimeMillis();
    }
}
