package com.licenta.postureimprover.screens.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class TimerDialogViewModel @Inject constructor(
    private val prefs: SharedPreferences
) : ViewModel() {

    var showDialog1: Boolean  by mutableStateOf(true)
    var showDialog2: Boolean  by mutableStateOf(false)
    var showDialog3: Boolean  by mutableStateOf(false)


    private val dialog1Text = "You have a 5 second timer to capture your posture!"
    private val dialog2Text = "Make sure to stand sideways towards the camera, comfortably, like you would at any time."
    private val dialog3Text = "Please make sure that your full body is visible in the picture :)"

    var dialogText: String by mutableStateOf(dialog1Text)
    var onNextPressed: () -> Unit by mutableStateOf({ onNextDialog1() })
    var onBackPressed: () -> Unit by mutableStateOf( { onBackDialog2() } )
    var isChecked: Boolean by mutableStateOf(false)

    fun onBoxSelected() {
        isChecked = !isChecked
    }

    fun saveInstructionsPrefs() {
        var p = prefs.edit().putBoolean("camera instructions", !isChecked).apply()
        println(p)
    }

    fun getInstructionPrefs(): Boolean {
        //if not found returns true
        return prefs.getBoolean("camera instructions", true)
    }

    private fun onNextDialog1() {
        showDialog1 = false
        showDialog2 = true
        dialogText = dialog2Text
        onNextPressed = { onNextDialog2() }
    }

    private fun onNextDialog2() {
        showDialog2 = false
        showDialog3 = true
        dialogText = dialog3Text
        onBackPressed = { onBackDialog3() }
    }

    private fun onBackDialog2() {
        showDialog2 = false
        showDialog1 = true
        dialogText = dialog1Text
        onNextPressed = { onNextDialog1() }
    }

    private fun onBackDialog3() {
        showDialog3 = false
        showDialog2 = true
        dialogText = dialog2Text
        onBackPressed = { onBackDialog2() }
    }


}