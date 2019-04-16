package com.rosinante.penebusanresepdokter.models;

import java.util.List;

import lombok.Data;

@Data
public class ResepDokterModel {
    private List<ResepModelData> hasil;

    @Data
    public class ResepModelData {
        private int resep_id;
        private int dokter_id;
        private int pasien_id;
        private String dokter_name;
        private String pasien_name;
        private String resep_text;
        private String resep_date;
        private int resep_status;
    }

}
