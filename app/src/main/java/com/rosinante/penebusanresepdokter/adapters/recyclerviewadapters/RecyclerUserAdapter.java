package com.rosinante.penebusanresepdokter.adapters.recyclerviewadapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rosinante.penebusanresepdokter.R;
import com.rosinante.penebusanresepdokter.models.LoginModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerUserAdapter extends RecyclerView.Adapter<RecyclerUserAdapter.ViewHolder> {

    private Context context;
    private List<LoginModel.UserLoginData> userDataList;

    public RecyclerUserAdapter(Context context, List<LoginModel.UserLoginData> userDataList) {
        this.context = context;
        this.userDataList = userDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_for_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion) {
        viewHolder.textViewUsernameUser.setText(userDataList.get(postion).getUsername());
        viewHolder.textViewUserRole.setText(userDataList.get(postion).getUser_role());
//        viewHolder.cardItemDokter.setOnClickListener(v -> {
//            Intent intent = new Intent(context.getApplicationContext(), AdminEditDokterProfileActivity.class);
//            intent.putExtra("poliklinik_id", poliklinikDataList.get(postion).getPoliklinik_id());
//            intent.putExtra("poliklinik_name", poliklinikDataList.get(postion).getPoliklinik_name());
//            context.startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_username_user)
        TextView textViewUsernameUser;
        @BindView(R.id.text_view_user_role)
        TextView textViewUserRole;
        @BindView(R.id.relative_list_bottom)
        RelativeLayout relativeListBottom;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
