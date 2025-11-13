cmake_minimum_required(VERSION 3.22.1)

# === 1. FIND AND CONFIGURE OPENCV ===
# Set the path to the OpenCV SDK relative to the cpp directory
set(OpenCV_DIR ${CMAKE_SOURCE_DIR}/opencv/sdk/native/jni)
find_package(OpenCV REQUIRED)

# === 2. ADD YOUR NATIVE LIBRARY ===
add_library(
        edgeviewer
        SHARED
        native-lib.cpp # Ensure this points to your C++ source file
)

# === 3. LINK ALL LIBRARIES TOGETHER ===
target_link_libraries(
        edgeviewer
        ${OpenCV_LIBS} # This is the crucial link to OpenCV
        log
        android
        jnigraphics
        GLESv2
)