package com.example.myapplication.Service.implement;

import android.content.Context;

import com.example.myapplication.Service.OnReservationDeletedListener;
import com.example.myapplication.Service.OnReservationsFetchedListener;
import com.example.myapplication.Service.ReservationDataService;
import com.example.myapplication.activity.DatabaseHelper;
import com.example.myapplication.activity.User_Reservation;

import java.util.ArrayList;
import java.util.List;
/**
 * 实现预约数据服务接口，与后端进行通信获取和删除预约信息。
 */
public class ReservationDataServiceImpl implements ReservationDataService {

    // 预约列表，用于存储用户预约信息
    List<User_Reservation> reservations;
    private static DatabaseHelper databaseHelper = new DatabaseHelper(null);


    /**
     * 获取预约信息，此方法应该实现从后端API获取数据的逻辑。
     *
     * @param listener 一个回调接口，用于在数据获取后通知调用者。
     */
    @Override
    public void fetchReservations(OnReservationsFetchedListener listener, Context context) {

        databaseHelper = new DatabaseHelper(context);
        // 实现API调用，从数据库获取预约列表
        // TODO: ⬇这里应该是从后端数据库异步查询预约数据的代码
//         reservations = database.fetchReservationsForUser(userId);
        reservations = databaseHelper.getAllUserReservation() /* 从数据库查询 */;


        // TODO: ⬇下面的虚拟例子是临时的，待删除
        // 初始化虚拟数据
//        reservations = new ArrayList<>();









        // 调用listener的onReservationsFetched方法，传入结果
        listener.onReservationsFetched(reservations);
    }


    /**
     * 删除指定ID的预约记录。
     *
     * @param reservationId 要删除的预约的ID。
     * @param listener 删除操作结果的回调接口。
     */
    @Override
    public void deleteReservation(int reservationId, OnReservationDeletedListener listener) {
        // ⬇ TODO: 实现API调用，删除特定的预约记录


        // TODO 数据库删除成功结果，修改默认true的结果
        int success =  databaseHelper.delUserReservation(reservationId);;/* 数据库删除操作结果 */



        // 调用listener的onReservationDeleted方法，传入删除操作的成功与否的结果
        listener.onReservationDeleted(success==1);
    }

}
