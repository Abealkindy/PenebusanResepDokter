package com.rosinante.penebusanresepdokter.models;

import java.util.List;

import lombok.Data;

@Data
public class ObatModel {
    private List<ObatModelData> hasil;

    @Data
    public class ObatModelData {
        private int id_obat;
        private String nama_obat;
        private double harga_obat;
    }
}
