package com.example.nanny

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.nanny.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        fun validate(){
            val datauser:String=binding.inputEmailLogin.text.toString()
            val datapass:String=binding.inputPasswordLogin.text.toString()

            val datos =getSharedPreferences("datauser",Context.MODE_PRIVATE)
            val user=datos.getString("nombreusuario","")
            val password=datos.getString("claveusuario","")
            if (user!!.isEmpty()){
                binding.inputEmailLogin.setHint("Ingrese el correo")
            }

            else if(password!!.isEmpty()){
                //binding.inputPasswordLogin.setHint("ingrese clave")
                //binding.inputPasswordLogin.setHintTextColor(Color.RED)
                Toast.makeText(this,"ingrese clave",Toast.LENGTH_SHORT).show()
            }
            else if(user.equals(datauser) && password.equals(datapass)){
                val intent=Intent(this,User::class.java)
                intent.putExtra("nombre", datauser)
                startActivity(intent)
                Toast.makeText(this,"Datos Correctos",Toast.LENGTH_SHORT).show()
            }
            else {
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

            validate()
        }
        binding.labelRecoveryPassLogin.setOnClickListener{
            startActivity(Intent(this,RecoveryKey::class.java))
        }
    }
}