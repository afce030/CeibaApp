package com.ceiba.ceibaapp.di.components

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import android.app.Application
import com.ceiba.ceibaapp.BaseApp
import com.ceiba.ceibaapp.di.modules.AppModule
import com.ceiba.ceibaapp.di.modules.ActivityBuilder
import com.ceiba.ceibaapp.di.modules.ViewModelModule
import dagger.BindsInstance
import javax.inject.Singleton

@Singleton
@Component(modules=[
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class,
    ViewModelModule::class
])
interface AppComponent : AndroidInjector<BaseApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun Build(): AppComponent

    }

}
