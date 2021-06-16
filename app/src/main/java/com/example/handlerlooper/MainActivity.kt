package com.example.handlerlooper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mediator: Handler = Handler(Looper.getMainLooper()) {
        main_tv.text = it.obj as String
        true
    }

    private lateinit var slave: Slave

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        slave = Slave()
        work(slave, "Товар собран!")
        work(slave, "Товар доставлен в док!")
        work(slave, "Товар отгружен!")
    }

    override fun onDestroy() {
        super.onDestroy()
        slave.quit()
    }

    private fun work(slave: Slave, report: String) {
        slave.execute {
            Thread.sleep(1000)
            //some work happens here
            val message = Message.obtain()
            message.obj = report
            mediator.sendMessage(message)
        }
    }

    fun repeat(view: View) {
        work(slave, "Товар собран!")
        work(slave, "Товар доставлен в док!")
        work(slave, "Товар отгружен!")
    }
}