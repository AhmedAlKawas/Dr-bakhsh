package com.example.midok.drbakhsh.Presenter;

import android.app.Dialog;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.midok.drbakhsh.Model.Appointments;
import com.example.midok.drbakhsh.R;

import java.util.ArrayList;

public class AdapterGeneralFunctionTimeline extends RecyclerView.Adapter<AdapterGeneralFunctionTimeline.GeneralFunctionTimelineHolder> {

    ArrayList<Appointments> mAppointments;
    //    ArrayList<LabResults> mLabResults;
//    ArrayList<Radiology> mRadiology;
//    ArrayList<Complaints> complaintsArrayList;
//    LabResults labResults;
//    Radiology radiology;
//    Complaints complaints;
    Context context;
    Appointments appointments;
    int type;
    Dialog complaintsDialog;

    public AdapterGeneralFunctionTimeline(ArrayList<Appointments> appointments,Context context , int type) {
        mAppointments = appointments;
        this.type = type;
        this.context = context;
    }

    @Override
    public GeneralFunctionTimelineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_general_functions_timeline, parent, false);
        return new GeneralFunctionTimelineHolder(view);
    }

    @Override
    public void onBindViewHolder(GeneralFunctionTimelineHolder holder, int position) {
        appointments = mAppointments.get(position);
        holder.dateTV.setText(appointments.getDate());
        holder.doctorNameTV.setText("DR. " + appointments.getDoctorName());
        holder.descriptionTV.setText(appointments.getDecription());
        holder.generalItemCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1){
                    createDialogForAppointments();
                }else if (type == 2){
                    createDialogForLabResults();
                }else if (type == 3){
                    createDialogForRadiology();
                }else if (type == 5){
                    createDialogForComplaints();
                }
            }

            private void createDialogForComplaints() {
                complaintsDialog = new Dialog(context);
                complaintsDialog.setContentView(R.layout.dialog_complaint);
                complaintsDialog.setCancelable(true);

                TextView sendMessageTV = complaintsDialog.findViewById(R.id.submit);
                TextView cancelTV = complaintsDialog.findViewById(R.id.cancel);
                TextView statusTV = complaintsDialog.findViewById(R.id.status_tv);

                statusTV.setVisibility(View.VISIBLE);
                sendMessageTV.setText(R.string.send_message);
                cancelTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        complaintsDialog.dismiss();
                    }
                });
                sendMessageTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createDialogForComplaintsMessage();
                    }
                });

                complaintsDialog.show();
            }

            private void createDialogForComplaintsMessage() {

                complaintsDialog.dismiss();

                final Dialog complaintsMessageDialog = new Dialog(context);
                complaintsMessageDialog.setContentView(R.layout.diolog_general_comments);
                complaintsMessageDialog.setCancelable(true);

                TextView headerTV = complaintsMessageDialog.findViewById(R.id.heading);
                EditText messageET = complaintsMessageDialog.findViewById(R.id.general_comments_et);
                TextView sendTV = complaintsMessageDialog.findViewById(R.id.submit);

                headerTV.setText(R.string.send_message);
                messageET.setHint(R.string.enter_message);
                sendTV.setText(R.string.send);
                sendTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        complaintsMessageDialog.dismiss();
                    }
                });

                complaintsMessageDialog.show();
            }

            private void createDialogForRadiology() {
                Log.e("dialog","Radiology");
            }

            private void createDialogForLabResults() {
                Log.e("dialog","Lab");
            }

            private void createDialogForAppointments() {
                final Dialog appointmentDiolog = new Dialog(context);
                appointmentDiolog.setContentView(R.layout.dialog_appointments);
                appointmentDiolog.setCancelable(true);

                TextView checkTV = appointmentDiolog.findViewById(R.id.check);
                TextView cancelTV = appointmentDiolog.findViewById(R.id.cancel);
                TextView statusTV = appointmentDiolog.findViewById(R.id.status_tv);

                statusTV.setVisibility(View.VISIBLE);
                checkTV.setVisibility(View.GONE);
                cancelTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        appointmentDiolog.dismiss();
                    }
                });


                appointmentDiolog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAppointments.size();
    }

    public class GeneralFunctionTimelineHolder extends RecyclerView.ViewHolder {
        TextView doctorNameTV, descriptionTV, dateTV;
        ConstraintLayout generalItemCons;

        public GeneralFunctionTimelineHolder(View itemView) {
            super(itemView);
            doctorNameTV = itemView.findViewById(R.id.doctor_name_tv);
            descriptionTV = itemView.findViewById(R.id.desription_tv);
            dateTV = itemView.findViewById(R.id.dateTV);
            generalItemCons = itemView.findViewById(R.id.general_fuctions_cons_layout);
        }
    }
}

