package org.maktab.scheduledlogs.database;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import org.maktab.scheduledlogs.model.Logs;

import java.util.List;

@Dao
public interface LogDatabaseDAO {

    @Update
    void updateLog(Logs logs);

    @Insert
    void insertLog(Logs logs);

    @Insert
    void insertLogs(Logs... logs);

    @Delete
    void deleteLog(Logs logs);

    @Query("SELECT * FROM logTable")
    List<Logs> getLogs();

    @Query("SELECT * FROM logTable WHERE id =:inputID")
    Logs getLog(long inputID);

}
