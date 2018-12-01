package com.rosinante.penebusanresepdokter.models;

import java.util.List;

import lombok.Data;

@Data
public class DokterDetailModel {
    private List<DokterDetailData> hasil;
    private boolean response;

    @Data
    public class DokterDetailData {
        private int dokter_id;
        private String dokter_name;
    }
}
