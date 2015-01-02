package com.newtpond.ribbt;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class EditProfileActivity extends ActionBarActivity {

    protected ParseUser mCurrentUser;
    protected EditText mFirstName;
    protected EditText mLastName;
    protected EditText mHometown;
    protected EditText mWebsite;
    protected EditText mEmail;
    protected Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mEmail = (EditText) findViewById(R.id.emailField);
        mFirstName = (EditText) findViewById(R.id.nameField);
        mLastName = (EditText) findViewById(R.id.lastNameField);
        mHometown = (EditText) findViewById(R.id.hometown);
        mWebsite = (EditText) findViewById(R.id.website);
        mSaveButton = (Button)findViewById(R.id.saveButton);

        mCurrentUser = ParseUser.getCurrentUser();

        ((TextView) findViewById(R.id.usernameField))
                .setText(mCurrentUser.getUsername());

        mEmail.setText(mCurrentUser.getEmail());
        mFirstName.setText(mCurrentUser.getString("firstName"));
        mLastName.setText(mCurrentUser.getString("lastName"));
        mHometown.setText(mCurrentUser.getString("hometown"));
        mWebsite.setText(mCurrentUser.getString("website"));

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();
                String hometown = mHometown.getText().toString();
                String website = mWebsite.getText().toString();
                String email = mEmail.getText().toString();

                email = email.trim();
                firstName = firstName.trim();
                lastName = lastName.trim();
                hometown = hometown.trim();
                website = website.trim();


                if(email.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
                    builder.setMessage(R.string.edit_profile_error_message)
                            .setTitle(R.string.signup_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    mCurrentUser.setEmail(email);
                    mCurrentUser.put("firstName", firstName);
                    mCurrentUser.put("lastName", lastName);
                    mCurrentUser.put("hometown", hometown);
                    mCurrentUser.put("website", website);

                    mCurrentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
                                builder.setMessage(e.getMessage())
                                        .setTitle(R.string.signup_error_title)
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
                }
            }
        });

    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
