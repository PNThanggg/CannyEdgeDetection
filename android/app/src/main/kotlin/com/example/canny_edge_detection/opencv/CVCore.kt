package com.example.canny_edge_detection.opencv

import android.util.Log
import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc

object CVCore {
    private const val TAG = "CVCore"
    private const val JPG = ".jpg"

    fun cannyEdgeDetection(
        byteData: ByteArray?,
        threshold1: Double,
        threshold2: Double
    ): ByteArray {
        var byteArray = ByteArray(0)

        try {
            val dst = Mat()
            val src = Imgcodecs.imdecode(MatOfByte(byteData), Imgcodecs.IMREAD_UNCHANGED)

            Imgproc.Canny(src, dst, threshold1, threshold2)

            val matOfByte = MatOfByte()
            Imgcodecs.imencode(JPG, dst, matOfByte)
            byteArray = matOfByte.toArray()
        } catch (e: Exception) {
            Log.e(TAG, "OpenCV Error: $e")
            println("OpenCV Error: $e")
        }

        return byteArray
    }
}