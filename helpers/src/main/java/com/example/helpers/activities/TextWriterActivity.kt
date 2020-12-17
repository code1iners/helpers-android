package com.example.helpers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.helpers.Keypad
import com.example.helpers.R
import com.example.helpers.toastForShort

class TextWriterActivity : AppCompatActivity()
    , View.OnClickListener
    , TextView.OnEditorActionListener
{
    companion object {
        val TAG = TextWriterActivity::class.simpleName
    }

    // note. # widgets
    private lateinit var textWriterActivity_header_title: TextView
    private lateinit var textWriterActivity_header_terminate: ImageButton
    private lateinit var textWriterActivity_body_editor: EditText
    private lateinit var textWriterActivity_body_submit: Button

    // note. # vars
    private var title = "Title"
    
    private val TITLE = "TITLE"
    private val RESULT = "RESULT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_writer)

        init()
    }

    private fun init() {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)

        checkArgs()
        initWidgets()
    }

    private fun checkArgs() {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
        intent?.extras?.getString(TITLE)?.let {
            title = it
            Log.v(TAG, "title:$title")
        }
    }

    private fun initWidgets() {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)

        textWriterActivity_header_title = findViewById(R.id.textWriterActivity_header_title)
        textWriterActivity_header_title.text = "$title 입력"
        textWriterActivity_header_terminate = findViewById(R.id.textWriterActivity_header_terminate)
        textWriterActivity_header_terminate.setOnClickListener(this)
        textWriterActivity_body_editor = findViewById(R.id.textWriterActivity_body_editor)
        textWriterActivity_body_editor.setOnEditorActionListener(this)
        Keypad(this).up(textWriterActivity_body_editor)
        textWriterActivity_body_submit = findViewById(R.id.textWriterActivity_body_submit)
        textWriterActivity_body_submit.setOnClickListener(this)
    }

    private fun finishWithResults() {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)

        val result = textWriterActivity_body_editor.text.toString()
        if (result.isEmpty()) {
            this.toastForShort("${title}을(를) 입력해주세요.")
            return
        }

        Log.v(TAG, "title:$title, result:$result")
        val results = Intent()
        results.putExtra(TITLE, title)
        results.putExtra(RESULT, result)
        setResult(RESULT_OK, results)
        finish()
        Keypad(this).down(textWriterActivity_body_editor)
    }

    override fun onClick(v: View) {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
        when (v.id) {
            R.id.textWriterActivity_header_terminate -> finish()
            R.id.textWriterActivity_body_submit -> finishWithResults()
        }
    }

    override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
        when (v.id) {
            R.id.textWriterActivity_body_editor -> {
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> textWriterActivity_body_submit.performClick()
                }
                return true
            }
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        Keypad(this).down(textWriterActivity_body_editor)
    }
}