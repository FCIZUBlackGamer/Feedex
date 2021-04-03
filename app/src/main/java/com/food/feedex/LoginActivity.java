package com.food.feedex;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.food.Utils.CheckForNetwork;
import com.food.Utils.InputValidation;
import com.food.Utils.PreferenceController;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class LoginActivity extends BaseActivity {

    private ImageView bookIconImageView;
    private TextView bookITextView, signupTextView, forgetPasswordTv;
    private ProgressBar loadingProgressBar;
    private RelativeLayout rootView, afterAnimationView;
    private Button btn_loginButton;
    private TextInputEditText email, password;
//    APIViewModel apiViewModel;
    LayoutInflater inflate;

    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
//        apiViewModel = ViewModelProviders.of(this).get(APIViewModel.class);
        initViews();
        inflate = getLayoutInflater();
        gson = new GsonBuilder()
                .serializeNulls()
                .create();

        setObserver();
//        Log.e("ID",PreferenceController.getInstance(this).get(PreferenceController.PREF_USER_ID)+"&");
        if (PreferenceController.getInstance(this).get(PreferenceController.PREF_USER_ID) == null || PreferenceController.getInstance(this).get(PreferenceController.PREF_USER_ID).isEmpty()) {
            PreferenceController.getInstance(this).persist(PreferenceController.PREF_USER_ID, "0");
        }
        if (!PreferenceController.getInstance(this).get(PreferenceController.PREF_USER_ID).equals("0")) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

//        if (PreferenceController.getInstance(this).get(PreferenceController.LANGUAGE) == null || PreferenceController.getInstance(this).get(PreferenceController.LANGUAGE).isEmpty()) {
//            PreferenceController.getInstance(this).persist(PreferenceController.LANGUAGE, Constants.ENGLISH);
//        }
        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        forgetPasswordTv.setOnClickListener(view -> {
            if (CheckForNetwork.isConnectionOn(this)) {
                View change = inflate.inflate(R.layout.forget_password, null);
                TextInputEditText tietEmail = change.findViewById(R.id.tietEmail);
                builder = new AlertDialog.Builder(this)
                        .setTitle("Forget Password")
                        .setMessage("We will send an important message to your email!")
                        .setView(change)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                User user = new User();
//                                if (InputValidation.isValidEmail(tietEmail.getText().toString().replace(" ", ""))) {
//                                    user.setEmail(tietEmail.getText().toString());
//                                    apiViewModel.forgetPassword(com.food.view.LoginActivity.this, gson.toJson(user));
                                    dialog.dismiss();
//                                } else {
//                                    tietEmail.setError("Invalid Email!");
//                                }
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert);

                alertDialog = builder.create();
                alertDialog.show();
            }else {
                Snackbar snack = Snackbar.make(findViewById(R.id.rootView).getRootView(), R.string.network_connection, Snackbar.LENGTH_INDEFINITE);
                snack.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snack.dismiss();
                    }
                });
                View view1 = snack.getView();
                FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view1.getLayoutParams();
                params.gravity = Gravity.TOP;
                view1.setLayoutParams(params);
                snack.show();
            }


        });
        btn_loginButton.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            if (CheckForNetwork.isConnectionOn(this)) {
//                findViewById(R.id.loadingPanel).setVisibility(VISIBLE);
//                if (valid()) {
//                    PreferenceController.getInstance(this).persist(PreferenceController.PREF_EDIT_MODE, "OFF");
////                    apiViewModel.login(this, email.getText().toString(), password.getText().toString(), deviceToken);
//                } else {
//                    findViewById(R.id.loadingPanel).setVisibility(GONE);
//                }
//            }else {
//                Snackbar snack = Snackbar.make(findViewById(R.id.rootView).getRootView(), R.string.network_connection, Snackbar.LENGTH_INDEFINITE);
//                View view1 = snack.getView();
//                FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view1.getLayoutParams();
//                params.gravity = Gravity.TOP;
//                view1.setLayoutParams(params);
//                snack.show();
//            }

        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bookITextView.setVisibility(GONE);
                loadingProgressBar.setVisibility(GONE);
                rootView.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.white));
                //bookIconImageView.setImageResource(R.drawable.zlogo);
                startAnimation();
            }
        }, 5000);

    }

    private void setObserver() {
//        apiViewModel.login(this, email.getText().toString(), password.getText().toString(), deviceToken).observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
////                Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
//                Log.e("Result", s + "");
//                if (s != null) {
//                    if (s.equals("404")) {
//
//                    } else if (s.equals("empty")) {
//
//                    } else {
//                        Log.e("login response", s);
//                        Gson gson = new Gson();
//                        User u = gson.fromJson(s.replace("\\", ""), User.class);
////                        Log.e("email ", email.getText().toString());
////                        Log.e("password ", password.getText().toString());
//                        if (u.getId() != 0) {
//                            PreferenceController.getInstance(LoginActivity.this).persist(PreferenceController.PREF_USER_ID, String.valueOf(u.getId()));
//                            PreferenceController.getInstance(LoginActivity.this).persist(PreferenceController.PREF_USER_NAME, String.valueOf(u.getName()));
//                            PreferenceController.getInstance(LoginActivity.this).persist(PreferenceController.PREF_EMAIL, String.valueOf(u.getEmail()));
//                            PreferenceController.getInstance(LoginActivity.this).persist(PreferenceController.PREF_USER_PASSWORD, password.getText().toString().trim());
//
////                            Log.e("VK1",String.valueOf(sharedPreferences.getInt(SharedPreferences_Key, 0)));
////                            FirebaseMessaging.getInstance().subscribeToTopic(s);//UserId
//                            Log.e("Response", (s));
//                            Toast.makeText(LoginActivity.this, "Welcome Home!", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                        } else {
//                            //textView.setText(serverResponse.toString());
//                            Log.e("Err", "Empty");
//
//                            new AlertDialog.Builder(LoginActivity.this)
//                                    .setMessage("اسم مستخدم او كلمة مرور خاطئة")
//                                    .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss())
//                                    .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss())
//                                    .create()
//                                    .show();
//                        }
//                    }
//                    findViewById(R.id.loadingPanel).setVisibility(GONE);
//
//                } else {
//                    Log.e("login response", "fail");
//                    Toast.makeText(com.food.view.LoginActivity.this, "Server is busy now, please call client services or try again", Toast.LENGTH_SHORT).show();
//                    findViewById(R.id.loadingPanel).setVisibility(GONE);
//
//                }
//            }
//        });
    }

    private boolean valid() {
        if (InputValidation.isValidEmail(email.getText().toString().replace(" ", ""))) {
//            if (InputValidation.isValidPassword(password.getText().toString())) {
            return true;
//            } else {
//                password.setError("Invalid Password");
//                return false;
//            }
        } else {
            email.setError("Invalid Email");
            return false;
        }
    }


    private void initViews() {
        bookIconImageView = findViewById(R.id.bookIconImageView);
        bookITextView = findViewById(R.id.bookITextView);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        rootView = findViewById(R.id.rootView);
        btn_loginButton = findViewById(R.id.loginButton);
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        afterAnimationView = findViewById(R.id.afterAnimationView);
        signupTextView = findViewById(R.id.signupTextView);
        forgetPasswordTv = findViewById(R.id.tvForgoPassword);
        findViewById(R.id.loadingPanel).setVisibility(GONE);
    }

    private void startAnimation() {
        ViewPropertyAnimator viewPropertyAnimator = bookIconImageView.animate();
//        viewPropertyAnimator.x(25f);
        viewPropertyAnimator.y(50f);
        viewPropertyAnimator.setDuration(1000);
        viewPropertyAnimator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                afterAnimationView.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
