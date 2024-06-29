package zazueta.marcos.firebaselogin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
//import zazueta.marcos.firebaselogin.databinding.ActivitySignInBinding


class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        auth = Firebase.auth

        val btn_ingresar: Button = findViewById(R.id.btn_ingresar)

        btn_ingresar.setOnClickListener {

            val et_correo: EditText = findViewById(R.id.et_correo)
            val et_contra: EditText = findViewById(R.id.et_contra)

            var correo: String = et_correo.text.toString()
            var contra: String = et_contra.text.toString()

            if(!correo.isNullOrBlank() && !contra.isNullOrBlank()){
                signIn(correo, contra)
            }else{
                Toast.makeText(this, "Ingresar datos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signIn(email: String, password: String){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = auth.currentUser
                    reaload()

                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
    }

    private fun reaload(){
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}