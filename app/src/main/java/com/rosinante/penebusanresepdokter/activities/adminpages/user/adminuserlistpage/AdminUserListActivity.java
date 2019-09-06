package com.rosinante.penebusanresepdokter.activities.adminpages.user.adminuserlistpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.activities.adminpages.MainAdminActivity;
import com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters.RecyclerUserAdapter;
import com.rosinante.penebusanresepdokter.models.LoginModel;
import com.rosinante.penebusanresepdokter.networks.ApiService;
import com.rosinante.penebusanresepdokter.networks.RetrofitConfig;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AdminUserListActivity extends AppCompatActivity implements AdminUserListInterface {

    @BindView(R.id.recycler_user_list)
    RecyclerView recyclerUserList;
    private AdminUserListPresenter adminUserListPresenter = new AdminUserListPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_list);
        ButterKnife.bind(this);
        setTitle("User List");
        adminUserListPresenter.getAllUserData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminUserListActivity.this, MainAdminActivity.class));
        finish();
    }

    @Override
    public void onGetAllUserDataSuccess(LoginModel loginModel) {
        recyclerUserList.setLayoutManager(new LinearLayoutManager(AdminUserListActivity.this));
        recyclerUserList.setAdapter(new RecyclerUserAdapter(AdminUserListActivity.this, Objects.requireNonNull(loginModel).getHasil()));
    }

    @Override
    public void onGetAllUserDataFailed(String errorMessage) {
        Toast.makeText(AdminUserListActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
