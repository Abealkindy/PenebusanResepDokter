package com.rosinante.penebusanresepdokter.models;

import java.util.List;

import lombok.Data;

@Data
public class AntrianPasienModel {
    private List<AntrianModelData> hasil;

    @Data
    public class AntrianModelData {
        private int antrian_id;
        private String tanggal_antrian;
        private int dokter_id;
        private String dokter_name;
        private int pasien_id;
        private String pasien_name;
        private String poliklinik_name;
        private String keterangan;
        private int status_antrian;
    }

}
