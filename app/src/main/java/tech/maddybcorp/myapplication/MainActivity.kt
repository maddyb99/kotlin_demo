package tech.maddybcorp.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title="Test"
        // Example of a call to a native method
        findViewById<TextView>(R.id.sample_text).text = stringFromJNI()
        val textView=findViewById<TextView>(R.id.textView_progress)
        val seekBar=findViewById<SeekBar>(R.id.seekBar)
        val translateY=textView.translationY

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView.text = progress.toString()

                val translationDistance = (translateY +
                        progress * resources.getDimension(R.dimen.text_anim_step) * -1)

                textView.animate().translationY(translationDistance)
                if (!fromUser)
                    textView.animate().setDuration(500).rotationBy(360f)
                        .translationY(translateY)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        findViewById<Button>(R.id.button3).setOnClickListener { _ ->
            seekBar.progress = 0
        }


    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}