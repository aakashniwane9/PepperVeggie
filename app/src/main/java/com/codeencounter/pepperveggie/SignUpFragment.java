package com.codeencounter.pepperveggie;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */

public class SignUpFragment extends Fragment {

    private TextView alreadyhaveanaccount;
    private FrameLayout parentframelayout;
    private ProgressBar progressBar;
    private EditText email,password,cnf_password,name;
    private Button signupbtn;
    private FirebaseAuth firebaseAuth;
    private ImageButton imageButton;
    private String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    public SignUpFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        alreadyhaveanaccount = view.findViewById(R.id.tv_already_have_an_account);
        progressBar = view.findViewById(R.id.signup_progressbar);
        progressBar.setVisibility(View.GONE);
        parentframelayout = getActivity().findViewById(R.id.register_framelayout);
        email = view.findViewById(R.id.signup_email);
        name = view.findViewById(R.id.signup_name);
        password = view.findViewById(R.id.signup_password);
        cnf_password = view.findViewById(R.id.signup_confirm_password);
        imageButton = view.findViewById(R.id.signup_close_button);
        signupbtn = view.findViewById(R.id.signup_button);
        firebaseAuth = FirebaseAuth.getInstance();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alreadyhaveanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignInFragment());
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        cnf_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEmailAndPassword();
            }
        });

    }

    private void checkEmailAndPassword() {
        if(email.getText().toString().matches(emailpattern)){
            if(password.getText().toString().equals(cnf_password.getText().toString())){
                progressBar.setVisibility(View.VISIBLE);
                signupbtn.setEnabled(false);
                signupbtn.setTextColor(Color.argb(50,255,255,255));
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString().trim(),password.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(getActivity(),HomePage.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                }else {
                                    progressBar.setVisibility(View.GONE);
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
                                    signupbtn.setEnabled(true);
                                    progressBar.setVisibility(View.GONE);
                                    signupbtn.setTextColor(Color.rgb(255,255,255));
                                }
                            }
                        });
            }else
                cnf_password.setError("Password does not match, try again!");
        }else
            email.setError("Invalid email");
    }

    private void checkInputs() {
        if(!TextUtils.isEmpty(email.getText())){
            if (!TextUtils.isEmpty(name.getText())){
                if (!TextUtils.isEmpty(password.getText())){
                    if (!TextUtils.isEmpty(cnf_password.getText())){
                        signupbtn.setEnabled(true);
                        signupbtn.setTextColor(Color.rgb(255,255,255));
                    }else{
                        signupbtn.setEnabled(false);
                        signupbtn.setTextColor(Color.argb(50,255,255,255));
                    }
                }else{
                    signupbtn.setEnabled(false);
                    signupbtn.setTextColor(Color.argb(50,255,255,255));
                }
            }else{
                signupbtn.setEnabled(false);
                signupbtn.setTextColor(Color.argb(50,255,255,255));
            }
        }else{
            signupbtn.setEnabled(false);
            signupbtn.setTextColor(Color.argb(50,255,255,255));
        }

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slideout_from_right,R.anim.slide_from_left);
        fragmentTransaction.replace(parentframelayout.getId() , fragment);
        fragmentTransaction.commit();
    }
}
