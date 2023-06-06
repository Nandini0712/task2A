package com.example.task2aaa

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {

    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        view = object : View(this) {
            private val paint = Paint()
            private var circleX = 100f
            private var circleY = 150f
            private var circleRadius = 50f
            private var obstacleX = 500f
            private var obstacleY = 100f
            private var obstacleWidth = 200f
            private var obstacleHeight = 100f
            private var lives = 10
            private var isGameOver = false
            private var i = 100
            private var Touched_obs = false
            private var Touching = false



            override fun onDraw(canvas: Canvas) {
                super.onDraw(canvas)

                //  circle
                if ( i > 0 )
                    i -= 1
                else{
                    i = 0
                    circleY = 150f
                }
                paint.color = Color.RED
                canvas.drawCircle(circleX, circleY, circleRadius, paint)

                //  obstacle
                paint.color = Color.BLUE
                canvas.drawRect(
                    obstacleX,
                    obstacleY,
                    obstacleX + obstacleWidth,
                    obstacleY + obstacleHeight,
                    paint
                )

                // collision
                if (circleX + circleRadius > obstacleX &&
                    circleX - circleRadius < obstacleX + obstacleWidth &&
                    circleY + circleRadius > obstacleY &&
                    circleY - circleRadius < obstacleY + obstacleHeight
                ) {
                    Touching = true
                }
                else {
                    Touching= false
                    if(Touched_obs == true){
                        Touched_obs = false
                    }

                }

                if ( Touching && ( !Touched_obs)){
                    Touched_obs = true
                    lives--
                }


                if (lives == 0) {
                    // Game over
                    isGameOver = true
                    paint.color = Color.BLACK
                    paint.textSize = 60f
                    canvas.drawText("Game Over", width / 2f - 150f, height / 2f, paint)
                }



                paint.color = Color.GREEN
                paint.textSize = 40f
                canvas.drawText("Lives: $lives", 20f, 40f, paint)


                if (!isGameOver) {


                    if( circleX > 1000f){
                        circleX = 0f
                    }
                    else {
                        circleX += 5f
                    }


                    invalidate()


                }
            }

            override fun onTouchEvent(event: MotionEvent): Boolean {

                if (event.action == MotionEvent.ACTION_DOWN) {
                    circleY -= 100f
                    i = 100
                }
                return true
            }
        }

        setContentView(view)
    }
}