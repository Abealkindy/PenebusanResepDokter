package com.rosinante.penebusanresepdokter.models;

import java.util.List;

import lombok.Data;

@Data
public class DokterByIDModel {
    private List<DokterData> hasil;
    private boolean response;

    @Data
    public class DokterData {
        private int id_user;
        private String username;
        private String user_password;
        private String dokter_name;
        private String dokter_gender;
        private String dokter_address;
        private String dokter_specialist;
        private String dokter_email;
        private String dokter_phone;
        private String dokter_tarif;
        private int poliklinik_id;
        private String poliklinik_name;
    }
}
