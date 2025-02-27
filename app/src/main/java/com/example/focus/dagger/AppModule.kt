package com.example.focus.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.focus.SoundService
import com.example.focus.notification.AlarmManagerUtil
import com.example.focus.settings.GlobalSettings
import com.example.focus.storage.UserDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.applicationContext.getSharedPreferences("app_data", Context.MODE_PRIVATE)
    }

    private var globalSettings: GlobalSettings? = null

    @Provides
    @Singleton
    fun provideGlobalSetting(sharedPreferences: SharedPreferences): GlobalSettings {
        if(globalSettings == null)
            globalSettings = GlobalSettings(sharedPreferences)
        return globalSettings!!
    }

    @Provides
    @Singleton
    fun provideSoundService(application: Application, sharedPreferences: SharedPreferences): SoundService {
        if(globalSettings == null)
            globalSettings = GlobalSettings(sharedPreferences)
        return SoundService(application.applicationContext, globalSettings!!)
    }

    @Provides
    @Singleton
    fun provideAlarmManagerUtil(application: Application, sharedPreferences: SharedPreferences): AlarmManagerUtil {
        if(globalSettings == null)
            globalSettings = GlobalSettings(sharedPreferences)
        return AlarmManagerUtil(application.applicationContext, globalSettings!!)
    }

    @Provides
    @Singleton
    fun provideUserDataSource(sharedPreferences: SharedPreferences): UserDataSource {
        return UserDataSource(sharedPreferences)
    }
}