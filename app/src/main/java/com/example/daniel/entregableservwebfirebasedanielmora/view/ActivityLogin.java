package com.example.daniel.entregableservwebfirebasedanielmora.view;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.daniel.entregableservwebfirebasedanielmora.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ActivityLogin extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton loginButtonFacebook;
    private static final String TAG = ActivityLogin.class.getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText editTextMail;
    private EditText editTextPass;
    private Button botonCrear;
    private Button botonLogin;
    private ImageView fotoPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //  printHash();
        mAuth = FirebaseAuth.getInstance();

        fotoPerfil = findViewById(R.id.foto_perfil_usuario_id);
        editTextMail = findViewById(R.id.edit_text_email);
        editTextPass = findViewById(R.id.edit_text_pass);
        botonCrear = findViewById(R.id.boton_crear_usuario);
        botonLogin = findViewById(R.id.boton_login_usuario);

        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUsuario(editTextMail.getText().toString(), editTextPass.getText().toString());
            }
        });

        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearUsuario(editTextMail.getText().toString(), editTextPass.getText().toString());
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //El usuario ha iniciado sesión
                    Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                    startActivity(intent);
                    Log.d("facebook", "onAuthStateChanged:signed_out");


                } else {
                    //El usuario está desconectado
                    Log.d("facebook", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        cargarFotoDelUsuario();

        callbackManager = CallbackManager.Factory.create();
        loginButtonFacebook = findViewById(R.id.login_button);
        loginButtonFacebook.setReadPermissions("email", "public_profile");
        loginButtonFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });
    }

    AccessToken accessToken = AccessToken.getCurrentAccessToken();
    boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

    private void cargarFotoDelUsuario() {
        if (Profile.getCurrentProfile() != null) {
            Uri photoUri = Profile.getCurrentProfile().getProfilePictureUri(200, 200);
            Picasso.get().load(photoUri).into(fotoPerfil);
        }
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(ActivityLogin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void printHash() {
        try {
            PackageInfo info =
                    getPackageManager().getPackageInfo(getApplicationContext().getPackageName(),
                            PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.v("MY KEY HASH:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //Compruebe si el usuario ha iniciado sesión (no nulo) y actualizar UI en consecuencia.
        mAuth.addAuthStateListener(mAuthListener);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        // Pase el resultado de la actividad al SDK de Facebook
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void crearUsuario(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Inicie sesión con éxito, actualice la IU con la información del usuario que inició sesión.
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            //Si el inicio de sesión falla, muestre un mensaje al usuario
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(ActivityLogin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
            startActivity(intent);
        }
    }

    private void loginUsuario(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Inicie sesión con éxito, actualice la IU con la información del usuario que inició sesión.
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            //Si el inicio de sesión falla, muestre un mensaje al usuario.
                            Toast.makeText(ActivityLogin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        // ...
                    }
                });
    }
}