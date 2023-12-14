package com.example.myapplication.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ReservationDetailsFragment extends BottomSheetDialogFragment {
    private User_Reservation reservationDetails;

    public void setReservationDetails(User_Reservation reservation) {
        this.reservationDetails = reservation;
        updateUI();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservation_details, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (reservationDetails != null) {
            updateUI();
        }
    }

    private void updateUI() {
        if (getView() != null && reservationDetails != null) {
            TextView appointmentTimeView = getView().findViewById(R.id.tv_appointment_time);
            TextView doctorNameView = getView().findViewById(R.id.tv_doctor_name);
            // ...更新其他视图...

            appointmentTimeView.setText(reservationDetails.getTime_slot());
            doctorNameView.setText(reservationDetails.getDoctor());
            // ...设置其他数据...
        }
    }
}
