package ac.id.del.delifood.activities

import ac.id.del.delifood.R
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowInsets
import android.view.WindowManager
import ac.id.del.delifood.utils.InterTextViewBold
import ac.id.del.delifood.utils.InterEditText
import ac.id.del.delifood.utils.InterButton
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        setupActionBar()

        val textLogin: InterTextViewBold = findViewById(R.id.txt_login)
        textLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        val buttonRegister: InterButton = findViewById(R.id.button_register)
        buttonRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun setupActionBar() {
        val toolbarRegisterActivity: Toolbar = findViewById(R.id.toolbar_register_activity)

        setSupportActionBar(toolbarRegisterActivity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        }

        toolbarRegisterActivity.setNavigationOnClickListener { onBackPressed() }
    }

    /**
     * A function to validate the entries of a new user
     */
    private fun validateRegisterDetails(): Boolean {

        val etFirstName: InterEditText = findViewById(R.id.et_first_name)
        val etLastName: InterEditText = findViewById(R.id.et_last_name)
        val etEmail: InterEditText = findViewById(R.id.et_email)
        val etPassword: InterEditText = findViewById(R.id.et_password)
        val etConfirmPassword: InterEditText = findViewById(R.id.et_confirm_password)

        return when {
            TextUtils.isEmpty(etFirstName.text.toString().trim { it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_first_name), true)
                false
            }
            TextUtils.isEmpty(etLastName.text.toString().trim { it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_last_name), true)
                false
            }
            TextUtils.isEmpty(etEmail.text.toString().trim { it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(etPassword.text.toString().trim { it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            TextUtils.isEmpty(etConfirmPassword.text.toString().trim { it <= ' '}) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_confirm_password), true)
                false
            }
            etPassword.text.toString().trim { it <= ' ' } != etConfirmPassword.text.toString()
                .trim {  it <= ' ' } -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_password_and_confirm_password_mismatch), true)
                false
            }
            else -> {
//                showErrorSnackBar(resources.getString(R.string.registery_successfull), false)
                true
            }
        }
    }

    private fun registerUser() {
        val etEmail: InterEditText = findViewById(R.id.et_email)
        val etPassword: InterEditText = findViewById(R.id.et_password)

        if(validateRegisterDetails()) {
            showProgresssDialog()

            val email: String = etEmail.text.toString().trim {  it <= ' ' }
            val password: String = etPassword.text.toString().trim {  it <= ' ' }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->

                        hideProgresssDialog()

                        // If the registration is succesfullty done
                        if (task.isSuccessful) {
                            val firebaseUser: FirebaseUser = task.result!!.user!!

                            showErrorSnackBar(
                                "Kamu telah barhasil mendaftar. User Id kamu adalah ${firebaseUser.uid}",
                                false
                            )

                            FirebaseAuth.getInstance().signOut()
                            finish()
                        } else {
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                    }
                )
        }
    }
}