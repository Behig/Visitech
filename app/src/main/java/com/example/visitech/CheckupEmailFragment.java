package com.example.visitech;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class CheckupEmailFragment extends Fragment {
    private static final String TAG = "CheckupEmailFragment";
    private Patient patient;
    private Checkup checkup;
    private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_checkup_email, container, false);

        checkup = getArguments().getParcelable("selectedCheckupToMail");
        patient = getArguments().getParcelable("selectedPatientToMail");

        mEditTextTo = (EditText) v.findViewById(R.id.edit_text_to);
        mEditTextSubject = (EditText) v.findViewById(R.id.edit_text_subject);
        mEditTextMessage = (EditText) v.findViewById(R.id.edit_text_Message);
        Button buttonSend = (Button) v.findViewById(R.id.btn_send_email);

        mEditTextSubject.setText(String.format("Checkup of %s, %s on %s.%s.%s", patient.getLastName(), patient.getFirstName(), checkup.getDate()[2], checkup.getDate()[1], checkup.getDate()[0]));
        mEditTextMessage.setText(String.format("Date of checkup: %s.%s.%s\nDone by: Dr %s\n\nDescription: %s\n\nFindings: %s\n", checkup.getDate()[2], checkup.getDate()[1], checkup.getDate()[0], checkup.getDoctor(), checkup.getAreas(), checkup.getFindings()));

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        return v;
    }

    private void sendMail() {
        String recipientList = mEditTextTo.getText().toString();
        String[] recipients = recipientList.split(",");
        String message = mEditTextMessage.getText().toString();
        String subject = mEditTextSubject.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an e-mail client"));
    }


    /*
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu_mail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }*/

}