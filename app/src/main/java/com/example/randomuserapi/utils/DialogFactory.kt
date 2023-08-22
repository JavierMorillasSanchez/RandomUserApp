package com.example.randomuserapi.utils

import android.app.Dialog
import android.content.Context
import com.example.randomuserapi.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

//Implemented using Factory Pattern Design
interface CustomDialog{
    fun customDialog(context: Context): Dialog
}

abstract class DialogFactory {
    abstract fun createDialog(): CustomDialog
}

class InfoDialog: CustomDialog {

    companion object Factory: DialogFactory() {
        override fun createDialog(): CustomDialog = InfoDialog()

    }

    override fun customDialog(context: Context): Dialog {
        return MaterialAlertDialogBuilder(context)
            .setTitle(R.string.dialog_title_no_internet)
            .setMessage(R.string.dialog_message_no_internet)
            .setPositiveButton(R.string.dialog_understand_button) { dialog, which ->
                { }
            }.show()
    }

}