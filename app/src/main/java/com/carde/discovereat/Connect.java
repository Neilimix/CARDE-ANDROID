package com.carde.discovereat;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

public class Connect extends AppCompatActivity{

    ////////////////FB//////////////////
    private CallbackManager callbackManager;
    private TextView info;
    private LoginButton loginButton;
    private ProfilePictureView profilePictureView;
    private Button SignInLocal;
    private TextView ignore;

    ///////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ////////////////////FB///////////////
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_connect);
        info = (TextView)findViewById(R.id.info);
        profilePictureView = (ProfilePictureView) findViewById(R.id.friendProfilePicture);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        SignInLocal = (Button)findViewById(R.id.sign_in_button);
        ignore=(TextView)findViewById(R.id.ignore);

        ignore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ignore = new Intent(Connect.this, Default.class);
                startActivity(ignore);
            }
        });

        SignInLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent connect = new Intent(Connect.this, LoginActivity.class);
                startActivity(connect);
            }
        });

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                info.setText("User ID:  " +
                        loginResult.getAccessToken().getUserId() + "\n" +
                        "Auth Token: " + loginResult.getAccessToken().getToken());
                profilePictureView.setProfileId(loginResult.getAccessToken().getUserId());

            }

            @Override
            public void onCancel() {
                info.setText("Login attempt cancelled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed.");
            }
        });
        //////////////////////////////////////////
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
