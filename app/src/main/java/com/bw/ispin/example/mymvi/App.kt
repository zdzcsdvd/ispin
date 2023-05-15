package com.bw.ispin.example.mymvi

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter

class App : Application() {
    companion object{
        @JvmField
        var context:Context?=null
    }

    override fun onCreate() {
        super.onCreate()
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
        context=applicationContext
    }


}