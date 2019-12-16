package com.opengl.experiments

import android.content.Context
import android.opengl.GLSurfaceView

import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MyGLRenderer
    (context: Context, val redraw: () -> Unit) : GLSurfaceView.Renderer {


    private val pyramid: Pyramid = Pyramid()
    @Volatile
    var angle: Float = 5f
    @Volatile
    var yChange: Float = 0f

    var yChangeArray: FloatArray = floatArrayOf(0f)


    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {


    }


    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {

    }


    override fun onDrawFrame(gl: GL10) {

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)


        gl.glLoadIdentity()
        gl.glScalef(0.5f, 0.5f, 1f)
        gl.glTranslatef(0.0f, yChangeArray[0], 0.0f);
        gl.glRotatef(angle, 0.0f, 1.0f, -0.1f)
        pyramid.draw(gl)


    }

    fun onDrawAnimation() {
        var y = 0f
        val isUp: Boolean = yChange < 0
        Runnable {
            if (isUp) {
                while (y > yChange) {
                    yChangeArray[0] = y
                    redraw
                   // Thread.sleep(10)
                    y -= 0.01f
                }
            } else {
                while (y < yChange) {
                    yChangeArray[0] = y
                    redraw
                    //Thread.sleep(10)
                    y += 0.01f
                }
            }

        }.run()
    }


}