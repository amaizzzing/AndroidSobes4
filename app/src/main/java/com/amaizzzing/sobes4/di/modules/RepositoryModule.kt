package com.amaizzzing.sobes4.di.modules

import com.amaizzzing.sobes4.data.dataSources.ClassesDatasource
import com.amaizzzing.sobes4.data.dataSources.HomeworkDatasource
import com.amaizzzing.sobes4.data.dataSources.IClassesDatasource
import com.amaizzzing.sobes4.data.dataSources.IHomeworkDatasource
import com.amaizzzing.sobes4.data.repositories.*
import com.amaizzzing.sobes4.data.services.ITimerService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun classesRepository(classesDatasource: IClassesDatasource): IClassesRepository =
        ClassesRepository(classesDatasource)

    @Singleton
    @Provides
    fun homeworkRepository(homeworkDatasource: IHomeworkDatasource): IHomeworkRepository =
        HomeworkRepository(homeworkDatasource)

    @Singleton
    @Provides
    fun timerRepository(timer: ITimerService): ITimerRepository =
        TimerRepository(timer)

    @Singleton
    @Provides
    fun classesDataSource(): IClassesDatasource = ClassesDatasource()

    @Singleton
    @Provides
    fun homeworkDataSource(): IHomeworkDatasource = HomeworkDatasource()
}