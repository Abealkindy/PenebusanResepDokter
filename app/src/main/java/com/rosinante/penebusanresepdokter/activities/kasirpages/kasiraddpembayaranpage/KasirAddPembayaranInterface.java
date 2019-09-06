package com.rosinante.penebusanresepdokter.activities.kasirpages.kasiraddpembayaranpage;

import com.rosinante.penebusanresepdokter.models.RegisterResponse;

public interface KasirAddPembayaranInterface {
    void onAddPembayaranSuccess(RegisterResponse registerResponse);

    void onAddPembayaranFailed(String errorMessage);
}
