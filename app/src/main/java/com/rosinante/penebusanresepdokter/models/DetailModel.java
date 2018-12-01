package com.rosinante.penebusanresepdokter.models;

import java.util.List;

import lombok.Data;

@Data
public class DetailModel {
    private List<DetailModelData> hasil;

    @Data
    public class DetailModelData {
        private int detail_id;
        private int resep_id;
    }
}
