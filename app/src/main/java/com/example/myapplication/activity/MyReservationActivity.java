package com.example.myapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Service.OnReservationDeletedListener;
import com.example.myapplication.Service.OnReservationsFetchedListener;
import com.example.myapplication.Service.ReservationDataService;
import com.example.myapplication.Service.implement.ReservationDataServiceImpl;
import com.example.myapplication.adapter.ReservationsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyReservationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ReservationsAdapter adapter;
    private List<User_Reservation> reservationList = new ArrayList<>();
    private ReservationDataService dataService; // 初始化这个接口的实现

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservation);

        recyclerView = findViewById(R.id.reservationsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataService = new ReservationDataServiceImpl();
        loadReservations(this);

        // 找到并设置退出按钮的点击事件
        Button exitButton = findViewById(R.id.btn_exit);
        exitButton.setOnClickListener(view -> finish()); // 关闭当前活动
    }

    private void loadReservations(Context context) {

        dataService.fetchReservations(new OnReservationsFetchedListener() {
            @Override
            public void onReservationsFetched(List<User_Reservation> reservations) {
                reservationList = reservations;
                adapter = new ReservationsAdapter(reservations, MyReservationActivity.this);
                recyclerView.setAdapter(adapter);
            }
        },context);
    }

    public void onDeleteClicked(int position) {
        User_Reservation reservation = reservationList.get(position);

        // 先从界面上删除预约
        reservationList.remove(position);
        adapter.notifyItemRemoved(position);


        // 然后执行后端删除操作
        dataService.deleteReservation(reservation.getId(), new OnReservationDeletedListener() {
            @Override
            public void onReservationDeleted(boolean success) {
                if (!success) {
                    // 如果后端删除失败，您可能需要重新将数据添加回列表，并通知用户
                    reservationList.add(position, reservation);
                    adapter.notifyItemInserted(position);
                    Toast.makeText(MyReservationActivity.this, "Failed to delete reservation", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MyReservationActivity.this, "Reservation deleted", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}

