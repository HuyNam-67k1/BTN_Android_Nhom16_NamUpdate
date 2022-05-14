package com.example.dhktpm15a_nhom16;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class NhanVienAdapter extends BaseAdapter {

    Activity ctx;
    int layoutItem;
    ArrayList<NhanVien> arrayList;
    Button btnInfo;

    public NhanVienAdapter(Activity ctx, int layoutItem, ArrayList<NhanVien> arrayList) {
        this.ctx = ctx;
        this.layoutItem = layoutItem;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(ctx).inflate(layoutItem,viewGroup,false);
        TextView tvName = view.findViewById(R.id.txtName);
        TextView tvPosition = view.findViewById(R.id.txtPosition);

        tvName.setText(arrayList.get(i).getName());
        tvPosition.setText(arrayList.get(i).getPosition());

        view.findViewById(R.id.btnInfor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, UpdateScreen.class);
                intent.putExtra("id",arrayList.get(i).getId());
                ctx.startActivity(intent);
            }
        });

        return view;
    }

}
