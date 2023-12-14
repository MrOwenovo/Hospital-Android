package com.example.myapplication.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity.DatabaseHelper;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.activity.User_Reservation;
import com.example.myapplication.entity.Department_doctor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AppointmentActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint);

        db = new DatabaseHelper(this);
        db.addDepartmentDoctor(new Department_doctor("SAT", "Bakery"));
        db.addDepartmentDoctor(new Department_doctor("IBSS", "DADDYYSY"));
        btn_back= findViewById(R.id.backIcon);

        DatePicker datePicker = findViewById(R.id.datePicker);
        Calendar calendar = Calendar.getInstance();
        datePicker.setMinDate(calendar.getTimeInMillis());

        // set the selection of time spinner
        Spinner timeSpinner = findViewById(R.id.timeSpinner);
        ArrayAdapter<CharSequence> time_adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_time_am, android.R.layout.simple_spinner_item);
        timeSpinner.setAdapter(time_adapter);

        // set the selection of doctor spinner
        Spinner docterSpinner = findViewById(R.id.doctorSpinner);

        // set the selection of  different departments
        ListView departmentListView = findViewById(R.id.departmentList);
        String[] departments = getResources().getStringArray(R.array.department_name);
        ArrayAdapter<String> department_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, departments);
        departmentListView.setAdapter(department_adapter);

        // match different doctors when choosing different departments
        Button submitButton = findViewById(R.id.submitReservationButton);
        final String[] selectedDepartment = {""};
        final List<String>[] doctor_list = new List[]{null};

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppointmentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        departmentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDepartment[0] = parent.getItemAtPosition(position).toString();
                Log.d("666", selectedDepartment[0]);
                doctor_list[0] = db.findDoctorsByDepartment(selectedDepartment[0]);
                ArrayAdapter<String> doctor_adapter = new ArrayAdapter<>(AppointmentActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, doctor_list[0]);
                docterSpinner.setAdapter(doctor_adapter);
                // Now 'selectedDepartment' holds the value of the selected item
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected items
                String selectedDate = datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear();
                String selectedTime = timeSpinner.getSelectedItem().toString();
                String selectedDoctor = docterSpinner.getSelectedItem().toString();

                // Store the selections in a string
                String reservationDetails = "Department: " + selectedDepartment[0] +
                        ", Date: " + selectedDate +
                        ", Time: " + selectedTime +
                        ", Doctor: " + selectedDoctor;

                Toast toast1 = Toast.makeText(getApplicationContext(), reservationDetails, Toast.LENGTH_LONG);
                toast1.show();

                if (selectedTime.equals("Please Select")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AppointmentActivity.this);
                    builder.setMessage("Please select a time slot");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            timeSpinner.setSelection(0);
                            ((ArrayAdapter) timeSpinner.getAdapter()).notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                } else if (selectedDoctor.equals("Please Select")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AppointmentActivity.this);
                    builder.setMessage("Please select a doctor");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            docterSpinner.setSelection(0);
                            ((ArrayAdapter) docterSpinner.getAdapter()).notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
                else{
                    goToMainActivity();
                }

                db.addUserReservation(new User_Reservation("210105", "BAIGOU", selectedDate, selectedTime, selectedDepartment[0], selectedDoctor));

            }

            private void goToMainActivity() {
                Intent intent = new Intent(AppointmentActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}