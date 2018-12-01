package com.rosinante.penebusanresepdokter.models;

import java.util.List;

import lombok.Data;

@Data
public class PasienModel {
    private List<PasienData> hasil;

    @Data
    public class PasienData {
        private int id_user;
        private String username;
        private String user_password;
        private String pasien_name;
        private String pasien_gender;
        private String pasien_address;
        private String pasien_age;
        private String pasien_email;
        private String pasien_phone;
        private int status_antrian;
    }
}
