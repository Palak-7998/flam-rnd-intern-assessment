# EdgeViewer: Android + OpenCV + OpenGL ES Assessment

This project implements a real-time edge detection viewer for an Android application, utilizing Native C++ (NDK) for OpenCV processing and OpenGL ES 2.0 for high-performance rendering. [cite_start]A separate TypeScript web viewer simulates the display of the processed output[cite: 16, 18].

## Implemented Key Features

| Feature | Status | Weight | Notes |
| :--- | :--- | :--- | :--- |
| **Native-C++ Integration (JNI)** | ✅ Completed | 25% | [cite_start]Frames are sent via JNI to C++ for processing[cite: 71, 26]. |
| **OpenCV Usage (C++)** | ✅ Completed | 20% | [cite_start]Implements **Canny Edge Detection** using OpenCV in C++[cite: 74, 27]. |
| **OpenGL ES Rendering** | ✅ Completed | 20% | [cite_start]Processed output is rendered via `GLSurfaceView` using OpenGL ES 2.0[cite: 74, 29]. |
| **TypeScript Web Viewer** | ✅ Completed | 20% | [cite_start]Minimal static web page displaying a mock processed frame[cite: 74, 34]. |
| **Project Structure, Git & Docs** | ✅ Completed | 15% | [cite_start]Modular structure maintained with proper Git commits[cite: 74, 51]. |

## Architecture Overview

1.  **Kotlin/Android (UI & Camera):** Uses CameraX to capture frames and manages the `GLSurfaceView` and OpenGL lifecycle hooks. Passes frame addresses to JNI.
2.  **JNI/C++ (Processing & Rendering):**
    * [cite_start]`native-lib.cpp`: Contains the `processFrameNative` JNI function for **Canny Edge Detection** using OpenCV[cite: 48, 27].
    * `gles2_lib.cpp`: Implements the OpenGL ES 2.0 rendering logic (shaders, textures) called from Kotlin's `MyGLRenderer.kt`.
3.  [cite_start]**TypeScript (Web Viewer):** Demonstrates ability to bridge native processing results to a simple web layer by displaying a static Base64 image of a processed frame[cite: 18].

## Setup Instructions

1.  **OpenCV:** Ensure the OpenCV Android SDK's `sdk` folder is correctly placed in `app/src/main/cpp/opencv/`.
2.  **Gradle:** `build.gradle.kts` must include CameraX dependencies and the essential NDK flags: `cppFlags += listOf("-fexceptions", "-frtti")`.
3.  **CMake:** `CMakeLists.txt` must link OpenCV, `gles2_lib.cpp`, and the `GLESv2` library.

---

## 2. TypeScript Web Viewer Files (20% Weight)

Create a new folder named **`web`** in your project root (`EdgeViewer/web/`) and place these four files inside it.

### File 1: `web/package.json`

```json
{
  "name": "edge-viewer-web",
  "version": "1.0.0",
  "description": "TypeScript Web Viewer for Edge Detection App",
  "scripts": {
    "start": "tsc && open index.html"
  },
  "dependencies": {},
  "devDependencies": {
    "typescript": "^5.0.0"
  }
}