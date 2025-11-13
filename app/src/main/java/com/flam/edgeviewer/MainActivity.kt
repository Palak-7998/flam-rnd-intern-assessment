// In MainActivity.kt

// ... imports (make sure you have android.opengl.GLSurfaceView)

class MainActivity : AppCompatActivity() {
    // ... existing member variables

    // Declare the renderer instance
    private lateinit var glRenderer: MyGLRenderer // <--- ADD THIS

    override fun onCreate(savedInstanceState: Bundle?) {
        // ... existing setup (binding, setContentView)

        // V V V V V V V V V V V V V V V V V V V V V V V V V V V V V V V V V V V V V V
        // 1. Initialize OpenGL View
        glRenderer = MyGLRenderer()
        // Must use OpenGL ES 2.0 or higher
        binding.glSurfaceView.setEGLContextClientVersion(2)
        binding.glSurfaceView.setRenderer(glRenderer)
        // Ensure continuous rendering for real-time video
        binding.glSurfaceView.renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
        // ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            // ... request permissions
        }
    }

    private fun startCamera() {
        // ... CameraX setup logic (ProcessCameraProvider.getInstance(this))

        // Image Analysis use case: where we capture frames
        // ... code to build imageAnalyzer
        .also {
            it.setAnalyzer(cameraExecutor, FrameAnalyzer { inputMat ->
                // Pass the frame's memory address to the C++ code for OpenCV processing
                val processedMatAddress = processFrameNative(inputMat.nativeObjAddr)

                // After processing, force the GLSurfaceView to redraw,
                // which triggers the nativeOnDrawFrame() C++ function.
                binding.glSurfaceView.requestRender() // <--- ADD THIS

                // ... release memory
            })
        }

        // ... rest of startCamera()
    }

    // ... onResume, onPause, onDestroy functions (ADD THEM for GLSurfaceView lifecycle)
    override fun onResume() {
        super.onResume()
        binding.glSurfaceView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.glSurfaceView.onPause()
    }
    // ... rest of the file
}