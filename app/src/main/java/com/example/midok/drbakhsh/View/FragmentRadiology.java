package com.example.midok.drbakhsh.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.midok.drbakhsh.Model.Appointments;
import com.example.midok.drbakhsh.Presenter.AdapterGeneralFunctionTimeline;
import com.example.midok.drbakhsh.R;

import java.util.ArrayList;

public class FragmentRadiology extends Fragment {

    ArrayList<Appointments> mRadiology;
    Appointments radiologies;
    RecyclerView recyclerView;
    AdapterGeneralFunctionTimeline generalFunctionTimeline;


    public FragmentRadiology() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_radiology, container, false);
        recyclerView = view.findViewById(R.id.radiologyRV);
        mRadiology = new ArrayList<>();
        radiologies = new Appointments();
        for (int i=1 ; i<=10 ; i++){
            radiologies.setDoctorName("Abdullah");
            radiologies.setDecription("ULTRASOUND OF ABDOMEN)");
            radiologies.setDate("18/10/2018");
            mRadiology.add(radiologies);
        }
        generalFunctionTimeline = new AdapterGeneralFunctionTimeline(mRadiology,getContext(),3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(generalFunctionTimeline);
        return view;
    }

}
