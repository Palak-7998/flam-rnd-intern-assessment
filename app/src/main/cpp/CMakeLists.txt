cmake_minimum_required(VERSION 3.22.1)

# === 1. FIND AND CONFIGURE OPENCV ===
# Set the path to the OpenCV SDK relative to the cpp directory
set(OpenCV_DIR ${CMAKE_SOURCE_DIR}/opencv/sdk/native/jni)
find_package(OpenCV REQUIRED)

# === 2. ADD YOUR NATIVE LIBRARY ===
# ... above code ...

# === 2. ADD YOUR NATIVE LIBRARY ===
add_library(
        edgeviewer
        SHARED
        native-lib.cpp
        gles2_lib.cpp  # <--- ADD THE NEW OPENGL C++ FILE
)

# ... find other standard libraries ...

# === 4. LINK ALL LIBRARIES TOGETHER ===
target_link_libraries(
        edgeviewer
        ${OpenCV_LIBS}
        ${log-lib}
        ${android-lib}
        ${jnigraphics-lib}
        GLESv2  # <--- LINK THE OPENGL ES 2.0 LIBRARY
)