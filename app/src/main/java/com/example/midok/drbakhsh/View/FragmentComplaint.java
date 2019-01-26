package com.example.midok.drbakhsh.View;


import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.midok.drbakhsh.Model.Appointments;
import com.example.midok.drbakhsh.Presenter.AdapterGeneralFunctionTimeline;
import com.example.midok.drbakhsh.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FragmentComplaint extends Fragment {

    ArrayList<Appointments> mComplaints;
    Appointments complaints;
    RecyclerView recyclerView;
    AdapterGeneralFunctionTimeline generalFunctionTimeline;
    FloatingActionButton addComplaint;
    String date , complainedOnPersonName;
    TextView cancelBTN , submitBTN;
    EditText nameET , detailET;
    Spinner involvedPersonSPN , categorySPN , subCategorySPN , complainTypeSPN;
    Dialog complaintsDialog;


    public FragmentComplaint() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_fragment_complaint, container, false);

        recyclerView = view.findViewById(R.id.complaintsRV);
        addComplaint = view.findViewById(R.id.add_complaint);

        mComplaints = new ArrayList<>();
        complaints = new Appointments();
        for (int i=1 ; i<=10 ; i++){
            complaints.setDoctorName("Abdul Aziz");
            complaints.setDecription("Submitted");
            complaints.setDate("2/10/2018");
            mComplaints.add(complaints);
        }
        generalFunctionTimeline = new AdapterGeneralFunctionTimeline(mComplaints,getContext(),5);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(generalFunctionTimeline);

        addComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complaintsDialog = new Dialog(getContext());
                complaintsDialog.setContentView(R.layout.dialog_complaint);
                complaintsDialog.setCancelable(true);
                complaintsDialogContents(complaintsDialog);
                complaintsDialog.show();
            }

            private void complaintsDialogContents(final Dialog complaintsDialog) {

                cancelBTN = complaintsDialog.findViewById(R.id.cancel);
                submitBTN = complaintsDialog.findViewById(R.id.submit);
                involvedPersonSPN = complaintsDialog.findViewById(R.id.involved_person_spn);
                categorySPN = complaintsDialog.findViewById(R.id.category_spn);
                subCategorySPN = complaintsDialog.findViewById(R.id.sub_category_spn);
                complainTypeSPN = complaintsDialog.findViewById(R.id.complaint_type_spn);
                nameET = complaintsDialog.findViewById(R.id.name_et);
                detailET = complaintsDialog.findViewById(R.id.details_et);

                ArrayAdapter involvedPersonAdapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.involved_person,android.R.layout.simple_spinner_item);
                involvedPersonAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                involvedPersonSPN.setAdapter(involvedPersonAdapter);

                ArrayAdapter categoryAdapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.categories,android.R.layout.simple_spinner_item);
                categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                categorySPN.setAdapter(categoryAdapter);

                ArrayAdapter subCategoryAdapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.sub_category,android.R.layout.simple_spinner_item);
                subCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subCategorySPN.setAdapter(subCategoryAdapter);

                ArrayAdapter complaintTypeAdapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.complaint_type,android.R.layout.simple_spinner_item);
                complaintTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                complainTypeSPN.setAdapter(complaintTypeAdapter);

                cancelBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        complaintsDialog.dismiss();
                    }
                });

                submitBTN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        date = simpleDateFormat.format(calendar.getTimeInMillis());
                        complainedOnPersonName = nameET.getText().toString().trim();

                        if (!complainedOnPersonName.isEmpty()){
                            complaints = new Appointments(complainedOnPersonName , "Saved" , date);
                            mComplaints.add(complaints);
                            recyclerView.setAdapter(generalFunctionTimeline);
                            complaintsDialog.dismiss();
                        }else
                            nameET.setError(getString(R.string.complain_on_name_error));
                    }
                });

            }
        });

        return view;
    }

}
