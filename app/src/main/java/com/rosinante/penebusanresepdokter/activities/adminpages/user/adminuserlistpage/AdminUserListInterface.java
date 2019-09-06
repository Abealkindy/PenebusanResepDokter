package com.rosinante.penebusanresepdokter.activities.adminpages.user.adminuserlistpage;

import com.rosinante.penebusanresepdokter.models.LoginModel;

public interface AdminUserListInterface {
    void onGetAllUserDataSuccess(LoginModel loginModel);

    void onGetAllUserDataFailed(String errorMessage);
}
