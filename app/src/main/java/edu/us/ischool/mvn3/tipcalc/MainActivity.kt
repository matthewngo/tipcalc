package edu.us.ischool.mvn3.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tipButton = findViewById<Button>(R.id.Tip)
        val amountText = findViewById<EditText>(R.id.Amount)

        tipButton.isEnabled = false;
        amountText.addTextChangedListener(object: TextWatcher {
            val screenVal: TextView = amountText
            var lastVal = ""

            override fun afterTextChanged(s: Editable) {
                tipButton.isEnabled = s.length > 0 && !s.toString().equals("$")
                if (amountText.text.contains('.')) {
                    val len = amountText.text.length
                    if (amountText.text.toString()[len-4] == '.') {
                        screenVal.text = lastVal
                        amountText.setSelection(len-1)
                    }
                }
                lastVal = amountText.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (amountText.length() > 0) {
                    if (amountText.text.toString()[0] != '$') {
                        screenVal.text = "$" + amountText.text
                        amountText.setSelection(2)
                    }
                }
            }
        })

        tipButton.setOnClickListener {
            val text = amountText.text.toString().substring(1)
            val numVal = text.toFloat()
            val result = "$" + "%.2f".format(numVal * 0.15)
            val duration = Toast.LENGTH_LONG
            val toast = Toast.makeText(this, result, duration)
            toast.show()
        }
    }
}
