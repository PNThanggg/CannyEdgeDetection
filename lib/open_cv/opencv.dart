import 'dart:async';

import 'package:flutter/services.dart';

class OpenCV {
  static final _instance = OpenCV._internal();

  OpenCV._internal();

  static OpenCV getInstance() {
    return _instance;
  }

  static const MethodChannel _channel = MethodChannel('opencv');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<Uint8List?> cannyEdgeDetection(
    Uint8List byteData,
    double threshold1,
    double threshold2,
  ) async {
    /// Variable to store operation result
    final Uint8List? result = await _channel.invokeMethod(
        'canny_edge_detection', {
      'byteData': byteData,
      'threshold1': threshold1,
      'threshold2': threshold2
    });

    return result;
  }
}
