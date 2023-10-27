package com.albin.myapplication


import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var btnPhoto: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPhoto = findViewById(R.id.btnPhoto)

        btnPhoto.setOnClickListener { checkPermissions() }


    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
                requestCameraPermission()

        }
        else    {
                //Abrir camara
            openCamera()
        }
    }

    private fun openCamera() {
        Toast.makeText(this,"Abrir camara",Toast.LENGTH_SHORT).show()
    }

    private fun requestCameraPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.CAMERA))
        {
            //El Usuario ya ha rechazado los permisos
            Toast.makeText(this,"Permisos Rechazados ",Toast.LENGTH_SHORT).show()
        }
        else
        {
            //Perdir permiso
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 777)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 777){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //Abrir camara
                openCamera()
            }
            else
            {
                //El usuario ha rechazado los permisos
                Toast.makeText(this,"Permisos Rechazados por primera vez",Toast.LENGTH_SHORT).show()
            }
        }
    }
}