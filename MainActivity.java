package org.warnier.zhang.calendarview.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.warnier.zhang.support.v1.widget.CalendarView;


public class MainActivity extends AppCompatActivity {
    CalendarView mCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCalendarView = new CalendarView(this);
        setContentView(mCalendarView);
    }
}
