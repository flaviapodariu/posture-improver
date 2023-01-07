package com.licenta.postureimprover.db

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.licenta.postureimprover.navigation.Routes
import timber.log.Timber

lateinit var auth: FirebaseAuth

fun register(email: String, password:String, confirmPass: String, navController: NavHostController){
    auth = Firebase.auth
    try {

        if (password == confirmPass){
            val res = auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        navController.navigate(Routes.Dashboard.passArgs(email))
                    }
                }
        }
        else{
        }


    }
    catch (e: FirebaseAuthUserCollisionException) {
        Timber.tag("FIREBASE").d("sign up failed: %s", e.stackTrace)
    }
    catch(e: FirebaseAuthInvalidCredentialsException) {
        Timber.tag("FIREBASE").d("enter valid email")
    }

}


fun login(email: String, password: String, navController: NavHostController){
    auth = Firebase.auth
    val res = auth.signInWithEmailAndPassword(email, password)
                  .addOnCompleteListener{ task ->
                      if(task.isSuccessful) {
                          navController.navigate(Routes.Dashboard.passArgs(email))
                      }
                      else {
                          Timber.tag("FIREBASE").d("sign in failed")
                      }

                  }

}