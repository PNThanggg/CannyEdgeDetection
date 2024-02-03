package com.example.canny_edge_detection.opencv

import android.util.Log
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import org.opencv.core.Core

class OpenCvMethodCallHandler : MethodCallHandler {
    companion object {
        private const val TAG = "OpenCvMethodCallHandler"
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "getPlatformVersion" -> {
                result.success("OpenCV " + Core.VERSION)
            }

            "canny_edge_detection" -> {
                val byteData: ByteArray? = call.argument("byteData")
                val threshold1: Double = call.argument("threshold1") ?: 0.0
                val threshold2: Double = call.argument("threshold2") ?: 0.0

                Log.d(TAG, "byteData $byteData")
                Log.d(TAG, "threshold1 $threshold1")
                Log.d(TAG, "threshold2 $threshold2")

                val canny: ByteArray = CVCore.cannyEdgeDetection(
                    byteData,
                    threshold1,
                    threshold2,
                )

                result.success(
                    canny
                )
            }

            else -> result.notImplemented()
        }
    }

//    private fun getBytesFromBitmapChunk(bitmap: Bitmap): ByteArray {
//        val bitmapSize = bitmap.rowBytes * bitmap.height
//        val byteArray = ByteArray(bitmapSize)
//        val byteBuffer = ByteBuffer.allocate(bitmapSize)
//        bitmap.copyPixelsToBuffer(byteBuffer)
//        byteBuffer.rewind()
//        byteBuffer.get(byteArray)
//        return byteArray
//    }
//
//    private fun test(src: Bitmap): Bitmap {
//        val width = src.width
//        val height = src.height
//        // create output bitmap
//        val bmOut = Bitmap.createBitmap(width, height, src.config)
//        // color information
//        var A: Int
//        var R: Int
//        var G: Int
//        var B: Int
//        var pixel: Int
//        for (x in 0 until width) {
//            for (y in 0 until height) {
//                // get pixel color
//                pixel = src.getPixel(x, y)
//                A = Color.alpha(pixel)
//                R = Color.red(pixel)
//                G = Color.green(pixel)
//                B = Color.blue(pixel)
//                var gray = (0.2989 * R + 0.5870 * G + 0.1140 * B).toInt()
//                // use 128 as threshold, above -> white, below -> black
//                gray = if (gray > 128) {
//                    255
//                } else {
//                    0
//                }
//                // set new pixel color to output bitmap
//                bmOut.setPixel(x, y, Color.argb(A, gray, gray, gray))
//            }
//        }
//        return bmOut
//    }
}