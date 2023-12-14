package com.example.myapplication.Service;

import com.example.myapplication.activity.User_Reservation;

import java.util.List;

public interface OnReservationsFetchedListener {
    void onReservationsFetched(List<User_Reservation> reservations);
}