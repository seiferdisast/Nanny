package com.example.nanny

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.nanny.databinding.ActivityRegisterBinding
import java.io.File

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
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
}