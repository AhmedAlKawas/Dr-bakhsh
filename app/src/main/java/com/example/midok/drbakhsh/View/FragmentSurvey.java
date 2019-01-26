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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.midok.drbakhsh.Model.Survey;
import com.example.midok.drbakhsh.Presenter.AdapterSurvey;
import com.example.midok.drbakhsh.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class FragmentSurvey extends Fragment {

    ArrayList<Survey> mSurvey;
    Survey survey;
    RecyclerView recyclerView;
    AdapterSurvey adapterSurvey;
    FloatingActionButton addSurvey;
    String date, doctorName;
    EditText dateET;
    Dialog addSurveyDialog, surveyForumDialog , generalCommentsDialog;
    int questionId, surveyOptionsTester;
    String[] questions;
    TextView questionIdTV, questionTV;
    ImageView weakIMG, badIMG, goodIMG, excelentIMG, fairIMG;


    public FragmentSurvey() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_survey, container, false);

        recyclerView = view.findViewById(R.id.survey_rv);
        addSurvey = view.findViewById(R.id.add_survey);

        mSurvey = new ArrayList<>();
        survey = new Survey();
        for (int i = 1; i <= 3; i++) {
            survey.setDoctorName("Abdul Wadood");
            survey.setDate("22/7/1445");
            mSurvey.add(survey);
        }
        adapterSurvey = new AdapterSurvey(mSurvey);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterSurvey);

        addSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSurveyDialog = new Dialog(getContext());
                addSurveyDialog.setContentView(R.layout.diolog_add_survey);
                addSurveyDialog.setCancelable(true);
                addSurveydialogContents(addSurveyDialog);
                addSurveyDialog.show();
            }

            private void addSurveydialogContents(Dialog dialog) {
                final Spinner doctorNameSPN = dialog.findViewById(R.id.doctor_name_spn);
                dateET = dialog.findViewById(R.id.date_et);
                TextView proceedTV = dialog.findViewById(R.id.proceed);

                ArrayAdapter doctorsNameAdapter = ArrayAdapter.createFromResource(getContext(),
                        R.array.doctor_names, android.R.layout.simple_spinner_item);
                doctorsNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                doctorNameSPN.setAdapter(doctorsNameAdapter);
                doctorNameSPN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        doctorName = doctorNameSPN.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                final Calendar calendar = Calendar.getInstance();
                final DatePickerDialog.OnDateSetListener dialog1 = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String myFormat = "dd/MM/yyyy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        date = sdf.format(calendar.getTime());
                        dateET.setText(date);
                    }
                };

                dateET.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog(getContext(), dialog1, calendar
                                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });

                proceedTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        date = dateET.getText().toString();
                        if (!date.isEmpty()) {
                            surveyForumDialog = new Dialog(getContext());
                            surveyForumDialog.setContentView(R.layout.dialog_survey_forum);
                            surveyForumDialog.setCancelable(true);
                            addSurveyDialog.dismiss();
                            surveyForumDialogContents(surveyForumDialog);
                            surveyForumDialog.show();
                        } else
                            dateET.setError("Please enter the date");
                    }
                });
            }

            private void surveyForumDialogContents(Dialog dialog) {
                questionId = 1;
                surveyOptionsTester = 0;
                questionIdTV = dialog.findViewById(R.id.question_number);
                questionTV = dialog.findViewById(R.id.question);
                weakIMG = dialog.findViewById(R.id.weak_img);
                badIMG = dialog.findViewById(R.id.bad_img);
                fairIMG = dialog.findViewById(R.id.fair_img);
                goodIMG = dialog.findViewById(R.id.good_img);
                excelentIMG = dialog.findViewById(R.id.excelent_img);
                TextView nextTV = dialog.findViewById(R.id.next_tv);

                questions = getContext().getResources().getStringArray(R.array.survey_questions);

                formatDialog();

                nextTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (surveyOptionsTester == 1) {
                            formatDialog();
                            setAllImgToDefault();
                            surveyOptionsTester = 0;
                        } else {
                            Toast.makeText(getContext(), R.string.choose_option, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            public void formatDialog() {
                if (questionId <= questions.length) {
                    questionIdTV.setText("Question " + String.valueOf(questionId));
                    questionTV.setText(questions[questionId - 1]);

                    goodIMG.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setAllImgToDefault();
                            goodIMG.setImageResource(R.drawable.ic_smiling_colored);
                            surveyOptionsTester = 1;
                        }
                    });

                    weakIMG.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setAllImgToDefault();
                            weakIMG.setImageResource(R.drawable.ic_angry_colored);
                            surveyOptionsTester = 1;
                        }
                    });

                    badIMG.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setAllImgToDefault();
                            badIMG.setImageResource(R.drawable.ic_sad_colored);
                            surveyOptionsTester = 1;
                        }
                    });

                    excelentIMG.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setAllImgToDefault();
                            excelentIMG.setImageResource(R.drawable.ic_happy_colored);
                            surveyOptionsTester = 1;
                        }
                    });

                    fairIMG.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setAllImgToDefault();
                            fairIMG.setImageResource(R.drawable.ic_confused_colored);
                            surveyOptionsTester = 1;
                        }
                    });
                    questionId++;
                } else {

                    generalCommentsDialog = new Dialog(getContext());
                    generalCommentsDialog.setContentView(R.layout.diolog_general_comments);
                    generalCommentsDialog.setCancelable(true);
                    surveyForumDialog.dismiss();
                    generalCommentsDialogContents(generalCommentsDialog);
                    generalCommentsDialog.show();
                }
            }

            private void generalCommentsDialogContents(final Dialog generalCommentsDialog) {

                TextView submit = generalCommentsDialog.findViewById(R.id.submit);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Survey survey = new Survey(date, doctorName);
                        mSurvey.add(survey);
                        recyclerView.setAdapter(adapterSurvey);
                        generalCommentsDialog.dismiss();
                    }
                });

            }

            private void setAllImgToDefault() {
                weakIMG.setImageResource(R.drawable.ic_angry_grey);
                goodIMG.setImageResource(R.drawable.ic_smiling_grey);
                badIMG.setImageResource(R.drawable.ic_sad_grey);
                excelentIMG.setImageResource(R.drawable.ic_happy_grey);
                fairIMG.setImageResource(R.drawable.ic_confused_grey);
            }
        });

        return view;
    }

}
