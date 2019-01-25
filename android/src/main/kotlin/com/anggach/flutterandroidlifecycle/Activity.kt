package com.anggach.flutterandroidlifecycle

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.flutter.app.FlutterActivity
import io.flutter.plugin.common.EventChannel

class OnStartStreamHandler: EventChannel.StreamHandler {

    private var receiver: BroadcastReceiver? = null

    fun handleIntent(context: Context, intent: Intent?) {
        if (this.receiver != null && intent != null) receiver!!.onReceive(context, intent)
    }

    override fun onCancel(p0: Any?) {
        receiver = null
    }

    override fun onListen(p0: Any?, events: EventChannel.EventSink?) {
        receiver = createReceiver(events)
    }

    private fun createReceiver(events: EventChannel.EventSink?): BroadcastReceiver {
        return object : BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {
                events!!.success("ONSTART IS CALLED")
            }
        }
    }

}

class OnResumeStreamHandler: EventChannel.StreamHandler {

    private var receiver: BroadcastReceiver? = null

    fun handleIntent(context: Context, intent: Intent?) {
        if (this.receiver != null && intent != null) receiver!!.onReceive(context, intent)
    }

    override fun onCancel(p0: Any?) {
        receiver = null
    }

    override fun onListen(p0: Any?, events: EventChannel.EventSink?) {
        receiver = createReceiver(events)
    }

    private fun createReceiver(events: EventChannel.EventSink?): BroadcastReceiver {
        return object : BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {
                events!!.success("ONRESUME IS CALLED")
            }
        }
    }

}

class OnPauseStreamHandler: EventChannel.StreamHandler {

    private var receiver: BroadcastReceiver? = null

    fun handleIntent(context: Context, intent: Intent?) {
        if (this.receiver != null && intent != null) receiver!!.onReceive(context, intent)
    }

    override fun onCancel(p0: Any?) {
        receiver = null
    }

    override fun onListen(p0: Any?, events: EventChannel.EventSink?) {
        receiver = createReceiver(events)
    }

    private fun createReceiver(events: EventChannel.EventSink?): BroadcastReceiver {
        return object : BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {
                events!!.success("ONPAUSE IS CALLED")
            }
        }
    }

}

open class FlutterAndroidLifecycleActivity: FlutterActivity() {

    private var onStartStreamHandler: OnStartStreamHandler? = null
    private var onResumeStreamHandler: OnResumeStreamHandler? = null
    private var onPauseStreamHandler: OnPauseStreamHandler? = null

    private fun setUpOnStartStream(onStartStreamHandler: OnStartStreamHandler?) {
        FlutterAndroidLifecyclePlugin.setUpOnStart(onStartStreamHandler)
    }

    private fun setUpOnResumeStream(onResumeStreamHandler: OnResumeStreamHandler?) {
        FlutterAndroidLifecyclePlugin.setUpOnResume(onResumeStreamHandler)
    }

    private fun setUpOnPauseStream(onPauseStreamHandler: OnPauseStreamHandler?) {
        FlutterAndroidLifecyclePlugin.setUpOnPause(onPauseStreamHandler)
    }

    override fun onStart() {
        super.onStart()
        this.onStartStreamHandler = this.onStartStreamHandler ?: OnStartStreamHandler()
        setUpOnStartStream(this.onStartStreamHandler)
        val intent = Intent()
        this.onStartStreamHandler!!.handleIntent(this, intent)
    }

    override fun onResume() {
        super.onResume()
        this.onResumeStreamHandler = this.onResumeStreamHandler ?: OnResumeStreamHandler()
        setUpOnResumeStream(this.onResumeStreamHandler)
        val intent = Intent()
        this.onResumeStreamHandler!!.handleIntent(this, intent)
    }

    override fun onPause() {
        super.onPause()
        this.onPauseStreamHandler = this.onPauseStreamHandler ?: OnPauseStreamHandler()
        setUpOnPauseStream(this.onPauseStreamHandler)
        val intent = Intent()
        this.onPauseStreamHandler!!.handleIntent(this, intent)
    }

}