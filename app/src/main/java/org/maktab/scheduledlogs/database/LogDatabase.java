package org.maktab.scheduledlogs.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import org.maktab.scheduledlogs.model.Logs;

@Database(entities = {Logs.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class LogDatabase extends RoomDatabase {

    public abstract LogDatabaseDAO getLogDatabaseDAO();
}
