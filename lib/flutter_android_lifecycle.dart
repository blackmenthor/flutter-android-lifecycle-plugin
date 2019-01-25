import 'dart:async';

import 'package:flutter/services.dart';

class FlutterAndroidLifecycle {
  static const MethodChannel _channel =
      const MethodChannel('flutter_android_lifecycle');
  static const EventChannel _onStartChannel = const EventChannel('flutter_android_lifecycle/on_start');
  static const EventChannel _onResumeChannel = const EventChannel('flutter_android_lifecycle/on_resume');
  static const EventChannel _onPauseChannel = const EventChannel('flutter_android_lifecycle/on_pause');

  static Stream<String> onStartStream;
  static Stream<String> onResumeStream;
  static Stream<String> onPauseStream;

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Stream<String> listenToOnStartStream() {
    if ( onStartStream == null ) onStartStream = _onStartChannel.receiveBroadcastStream().cast<String>();
    return onStartStream;
  }

  static Stream<String> listenToOnResumeStream() {
    if ( onResumeStream == null ) onResumeStream = _onResumeChannel.receiveBroadcastStream().cast<String>();
    return onResumeStream;
  }

  static Stream<String> listenToOnPauseStream() {
    if ( onPauseStream == null ) onPauseStream = _onPauseChannel.receiveBroadcastStream().cast<String>();
    return onPauseStream;
  }
}
