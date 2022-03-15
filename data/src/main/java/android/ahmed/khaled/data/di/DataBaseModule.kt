package android.ahmed.khaled.data.di


//@Module
//@InstallIn(SingletonComponent::class)
//class DataBaseModule {
//
//    @Provides
//    @Singleton
//    fun provideRoomDatabase(application: Application): AppDatabase {
//        return Room
//            .databaseBuilder(
//                application,
//                AppDatabase::class.java,
//                AppDatabase.DATABASE_NAME
//            )
//            .build()
//    }
//
//    @Provides
//    fun provideMusicDao(appDataBase: AppDatabase): MusicDao {
//        return appDataBase.MusicDao()
//    }
//}