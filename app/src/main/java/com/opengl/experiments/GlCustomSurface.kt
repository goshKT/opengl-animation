package com.opengl.experiments

import android.content.Context
import android.opengl.GLSurfaceView
import android.view.MotionEvent


class GlCustomSurface(context: Context) : GLSurfaceView(context) {
    private var render: MyGLRenderer? = null
    private val TOUCH_SCALE_FACTOR: Float = 180.0f / 320f
    private var previousX: Float = 0f
    private var previousY: Float = 0f

    override fun setRenderer(renderer: Renderer?) {
        super.setRenderer(renderer)
        if (renderer is MyGLRenderer) {
            render = renderer as MyGLRenderer?
            renderMode = RENDERMODE_WHEN_DIRTY
        }
    }


    fun onTouch(e: MotionEvent?): Boolean {
        if (e == null)
            return false
        val x: Float = e.x
        val y: Float = e.y


        when (e.action) {
            MotionEvent.ACTION_MOVE -> {


                var dx: Float = x - previousX
                var dy: Float = y - previousY

                
                if (y > height / 2) {
                    dx *= -1
                }

                
                if (x < width / 2) {
                    dy *= -1
                }

                render?.angle = render?.angle?.plus((dx + dy) * TOUCH_SCALE_FACTOR)!!
                requestRender()
            }

            MotionEvent.ACTION_DOWN -> {
                if (y > height / 2f && previousY != 0f) {
                    render?.yChange = -1 * y/previousY

                } else if( previousY != 0f) {
                    render?.yChange = y/previousY
                }
                render?.onDrawAnimation()

            }


        }

        previousX = x
        previousY = y
        return true
    }
}