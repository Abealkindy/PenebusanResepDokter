package com.rosinante.penebusanresepdokter.models;

import java.util.List;

import lombok.Data;

@Data
public class PembayaranModel {
    private List<PembayaranModelData> hasil;

    @Data
    public class PembayaranModelData {
        private int pembayaran_id;
        private String pasien_name;
        private String pembayaran_date;
        private double uang_pembayaran;
        private double kembalian_pembayaran;
    }
}
