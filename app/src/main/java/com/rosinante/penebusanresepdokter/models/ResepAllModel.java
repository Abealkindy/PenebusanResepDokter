package com.rosinante.penebusanresepdokter.models;

import java.util.List;

import lombok.Data;

@Data
public class ResepAllModel {
    private List<ResepAllModelData> hasil;

    @Data
    public class ResepAllModelData {
        private int resep_id;
        private int antrian_id;
        private int pasien_id;
        private String resep_date;
        private String dokter_name;
        private String pasien_name;
        private String nama_obat;
        private double harga_obat;
        private String dosis_obat;
        private double total_harga;
    }
}
