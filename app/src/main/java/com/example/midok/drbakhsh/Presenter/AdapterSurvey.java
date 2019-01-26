package com.example.midok.drbakhsh.Presenter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.midok.drbakhsh.Model.Survey;
import com.example.midok.drbakhsh.R;

import java.util.ArrayList;

public class AdapterSurvey extends RecyclerView.Adapter<AdapterSurvey.SurveyHolder> {

    ArrayList<Survey> mSurvey;

    public AdapterSurvey(ArrayList<Survey> mSurvey) {
        this.mSurvey = mSurvey;
    }

    @Override
    public SurveyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_survey, parent, false);
        return new SurveyHolder(view);
    }

    @Override
    public void onBindViewHolder(SurveyHolder holder, int position) {
        Survey survey = mSurvey.get(position);
        holder.nameTV.setText("DR. " + survey.getDoctorName());
        holder.dateTV.setText(survey.getDate());
        holder.surveyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("dialog","Survey");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSurvey.size();
    }

    public class SurveyHolder extends RecyclerView.ViewHolder {

        TextView nameTV, dateTV;
        ConstraintLayout surveyLayout;

        public SurveyHolder(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.name_tv);
            dateTV = itemView.findViewById(R.id.dateTV);
            surveyLayout = itemView.findViewById(R.id.survey_item_cons_layout);
        }
    }
}
