package com.example.nanny

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.room.Room
import com.example.nanny.data.UserData
import com.example.nanny.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.io.File

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var database: UserData
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var file: File
    private val db = FirebaseFirestore.getInstance()
    private var id: String? = ""
    private var bitmap: Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        firebaseAuth = Firebase.auth


        database = Room.databaseBuilder(
            application, UserData::class.java, UserData.DATABASE_NAME
        ).allowMainThreadQueries().build()


        setContentView(binding.root)


        binding.labelTakeAPhotoRegister.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                it.resolveActivity(packageManager).also { component ->
                    photoFile()
                    val photoUri: Uri = FileProvider.getUriForFile(
                        this, BuildConfig.APPLICATION_ID + ".fileprovider", file
                    )
                    it.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                }
            }
            openCamera.launch(intent)
        }

        binding.btnSaveRegister.setOnClickListener {
            val emailValue = binding.inputEmailRegister.text.toString()
            val passwordValue = binding.inputPasswordRegister.text.toString()

            if (emailValue.isNotEmpty() && passwordValue.isNotEmpty()) {
                if (passwordValue.length >= 6) {
                    userRegisterFB(emailValue, passwordValue)

                } else {
                    Toast.makeText(this, "clave debe tener mas de 6 caracteres", Toast.LENGTH_LONG)
                        .show()
                }
                //val bundle = intent.extras
                //val correo: String? = bundle?.getString("correo")
                //binding.inputEmailRegister.setText(email)

            }
        }
    }


    private fun photoFile() {
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        file = File.createTempFile("IMG_${System.currentTimeMillis()}_", ".jpg", dir)
    }

    private fun saveDataFBStorage(id: String) {
        db.collection("appUser").document(id).set(
            hashMapOf(
                "names" to binding.inputNamesRegister.text.toString(),
                "address" to binding.inputAdressRegister.text.toString(),
                "phone" to binding.inputPhoneRegister.text.toString(),
                "roll" to binding.inputRollRegister.text.toString()//,
                //"picture" to binding.inputImageRegister.setImageBitmap(bitmap)
            )
        )
    }


    private fun userRegisterFB(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    id = firebaseAuth.uid
                    id?.let { saveDataFBStorage(it) }
                    id = ""
                    Toast.makeText(this, "Datos registrados ", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(this, "Los datos no pueden estar vacios", Toast.LENGTH_LONG)
                        .show()
                }
            }
    }

    val openCamera =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data!!
                bitmap = BitmapFactory.decodeFile(file.toString())
                binding.inputImageRegister.setImageBitmap(bitmap)
            }
        }

}