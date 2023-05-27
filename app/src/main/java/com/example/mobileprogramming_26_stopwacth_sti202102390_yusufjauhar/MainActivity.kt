package com.example.mobileprogramming_26_stopwacth_sti202102390_yusufjauhar


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    private var running: Boolean = false
    private var seconds: Int = 0
    private var laps: LinkedList<String> = LinkedList()
    private lateinit var stopwatchTextView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button
    private lateinit var lapLayout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stopwatchTextView = findViewById(R.id.stopwatchTextView)
        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        resetButton = findViewById(R.id.resetButton)
        lapLayout = findViewById(R.id.lapLayout)

        // Listener untuk tombol Start
        startButton.setOnClickListener {
            running = true
        }

        // Listener untuk tombol Stop
        stopButton.setOnClickListener {
            running = false
        }

        // Listener untuk tombol Reset
        resetButton.setOnClickListener {
            running = false
            seconds = 0
            laps.clear()
            stopwatchTextView.text = "00:00:00"
            lapLayout.removeAllViews()
        }

        // Listener untuk tombol Lap
        val lapButton: Button = findViewById(R.id.lapButton)
        lapButton.setOnClickListener {
            laps.addLast(stopwatchTextView.text.toString())
            val lapTextView = TextView(this)
            lapTextView.text = "${laps.size}. ${laps.last}"
            lapLayout.addView(lapTextView, 0)
        }

        // Thread untuk menghitung waktu stopwatch
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60
                val time = String.format("%02d:%02d:%02d", hours, minutes, secs)
                stopwatchTextView.text = time
                if (running) {
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }
}
