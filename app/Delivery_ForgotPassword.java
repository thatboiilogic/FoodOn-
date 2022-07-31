package my.foodon.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import my.foodon.app.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class Delivery_ForgotPassword extends AppCompatActivity {

    TextInputLayout forgetpassword;
    Button Reset;
    FirebaseAuth FAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_forgot_password);

        forgetpassword = (TextInputLayout) findViewById(R.id.forgotEmailid);
        Reset = (Button) findViewById(R.id.forgotreset);

        FAuth = FirebaseAuth.getInstance();
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(Delivery_ForgotPassword.this);
                mDialog.setCancelable(false);
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.setMessage("Logging in...");
                mDialog.show();


                FAuth.sendPasswordResetEmail(forgetpassword.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mDialog.dismiss();
                            my.foodon.app.ReusableCodeForAll.ShowAlert(Delivery_ForgotPassword.this, "", "Password has been sent to your Email");
                        } else {
                            mDialog.dismiss();
                            my.foodon.app.ReusableCodeForAll.ShowAlert(Delivery_ForgotPassword.this, "Error", task.getException().getMessage());
                        }
                    }
                });
            }
        });

    }
}
