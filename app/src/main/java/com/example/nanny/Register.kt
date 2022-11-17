package com.example.nanny

import android.content.Context
import android.content.Intent
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
import com.example.nanny.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.io.File

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var database:UserData
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        firebaseAuth=Firebase.auth

        database= Room.databaseBuilder(
            application,UserData::class.java,UserData.DATABASE_NAME).allowMainThreadQueries().build()

        setContentView(binding.root)
        binding.labelTakeAPhotoRegister.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                it.resolveActivity(packageManager).also { component ->
                    photoFile()
                    val photoUri: Uri = FileProvider.getUriForFile(
                        this, BuildConfig.APPLICATION_ID + ".fileprovider", file
                    )
                    it.putExtra(MediaStore.EXTRA_OUTPUT,photoUri)
                }
            }
            openCamera.launch(intent)
        }


        fun savedata(){
            val user:String = binding.inputEmailRegister.text.toString()
            val password:String=binding.inputPasswordRegister.text.toString()
            val address:String=binding.inputAdressRegister.text.toString()
            val name:String=binding.inputNamesRegister.text.toString()
            val phone:String=binding.inputPhoneRegister.text.toString()
            val rol:String=binding.inputRollRegister.text.toString()

            val datos=getSharedPreferences("datauser",Context.MODE_PRIVATE)
            val editor=datos.edit()
            editor.putString("nombreusuario",user)
            editor.putString("claveusuario",password)
            editor.putString("addressUsuario",address)
            editor.putString("nameusuario",name)
            editor.putString("phoneusuario",phone)
            editor.putString("rolusuario",rol)
            editor.commit()

            startActivity(Intent(this,MainActivity::class.java))
        }

        binding.btnSaveRegister.setOnClickListener{
            //savedata()
            //saveuserDB()
            //Toast.makeText(this,"Datos guardados",Toast.LENGTH_LONG).show()
            val usu=binding.inputEmailRegister.text.toString()
            val key=binding.inputPasswordRegister.text.toString()
            if (usu.isNotEmpty()&& key.isNotEmpty()){
                if (key.length>=6){
                    userRegisterFB(usu,key)
                    Toast.makeText(this,"datos guardados",Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this,"clave debe tener mas de 6 caracteres",Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(this,"no deje valores vacios",Toast.LENGTH_LONG).show()
            }


        }

        val bundle=intent.extras
        val correo:String?=bundle?.getString("correo")
        //binding.inputEmailRegister.setText(email)
    }

    val openCamera =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data!!
                val bitmap= BitmapFactory.decodeFile(file.toString())
                binding.inputImageRegister.setImageBitmap(bitmap)
            }
        }


    private lateinit var file: File

    private fun photoFile() {
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        file = File.createTempFile("IMG_${System.currentTimeMillis()}_", ".jpg", dir)
    }

    /*private fun createContent() :ContentValues{
        val namefile = file.name
        val typeFile = "image/jpg"
        return ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, namefile)
            put(MediaStore.Files.FileColumns.MIME_TYPE, typeFile)
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            put(MediaStore.MediaColumns.IS_PENDING,1)
        }
    }


    private fun saveContent(content:ContentValues):Uri{
        var outputStream:OutputStream?
        var uri:Uri?
        application.contentResolver.also { resolver ->
            uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, content)
            outputStream = resolver.openOutputStream(uri!!)
        }
        outputStream.use {
            output->
            mappingImage().compress(Bitmap.CompressFormat.JPEG, 100,output)
        }
        return uri!!
    }

    private fun mappingImage():Bitmap{
        return BitmapFactory.decodeFile(file.toString())
    }


    private fun saveImage() {
        val save=createContent()
        val uri=saveContent(content)
    }


    private fun createPhotoFile() {
    }*/

fun saveuserDB(){
    val user = binding.inputEmailRegister.text.toString()
    val password=binding.inputPasswordRegister.text.toString()
    val address=binding.inputAdressRegister.text.toString()
    val name=binding.inputNamesRegister.text.toString()
    val phone=binding.inputPhoneRegister.text.toString()
    val rol=binding.inputRollRegister.text.toString()
    val usu=UserModel(user,password,address,name,phone,rol)
    database.userDao.insert(usu)


    }

    private fun userRegisterFB(email:String,password:String){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
            task->
                if (task.isSuccessful){
                    val id=firebaseAuth.uid
                    val intent=Intent(this,Register::class.java)
                    intent.putExtra("dataid",id)
                    intent.putExtra("correo",email)
                    startActivity(intent)
                    Toast.makeText(this,"datos registrados",Toast.LENGTH_LONG).show()
                }
            else{
                    Toast.makeText(this,"los datos no pueden estar vacios",Toast.LENGTH_LONG).show()
                }

        }
    }
}
