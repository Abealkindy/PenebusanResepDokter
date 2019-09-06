package com.rosinante.penebusanresepdokter.models;

import java.util.List;

import lombok.Data;

@Data
public class DetailStrukModel {
    private List<DetailStrukModelData> hasil;

    @Data
    public class DetailStrukModelData {
        private int detail_obat_id;
        private int resep_id;
        private String pembayaran_date;
        private String pasien_name;
        private String dokter_name;
        private String poliklinik_name;
        private String resep_text;
        private String nama_obat;
        private String dosis_obat;
        private double harga_obat;
        private double total_harga;
        private String dokter_tarif;
        private double uang_pembayaran;
        private double kembalian_pembayaran;
        private int resep_status;
    }
}
