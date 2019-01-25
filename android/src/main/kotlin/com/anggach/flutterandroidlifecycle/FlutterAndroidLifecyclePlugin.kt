package com.anggach.flutterandroidlifecycle

import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class FlutterAndroidLifecyclePlugin: MethodCallHandler {
  companion object {
    private const val ON_START_CHANNEL: String = "flutter_android_lifecycle/on_start"
    private const val ON_RESUME_CHANNEL: String = "flutter_android_lifecycle/on_resume"
    private const val ON_PAUSE_CHANNEL: String = "flutter_android_lifecycle/on_pause"

    private lateinit var onStartChannel: EventChannel
    private lateinit var onResumeChannel: EventChannel
    private lateinit var onPauseChannel: EventChannel

    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "flutter_android_lifecycle")
      channel.setMethodCallHandler(FlutterAndroidLifecyclePlugin())

      onStartChannel = EventChannel(registrar.messenger(), ON_START_CHANNEL)
      onResumeChannel = EventChannel(registrar.messenger(), ON_RESUME_CHANNEL)
      onPauseChannel = EventChannel(registrar.messenger(), ON_PAUSE_CHANNEL)
    }

    fun setUpOnStart(handler: EventChannel.StreamHandler?) {
      this.onStartChannel.setStreamHandler(handler)
    }

    fun setUpOnResume(handler: EventChannel.StreamHandler?) {
      this.onResumeChannel.setStreamHandler(handler)
    }

    fun setUpOnPause(handler: EventChannel.StreamHandler?) {
      this.onPauseChannel.setStreamHandler(handler)
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else {
      result.notImplemented()
    }
  }
}
