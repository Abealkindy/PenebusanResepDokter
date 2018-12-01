package com.rosinante.penebusanresepdokter.models;

import java.util.List;

import lombok.Data;

@Data
public class PoliklinikModel {
    private List<PoliklinikData> hasil;

    @Data
    public static class PoliklinikData {
        private int poliklinik_id;
        private String poliklinik_name;
    }
}
