package com.rosinante.penebusanresepdokter.models;

import java.util.List;

import lombok.Data;

@Data
public class ResepModel {
    private List<ResepData> hasil;

    @Data
    public class ResepData {
        private int resep_id;
        private String dokter_name;
        private String pasien_name;
        private String resep_text;
        private String resep_date;
        private int resep_status;
    }
}
