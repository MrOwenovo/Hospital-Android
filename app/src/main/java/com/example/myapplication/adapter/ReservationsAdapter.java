package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.MyReservationActivity;
import com.example.myapplication.activity.User_Reservation;

import java.util.List;

public class
ReservationsAdapter extends RecyclerView.Adapter<ReservationsAdapter.ViewHolder> {

    private List<User_Reservation> reservationList;
    private MyReservationActivity activity;

    public ReservationsAdapter(List<User_Reservation> reservationList, MyReservationActivity activity) {
        this.reservationList = reservationList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User_Reservation reservation = reservationList.get(position);
        holder.doctorName.setText(reservation.getDoctor());
        String dateTime = reservation.getDate() + ": " + reservation.getTime_slot();
        holder.appointmentTime.setText(dateTime);
        holder.department.setText(reservation.getDepartment());

        holder.deleteButton.setOnClickListener(view -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {
                activity.onDeleteClicked(currentPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView doctorName, appointmentTime,department;
        public Button deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            doctorName = itemView.findViewById(R.id.doctorName);
            appointmentTime = itemView.findViewById(R.id.appointmentTime);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            department = itemView.findViewById(R.id.department);


        }
    }
}
