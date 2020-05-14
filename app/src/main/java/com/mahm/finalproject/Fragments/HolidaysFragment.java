package com.mahm.finalproject.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.mahm.finalproject.Model.Item_HolidaysFg;
import com.mahm.finalproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class HolidaysFragment extends Fragment {

    private View view;
    private com.applandeo.materialcalendarview.CalendarView mCalendarView;
    private RecyclerView mFragRecView;
    private List<Item_HolidaysFg> data = new ArrayList<>();
    private List<Item_HolidaysFg> allHolidays;
    private List<EventDay> events;
    private Item_HolidaysFg objectHolidays;

    private Custom_RvAdapter_HolidaysFg recViwe_Adapter = new Custom_RvAdapter_HolidaysFg();


    private String url = "http://mohamedd8f8w-001-site1.dtempurl.com/api/holidays";

    public HolidaysFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater
                .from(getContext())
                .inflate(R.layout.fragment_holidays, container, false);

        init();
        if (!checkInternetConnected()) {
            Toast.makeText(getActivity(), "عذرا لا يوجد اتصال بالانترنت", Toast.LENGTH_LONG).show();

        } else {

            allHolidays = new ArrayList<>();
            mFragRecView.setAdapter(recViwe_Adapter);
            mFragRecView.setLayoutManager(new LinearLayoutManager(getContext()));
            mFragRecView.setHasFixedSize(true);

            getAllHolidays();

            mCalendarView.setOnDayClickListener(new OnDayClickListener() {
                @Override
                public void onDayClick(EventDay eventDay) {
                    data.clear();
                    Calendar calendar = eventDay.getCalendar();

                    final String day = calendar.get(Calendar.YEAR) + "-" + "0" + (calendar.get(Calendar.MONTH) + 1) + "-" + "0" + calendar.get(Calendar.DAY_OF_MONTH);

                    for (Item_HolidaysFg b : allHolidays) {
                        if (b.getDateHoliday().contains(day)) {
                            Log.e("calender fragment", b.toString());
                            data.add(b);
                        }
                    }
                    recViwe_Adapter.notifyDataSetChanged();

                }
            });
            events = new ArrayList<>();

        }


        return view;
    }


    //Initialization "FindView"
    void init() {
        mCalendarView = view.findViewById(R.id.calendarView);
        mFragRecView = view.findViewById(R.id.holidaysfg_RecView);

    }

    private void getAllHolidays() {

        allHolidays.clear();
        // عدد الحجوزات في اليوم
        Map<Integer, Integer> days = new HashMap<>();
        // تعبئة الكلندر
        Map<Integer, Calendar> holidaysDates = new HashMap<>();

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);

                        int id = object.getInt("holidayId");
                        String title = object.getString("title");
                        String date = object.getString("dateDay");

                        String dateAfterCut = date.substring(0, 10);

                        objectHolidays = new Item_HolidaysFg();
                        objectHolidays.setId(id);
                        objectHolidays.setTitle(title);
                        objectHolidays.setDateHoliday(dateAfterCut);

                        allHolidays.add(objectHolidays);

                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD", Locale.ENGLISH);

                        try {
                            calendar.setTime(Objects.requireNonNull(sdf.parse(objectHolidays.getDateHoliday())));
                            int day = calendar.get(Calendar.DAY_OF_MONTH);
                            Log.e("Day", day + " Days");
                            if (days.containsKey(day)) {
                                int sumDaysOfHolidays = days.get(day);
                                sumDaysOfHolidays++;
                                days.put(day, sumDaysOfHolidays);

                            } else {
                                days.put(day, 1);
                                holidaysDates.put(day, calendar);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    for (Integer key : days.keySet()) {
                        int countEvents = days.get(key);
                        int icon = R.drawable.ic_event;

                        switch (countEvents) {
                            case 2:
                                icon = R.drawable.ic_event;
                                break;
                            case 3:
                                icon = R.drawable.ic_event;
                                break;
                        }

                        if (countEvents > 3) {
                            icon = R.drawable.ic_event;
                        }

                        events.add(new EventDay(holidaysDates.get(key), icon));
                    }

                    mCalendarView.setEvents(events);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("holidaysFragment", error.getMessage() + "");
            }
        });

        Volley.newRequestQueue(Objects.requireNonNull(getActivity())).add(request);
    }

    private boolean checkInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }


    class Custom_RvAdapter_HolidaysFg extends RecyclerView.Adapter<Custom_RvAdapter_HolidaysFg.mViewHolder> {

        @NonNull
        @Override
        public Custom_RvAdapter_HolidaysFg.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater
                    .from(getActivity())
                    .inflate(R.layout.holidaysfg_item_rvlist, parent, false);

            return new mViewHolder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull Custom_RvAdapter_HolidaysFg.mViewHolder holder, int position) {
            Item_HolidaysFg holidaysFg = data.get(position);
            holder.title.setText(holidaysFg.getTitle());

        }

        @Override
        public int getItemCount() {
            return data.size();
        }


        public class mViewHolder extends RecyclerView.ViewHolder {

            private TextView title, description;

            public mViewHolder(@NonNull View itemView) {
                super(itemView);

                title = itemView.findViewById(R.id.holidaysItem_tv_title);
                description = itemView.findViewById(R.id.holidaysItem_tv_description);

            }
        }
    }


}
