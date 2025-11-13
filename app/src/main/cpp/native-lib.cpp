#include <jni.h>
#include <string>
#include <opencv2/core.hpp>
#include <opencv2/imgproc.hpp>
#include <opencv2/imgproc/imgproc_c.h>
#include <android/log.h>

// Define a log tag for easier debugging
#define LOG_TAG "NativeFrameProcessor"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)

// Use the Mat class from OpenCV
using namespace cv;

/**
 * JNI function signature:
 * JNIEXPORT jlong JNICALL Java_com_flam_edgeviewer_MainActivity_processFrameNative
 * (JNIEnv *env, jclass clazz, jlong matAddrInput)
 *
 * It takes the memory address of the input Mat (frame) and returns the address
 * of the processed Mat.
 */
extern "C" JNIEXPORT jlong JNICALL
Java_com_flam_edgeviewer_MainActivity_processFrameNative(JNIEnv *env, jclass clazz, jlong matAddrInput) {
    // Cast the input address to a Mat pointer
    Mat& inputMat = *(Mat*)matAddrInput;
    Mat processedMat;

    // --- Start OpenCV Processing (Key Feature 2) ---
    // 1. Convert to Grayscale
    cvtColor(inputMat, processedMat, COLOR_RGBA2GRAY);
    LOGD("Frame converted to grayscale.");

    // 2. Apply Canny Edge Detection
    Canny(processedMat, processedMat, 50, 150);
    LOGD("Canny edge detection applied.");

    // 3. Convert back to RGB/RGBA for Android display (optional, depending on display method)
    // To display the grayscale Canny output on a color surface, convert it back to BGR/RGB
    cvtColor(processedMat, processedMat, COLOR_GRAY2RGBA);
    LOGD("Processed frame converted back to RGBA.");
    // --- End OpenCV Processing ---

    // Return the memory address of the processed Mat
    return (jlong) new Mat(processedMat);
}

// You can keep the original StringFromJNI function if you need it for debugging:
extern "C" JNIEXPORT jstring JNICALL
Java_com_flam_edgeviewer_ExampleInstrumentedTest_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}