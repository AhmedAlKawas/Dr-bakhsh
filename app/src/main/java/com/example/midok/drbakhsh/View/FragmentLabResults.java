package com.example.midok.drbakhsh.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.midok.drbakhsh.Model.Appointments;
import com.example.midok.drbakhsh.Model.LabResults;
import com.example.midok.drbakhsh.Presenter.AdapterGeneralFunctionTimeline;
import com.example.midok.drbakhsh.R;

import java.util.ArrayList;

public class FragmentLabResults extends Fragment {

    ArrayList<Appointments> mLabResults;
    Appointments labResults;
    RecyclerView recyclerView;
    AdapterGeneralFunctionTimeline generalFunctionTimeline;


    public FragmentLabResults() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lab_results, container, false);
        recyclerView = view.findViewById(R.id.labResultRV);
        mLabResults = new ArrayList<>();
        labResults = new Appointments();
        for (int i=1 ; i<=10 ; i++){
            labResults.setDoctorName("Abdul Rahman");
            labResults.setDecription("GLUCOSE RANDOM (RBS)");
            labResults.setDate("10/10/2018");
            mLabResults.add(labResults);
        }
        generalFunctionTimeline = new AdapterGeneralFunctionTimeline(mLabResults,getContext(),2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(generalFunctionTimeline);
        return view;
    }

}
