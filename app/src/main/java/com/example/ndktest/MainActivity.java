package com.example.ndktest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ndktest.databinding.ActivityMainBinding;

//import al.com.cacheable.network.adapter.ApiAdapter;
//import al.com.cacheable.network.enums.NetworkStatus;






public class MainActivity extends AppCompatActivity{


    //static {
    //    System.loadLibrary("ndktest");
    //}




    private ActivityMainBinding binding;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Context context = getApplicationContext();

        Intent intent = new Intent(context, MyService.class);
        startService(intent);
        //jjava.plus(1,2);

    }




/*



    /**
     * A native method that is implemented by the 'ndktest' native library,
     * which is packaged with this application.
     */

    //public native String stringFromJNI();


}