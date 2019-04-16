package com.rosinante.penebusanresepdokter.models;

import java.util.List;

import lombok.Data;

@Data
public class DetailFarmasiModel {
    private List<DetailFarmasiData> hasil;

    @Data
    public class DetailFarmasiData {
        private int detail_obat_id;
        private String resep_text;
        private String nama_obat;
        private String dosis_obat;
        private double harga_obat;
        private double total_harga;
        private int resep_status;
    }
}
