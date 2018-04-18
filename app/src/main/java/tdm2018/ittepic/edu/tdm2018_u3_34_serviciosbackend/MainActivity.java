package tdm2018.ittepic.edu.tdm2018_u3_34_serviciosbackend;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText usuario, contraseña;
    Button entrar, salir;
    ImageButton add;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario=(EditText)findViewById(R.id.editTextUsser);
        contraseña=(EditText)findViewById(R.id.editTextPass);
        entrar=(Button)findViewById(R.id.btnLog);
        salir=(Button)findViewById(R.id.btnSalir);
        add=(ImageButton)findViewById(R.id.ibAdd);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void Login(View v) {
        final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "Please wait...", "Proccessing...", true);

        (firebaseAuth.signInWithEmailAndPassword(usuario.getText().toString(), contraseña.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                            i.putExtra("mail", firebaseAuth.getCurrentUser().getEmail());
                            startActivity(i);
                        } else {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
    public void AddUser(View v){
        Intent intent=new Intent(MainActivity.this, Registro.class);
        startActivity(intent);
    }
    public void btnUserLogin_Click(View v) {
        final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "Por favor espere...", "Proccesando...", true);

        (firebaseAuth.signInWithEmailAndPassword(usuario.getText().toString(), contraseña.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login Completado!", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                            i.putExtra("mail", firebaseAuth.getCurrentUser().getEmail());
                            startActivity(i);
                        } else {
                            Log.e("ERROR", task.getException().toString());
                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}
