package de.pacheco.froggame.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import de.pacheco.froggame.databinding.ActivityCatchfrogBinding
import java.util.*
import kotlin.collections.ArrayList

/** Old and unused**/
class CatchFrogActivity : AppCompatActivity() {
    var score: Int = 0
    var highscore: Int = 0
    var imageArray = ArrayList<ImageView>()
    var handler: Handler = Handler()
    var runnable: Runnable = Runnable { }
    private lateinit var binding: ActivityCatchfrogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatchfrogBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPreferences = getSharedPreferences("HIGH_SC", Context.MODE_PRIVATE)
        highscore=sharedPreferences.getInt("HIGH_SCORE",0)
        score = 0
        imageArray = arrayListOf(
            binding.imageView1,
            binding.imageView2,
            binding.imageView3,
            binding.imageView4,
            binding.imageView5,
            binding.imageView6,
            binding.imageView7,
            binding.imageView8,
            binding.imageView9
        )

        hideImages()
        object : CountDownTimer(10000, 1000) {
            override fun onFinish() {
                binding.timeText.text = "Time' s off"
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                binding.buttonPlay.visibility = View.VISIBLE
                if (score > highscore) {
                    highscore = score
                    binding.highSCtext.text = "HighScore: " + highscore
                    binding.highSCtext.visibility = View.VISIBLE
                    val editor = sharedPreferences.edit()
                    editor.putInt("HighScore", highscore)
                    editor.apply()
                } else {
                    binding.highSCtext.text = "HighScore: " + highscore
                    binding.highSCtext.visibility = View.VISIBLE
                }

                binding.buttonPlay.setOnClickListener {
                    val i = baseContext.packageManager.getLaunchIntentForPackage(baseContext.packageName)
                    i!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(i)
                }
            }

            override fun onTick(millisUntilFinished: Long) {
                binding.timeText.text = "Time: " + millisUntilFinished / 1000
            }

        }.start()

    }

    fun hideImages() {
        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                val random = Random()
                val index = random.nextInt(8 - 0)
                imageArray[index].visibility = View.VISIBLE

                handler.postDelayed(runnable, 300)
            }

        }
        handler.post(runnable)

    }

    fun increaseScore(view: View) {
        score++
        binding.scoreText.text = "Score: " + score
    }
}