package com.example.weekendchat.di.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.weekendchat.data.ChatDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {
    @Provides
    @Singleton
    fun chatDB(context: Application): ChatDB {
        val mig = object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE ChatSummary ADD COLUMN name INTEGER")
            }
        }
        return Room.databaseBuilder(context, ChatDB::class.java, "ChatDB")
            /*.addMigrations(mig)*/ //adding a migration
            .createFromAsset("database/pre.db")
            .fallbackToDestructiveMigration() // handling failed or missing migration path
            .build()
    }

    @Provides
    fun sharedPrefs(application: Application): SharedPreferences {
        return application.getSharedPreferences("sample", Context.MODE_PRIVATE)
    }
}