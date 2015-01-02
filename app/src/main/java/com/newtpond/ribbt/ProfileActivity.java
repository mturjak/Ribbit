package com.newtpond.ribbt;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class ProfileActivity extends ActionBarActivity {

    public static final String TAG = ProfileActivity.class.getSimpleName();

    protected ParseUser mUser;
    protected TextView mFirstName;
    protected TextView mLastName;
    protected TextView mHometown;
    protected TextView mWebsite;
    protected TextView mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mFirstName = (TextView) findViewById(R.id.nameField);
        mLastName = (TextView) findViewById(R.id.lastNameField);
        mHometown = (TextView) findViewById(R.id.hometown);
        mWebsite = (TextView) findViewById(R.id.website);
        mUsername = (TextView) findViewById(R.id.usernameField);

        Intent intent = getIntent();

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo(ParseConstants.KEY_USERNAME, intent.getStringExtra(ParseConstants.KEY_USERNAME));
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if(e == null) {

                    mUser = users.get(0);

                    mUsername.setText(mUser.getUsername());
                    mFirstName.setText(mUser.getString("firstName"));
                    mLastName.setText(mUser.getString("lastName"));
                    mHometown.setText(mUser.getString("hometown"));
                    mWebsite.setText(mUser.getString("website"));
                } else {
                    Log.e(TAG, e.getMessage());
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                    builder.setMessage(e.getMessage())
                            .setTitle(R.string.edit_friends_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // go back instead of up
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
