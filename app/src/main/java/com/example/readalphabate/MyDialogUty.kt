package com.example.readalphabate

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialog

object MyDialogUty {
    interface MyDialogCallback<T> {
        fun myDialogCallback(action: T, pressOk: Boolean, requireData: T?)
    }

    interface MyDialogCallback2<T> {
        fun myDialogCallback(action: T, pressOk: Boolean, requireData1: T?, requireData2: T?)
    }

    fun showInfoDialog(
        context: Context, myDialogCallback: MyDialogCallback<String>?, action: String,
        title: String,
        message: String,
        positive: String
    ) {
        val dialog = AppCompatDialog(context, R.style.MyAppCompatDialog)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.window.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_info_positive)
        val tvTitle = dialog.findViewById<TextView>(R.id.tvTitle)
        tvTitle?.text = title
        val tvMessage = dialog.findViewById<EditText>(R.id.tvMessage)
        val btnPositive = dialog.findViewById<Button>(R.id.btnPositive)
        btnPositive?.text = positive
        val tv1 = dialog.findViewById<TextView>(R.id.tv1)
        tv1?.setOnClickListener {
            myDialogCallback?.myDialogCallback(action, true, tv1?.text.toString().toLowerCase())
            dialog.dismiss()
        }
        val tv2 = dialog.findViewById<TextView>(R.id.tv2)
        tv2?.setOnClickListener {
            myDialogCallback?.myDialogCallback(action, true, tv2?.text.toString().toLowerCase())
            dialog.dismiss()
        }
        btnPositive?.setOnClickListener {
            myDialogCallback?.myDialogCallback(action, true, tvMessage?.text.toString().toLowerCase())
            dialog.dismiss()
        }
        dialog.show()
    }

//    fun showLoadingDialog(context: Context): AppCompatDialog {
//        val dialog = AppCompatDialog(context, R.style.MyAppCompatLoadingDialog)
////        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
////        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setCancelable(false)
//        dialog.setContentView(R.layout.dialog_loading)
//        dialog.show()
//        return dialog
//    }
}
