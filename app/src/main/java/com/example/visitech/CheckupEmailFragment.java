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

/**
 * A Fragment to send a single checkup report as an E-Mail.
 *
 * This fragment uses three EditText fields to get the message, the subject and the receiver.
 *
 * @author Mohammad Goudarzi Moghadam
 */
public class CheckupEmailFragment extends Fragment {
    private static final String TAG = "CheckupEmailFragment";
    /**
     * The selected patient.
     */
    private Patient patient;
    /**
     * The checkup to send.
     */
    private Checkup checkup;
    private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_checkup_email, container, false);

        // Get patient and selected checkup from last fragment.
        checkup = getArguments().getParcelable("selectedCheckupToMail");
        patient = getArguments().getParcelable("selectedPatientToMail");

        // Initialize EditText fields.
        mEditTextTo = (EditText) v.findViewById(R.id.edit_text_to);
        mEditTextSubject = (EditText) v.findViewById(R.id.edit_text_subject);
        mEditTextMessage = (EditText) v.findViewById(R.id.edit_text_Message);

        // The button to send the Email.
        Button buttonSend = (Button) v.findViewById(R.id.btn_send_email);

        // Set the text of fields subject and message, as the are known.
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

    /**
     * This method makes an Email ready to send via an Email client like Gmail.
     */
    private void sendMail() {
        // Create the list of recipients, which are given separated by a comma.
        String recipientList = mEditTextTo.getText().toString();
        String[] recipients = recipientList.split(",");

        // Set message and subject of the Email.
        String message = mEditTextMessage.getText().toString();
        String subject = mEditTextSubject.getText().toString();

        // Store the information in an intent to send.
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        // Open the email client to send the given information.
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an e-Mail client"));
    }


}