package com.example.contentpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarDemo extends AppCompatActivity {
    private Uri CALENDAR_URI;
    private String uri;

    ListView list;
    ArrayList<String> listdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        list = findViewById(R.id.ListView);

        listdata = new ArrayList<String>();
        fetchCalendarToday();
    }

    private void fetchCalendarToday(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALENDAR},0);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_CALENDAR},0);

        String[] projection = new String[] { CalendarContract.Events.CALENDAR_ID, CalendarContract.Events.TITLE, CalendarContract.Events.DESCRIPTION, CalendarContract.Events.DTSTART, CalendarContract.Events.DTEND, CalendarContract.Events.ALL_DAY, CalendarContract.Events.EVENT_LOCATION };

        Calendar startTime = Calendar.getInstance();
        startTime.set(2020,00,01,00,00);

        Calendar endTime= Calendar.getInstance();
        endTime.set(2021,00,01,00,00);

        String selection = "(( " + CalendarContract.Events.DTSTART + " >= " + startTime.getTimeInMillis() + " ) AND ( " + CalendarContract.Events.DTSTART + " <= " + endTime.getTimeInMillis() + " ))";

        Cursor cursor = this.getBaseContext().getContentResolver().query( CalendarContract.Events.CONTENT_URI, projection, selection, null, null );

        if (cursor.moveToFirst()) {
            do {
                String cal="Title: " + cursor.getString(1) + " Start-Time: " + (new Date(cursor.getLong(3))).toString();
                listdata.add(cal);
            } while ( cursor.moveToNext());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_layout, listdata);
        list.setAdapter(adapter);
    }
}