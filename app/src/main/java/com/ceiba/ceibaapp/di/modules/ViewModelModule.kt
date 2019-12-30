package com.ceiba.ceibaapp.di.modules

import androidx.lifecycle.ViewModelProvider
import com.ceiba.ceibaapp.di.factory.viewModelFactory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}