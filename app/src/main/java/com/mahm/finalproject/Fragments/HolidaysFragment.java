package com.mahm.finalproject.Fragments;


import android.os.Bundle;
import android.telephony.mbms.MbmsErrors;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.mahm.finalproject.Adapters.C_RvAdapter_HolidaysFg;
import com.mahm.finalproject.Adapters.C_RvAdapter_HomeFg_ListNews;
import com.mahm.finalproject.Model.Item_HolidaysFg;
import com.mahm.finalproject.Model.Item_HomeFg;
import com.mahm.finalproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HolidaysFragment extends Fragment {

    private View view;
    private CalendarView mCalendarView;
    private RecyclerView mFragRecView;
    private List<Item_HolidaysFg> data;
    private C_RvAdapter_HolidaysFg recViwe_Adapter;


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

        data = new ArrayList<>();

        data.add(new Item_HolidaysFg("اجازة راس السنة١", " اجازة ل ٣ ايام من تاريخ ١"));
        data.add(new Item_HolidaysFg("اجازة راس السنة  2", " اجازة ل ٣ ايام من تاريخ ١-١-٢٠٢٠ الى ٣-١-٢٠٢٠"));
        data.add(new Item_HolidaysFg("اجازة راس السنة 3", " اجازة ل ٣ ايام من تاريخ ١-١-٢٠٢٠ الى ٣-١-٢٠٢٠"));
        data.add(new Item_HolidaysFg("اجازة راس السنة 4", " اجازة ل ٣ ايام من تاريخ ١-١-٢٠٢٠ الى ٣-١-٢٠٢٠"));
        data.add(new Item_HolidaysFg("اجازة راس السنة 5", " اجازة ل ٣ ايام من تاريخ ١-١-٢٠٢٠ الى ٣-١-٢٠٢٠"));
        data.add(new Item_HolidaysFg("اجازة راس السنة 6", " اجازة ل ٣ ايام من تاريخ ١-١-٢٠٢٠ الى ٣-١-٢٠٢٠"));
        data.add(new Item_HolidaysFg("اجازة راس السنة 7", " اجازة ل ٣ ايام من تاريخ ١-١-٢٠٢٠ الى ٣-١-٢٠٢٠"));
        data.add(new Item_HolidaysFg("اجازة راس السنة 8 ", " اجازة ل ٣ ايام من تاريخ ١-١-٢٠٢٠ الى ٣-١-٢٠٢٠"));


        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {

                recViwe_Adapter = new C_RvAdapter_HolidaysFg(getContext(), data);
                mFragRecView.setLayoutManager(new LinearLayoutManager(getContext()));
                mFragRecView.setHasFixedSize(true);
                mFragRecView.setAdapter(recViwe_Adapter);

                mFragRecView.setVisibility(View.VISIBLE);

            }
        });


        return view;
    }


    //Initialization "FindView"
    void init() {
        mCalendarView = view.findViewById(R.id.calendarView);
        mFragRecView = view.findViewById(R.id.holidaysfg_RecView);

    }

}
