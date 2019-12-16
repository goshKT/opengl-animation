package com.opengl.experiments

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.opengl.GLSurfaceView
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.MotionEvent
import android.view.View


class MainActivity : AppCompatActivity() {

    private var glView: GlCustomSurface? = null   

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glView = GlCustomSurface(this)          
        glView!!.setRenderer(MyGLRenderer(this, { glView!!.requestRender()})) 
        glView!!.setOnTouchListener{ v: View?, event: MotionEvent? ->
            if(v is GlCustomSurface)
                v.onTouch(event)
            true
        }
        this.setContentView(glView)                
    }

    
    override fun onPause() {
        super.onPause()
        glView!!.onPause()
    }

    
    override fun onResume() {
        super.onResume()
        glView!!.onResume()
    }
}
