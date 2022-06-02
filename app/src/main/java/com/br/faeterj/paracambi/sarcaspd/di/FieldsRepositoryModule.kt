package com.br.faeterj.paracambi.sarcaspd.di

import android.content.Context
import com.br.faeterj.paracambi.sarcaspd.data.repository.FieldsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class FieldsRepositoryModule {

    @Provides
    fun provideFieldsRepository(@ApplicationContext context : Context) : FieldsRepository = FieldsRepository(context)

}