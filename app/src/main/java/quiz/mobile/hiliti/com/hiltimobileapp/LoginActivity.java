package quiz.mobile.hiliti.com.hiltimobileapp;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import quiz.mobile.hiliti.com.hiltimobileapp.constants.UrlEndpoints;
import quiz.mobile.hiliti.com.hiltimobileapp.json.Endpoints;
import quiz.mobile.hiliti.com.hiltimobileapp.network.VolleySingleton;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.UserProfile;
import quiz.mobile.hiliti.com.hiltimobileapp.utility.SessionManager;

import static android.support.design.widget.Snackbar.LENGTH_LONG;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private Button loginButton;
    private EditText emailEditText;
    private EditText passwordEditText;
    private ProgressDialog progressDialog;
    private SessionManager sessionManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditText = (EditText) findViewById(R.id.email);
        passwordEditText = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.btnLogin);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        sessionManager = new SessionManager(getApplicationContext());

       /* if (sessionManager.isLoggedIn()) {
            quiz.mobile.hiliti.com.hiltimobileapp.logging.Log.m("Already session logged. So no more log into");

        if (sessionManager.isLoggedIn()) {

            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }*/


        // Login button Click Event
        loginButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Check for empty data in the form
                // Prompt user to enter credentials
                if (!email.isEmpty() && !password.isEmpty()) {
                    // login user
                    checkLogin(email, password);
                } else {
                    Snackbar.make(findViewById(R.id.loginView), "Enter the credentials!", LENGTH_LONG).show();
                }
            }

        });
    }
        //function to verify login

    private void checkLogin(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        progressDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                UrlEndpoints.LOGIN_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();
                if (response != null) {
                    try {
                        JSONObject jObj = new JSONObject(response);
                        UserProfile userProfile = new UserProfile();


                        // user successfully logged in
                        // Create login session
                        sessionManager.setLogin(true);
                        //set profile details in shared preference
                        sessionManager.setUserCred(jObj.getString("firstName"), jObj.getString("email"), jObj.getString("displayPic"));

                        userProfile.setEmpId(jObj.getInt("empid"));
                        userProfile.setPassword(jObj.getString("password"));
                        userProfile.setDisplayPic(Endpoints.getImageFromServer(jObj.getString("displayPic")));
                        userProfile.setFirstName(jObj.getString("firstName"));
                        userProfile.setLastName(jObj.getString("lastName"));
                        userProfile.setDepartment(jObj.getString("department"));
                        userProfile.setEmail(jObj.getString("email"));
                        userProfile.setAsOfDate(jObj.getString("asOfDate"));

                        Intent intent = new Intent(LoginActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (JSONException e) {
                        // JSON error
                        e.printStackTrace();

                        Toast.makeText(getApplicationContext(),"Invalid credentials", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    // Error in login. Get the error message

                    Toast.makeText(getApplicationContext(),
                            "Error in login", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

                @Override
                protected Map<String, String> getParams() {
                    // Posting parameters to login url
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", email);
                    params.put("password", password);

                    return params;
                }

        };

        // Adding request to request queue
        VolleySingleton.getvSingletonInstance().addToRequestQueue(strReq);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }



}





