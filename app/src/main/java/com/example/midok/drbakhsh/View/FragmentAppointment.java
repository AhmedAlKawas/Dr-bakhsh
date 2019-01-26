package com.example.midok.drbakhsh.View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.midok.drbakhsh.Model.Appointments;
import com.example.midok.drbakhsh.Presenter.AdapterGeneralFunctionTimeline;
import com.example.midok.drbakhsh.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class FragmentAppointment extends Fragment {

    ArrayList<Appointments> mAppointments;
    Appointments appointments;
    View view;
    RecyclerView appointmentRV;
    FloatingActionButton addAppointment;
    String docName , date;
    LinearLayoutManager l;
    AdapterGeneralFunctionTimeline generalFunctionTimeline;

    public FragmentAppointment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppointments = new ArrayList<>();
        for (int counter=1 ; counter<=3 ; counter++){
            appointments = new Appointments();
            appointments.setDoctorName("Ahmed AlKawas");
            appointments.setDecription("Booked");
            appointments.setDate("10/9/2018");
            mAppointments.add(appointments);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_appointment, container, false);

        appointmentRV = view.findViewById(R.id.appointmentRV);
        addAppointment = view.findViewById(R.id.add_appointment);

        addAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_appointments);
                dialog.setCancelable(true);
                dialogContents(dialog);
                dialog.show();
            }
        });
        generalFunctionTimeline = new AdapterGeneralFunctionTimeline(mAppointments,getContext(),1);
        l = new LinearLayoutManager(getActivity());
        appointmentRV.setLayoutManager(l);
        appointmentRV.setAdapter(generalFunctionTimeline);
        return view;
    }


    public void dialogContents(final Dialog dialog){
        TextView checkBTN = dialog.findViewById(R.id.check);
        Spinner specialitySPN = dialog.findViewById(R.id.doctor_speciality_spn);
        final Spinner doctorNameSPN = dialog.findViewById(R.id.doctor_name_spn);
        final EditText dateET = dialog.findViewById(R.id.date_from_et);
        Spinner hoursSPN = dialog.findViewById(R.id.hr_spn);
        Spinner minsSPN = dialog.findViewById(R.id.min_spn);
        TextView cancelBTN = dialog.findViewById(R.id.cancel);

        ArrayAdapter specialityAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.specialities,android.R.layout.simple_spinner_item);
        specialityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specialitySPN.setAdapter(specialityAdapter);

        final Calendar calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener dialog1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                date = sdf.format(calendar.getTime());
                dateET.setText(date);
            }
        };

        specialitySPN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter doctorNameAdapter = null;
                switch (position){
                    case 0:
                        doctorNameAdapter = ArrayAdapter.createFromResource(getActivity(),
                                R.array.doctor_names,android.R.layout.simple_spinner_item);
                        break;
                    case 1:
                        doctorNameAdapter = ArrayAdapter.createFromResource(getActivity(),
                                R.array.anaesthesia,android.R.layout.simple_spinner_item);
                        break;
                    case 2:
                        doctorNameAdapter = ArrayAdapter.createFromResource(getActivity(),
                                R.array.audiometry,android.R.layout.simple_spinner_item);
                        break;
                    case 3:
                        doctorNameAdapter = ArrayAdapter.createFromResource(getActivity(),
                                R.array.cardiology,android.R.layout.simple_spinner_item);
                        break;
                    case 4:
                        doctorNameAdapter = ArrayAdapter.createFromResource(getActivity(),
                                R.array.women,android.R.layout.simple_spinner_item);
                        break;
                }
                doctorNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                doctorNameSPN.setAdapter(doctorNameAdapter);
                doctorNameSPN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        docName = doctorNameSPN.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(),dialog1, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Integer[] hoursArray = new Integer[]{9 , 10 , 11 , 12 , 17 , 18 , 19 , 20};
        ArrayAdapter hoursAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,hoursArray);
        hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hoursSPN.setAdapter(hoursAdapter);

        String[] minsArrays = {"00" , "15" , "30" , "45"};
        ArrayAdapter minsAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,minsArrays);
        hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minsSPN.setAdapter(minsAdapter);

        checkBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dateET.getText().toString().isEmpty()){
                    Appointments appointments = new Appointments(docName,"Saved",date);
                    mAppointments.add(appointments);
                    appointmentRV.setAdapter(generalFunctionTimeline);
                    dialog.dismiss();
                }else
                    dateET.setError(getString(R.string.enter_date));
            }
        });
        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
