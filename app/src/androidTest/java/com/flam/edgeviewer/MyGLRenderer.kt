package com.flam.edgeviewer

import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Renderer class that implements the GLSurfaceView.Renderer interface.
 * It acts as the bridge to the native C++ OpenGL functions.
 */
class MyGLRenderer : GLSurfaceView.Renderer {

    // JNI functions declared in the GLES2Lib.cpp file
    companion object {
        // Must match the function names in gles2_lib.cpp
        external fun nativeOnSurfaceCreated()
        external fun nativeOnSurfaceChanged(width: Int, height: Int)
        external fun nativeOnDrawFrame()
        external fun nativeUpdateFrameTexture(textureID: Int, width: Int, height: Int)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        nativeOnSurfaceCreated()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        nativeOnSurfaceChanged(width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        nativeOnDrawFrame()
    }

    fun updateTexture(textureID: Int, width: Int, height: Int) {
        // This is called from MainActivity.kt when a new camera frame is ready
        nativeUpdateFrameTexture(textureID, width, height)
    }
}