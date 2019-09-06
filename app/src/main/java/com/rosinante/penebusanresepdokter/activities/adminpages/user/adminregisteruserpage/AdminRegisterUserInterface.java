package com.rosinante.penebusanresepdokter.activities.adminpages.user.adminregisteruserpage;

import com.rosinante.penebusanresepdokter.models.RegisterResponse;

public interface AdminRegisterUserInterface {
    void onInsertUserRegistrationDataSuccess(RegisterResponse registerResponse);

    void onInsertUserRegistrationDataFailed(String errorMessage);
}
