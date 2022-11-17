package com.example.nanny

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.nanny.data.UserData
import com.example.nanny.databinding.ActivityLoginBinding
import com.example.nanny.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {

    private lateinit var database: UserData
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        firebaseAuth=Firebase.auth
        setContentView(R.layout.activity_login)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        database= Room.databaseBuilder(
            application,UserData::class.java,UserData.DATABASE_NAME).allowMainThreadQueries().build()

        val view=binding.root
        setContentView(view)

        fun validate(){
            println("hola mundo")
            val datauser:String=binding.inputEmailLogin.text.toString()
            val datapass:String=binding.inputPasswordLogin.text.toString()

            val datos =getSharedPreferences("datauser",Context.MODE_PRIVATE)
            val user=datos.getString("nombreusuario","")
            val password=datos.getString("claveusuario","")
            if (user!!.isEmpty()){
                binding.inputEmailLogin.setHint("Ingrese el correo")
            }

            else if(password!!.isEmpty()){
                println("ingrese clave")
                //binding.inputPasswordLogin.setHint("ingrese clave")
                //binding.inputPasswordLogin.setHintTextColor(Color.RED)
                Toast.makeText(this,"ingrese clave",Toast.LENGTH_SHORT).show()
            }
            else if(user.equals(datauser) && password.equals(datapass)){
                println("Datos Correctos")
                val intent=Intent(this,User::class.java)
                intent.putExtra("nombre", datauser)
                startActivity(intent)
                Toast.makeText(this,"Datos Correctos",Toast.LENGTH_SHORT).show()
            }
            else {
                println("Datos incorrectos")
                binding.inputEmailLogin.setText("")
                Toast.makeText(this,"Datos incorrectos",Toast.LENGTH_SHORT).show()
            }

        }

        binding.btnEnterLogin.setOnClickListener{
            /*val usuario:String=binding.inputEmailLogin.text.toString()
            val clave:String=binding.inputPasswordLogin.text.toString()
            if (usuario=="pepe" && clave=="123"){
                startActivity(Intent(this,User::class.java))
            }
            else{
                Toast.makeText(this,"Datos incorrectos",Toast.LENGTH_SHORT).show()
            }*/
            //validateWithDB()
            //validate()
            authlogin(binding.inputEmailLogin.text.toString(),binding.inputPasswordLogin.text.toString())
        }
        binding.labelRecoveryPassLogin.setOnClickListener{
            startActivity(Intent(this,RecoveryKey::class.java))
        }
    }

    private fun authlogin(email:String,password:String){

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
            task->
            if (task.isSuccessful){
                Toast.makeText(this,"datos ok",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"datos incorrectos",Toast.LENGTH_LONG).show()
            }
        }


    }

    fun validateWithDB(){
        val user = binding.inputEmailLogin.text.toString()
        val password=binding.inputPasswordLogin.text.toString()
        val consultUser=database.userDao.consult(user)
        if (consultUser!=null){
            println(consultUser.pass)
            if (consultUser.pass==password){
                Toast.makeText(this,"datos ok",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,User::class.java))
            }
            else{
                Toast.makeText(this,"clave incorrecta",Toast.LENGTH_LONG).show()
            }
        }
        else{
            Toast.makeText(this,"verifique sus datos",Toast.LENGTH_LONG).show()

        }


    }

    fun saveDataFirebaseStorage(){

    }

}