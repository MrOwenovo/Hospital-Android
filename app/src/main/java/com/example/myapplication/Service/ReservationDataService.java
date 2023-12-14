package com.example.myapplication.Service;

import android.content.Context;

public interface ReservationDataService {
    void fetchReservations(OnReservationsFetchedListener listener, Context context);
    void deleteReservation(int reservationId, OnReservationDeletedListener listener);
    // 更多方法...
}

