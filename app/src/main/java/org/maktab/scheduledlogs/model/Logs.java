package org.maktab.scheduledlogs.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "logTable")
public class Logs {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long primaryId;

    @ColumnInfo(name = "current_date")
    private Date mCurrentDate;

    @ColumnInfo(name = "timestamp")
    private long mTimestamp;

    @ColumnInfo(name = "name")
    private String mName;

    public long getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(long primaryId) {
        this.primaryId = primaryId;
    }

    public Date getCurrentDate() {
        return mCurrentDate;
    }

    public void setCurrentDate(Date currentDate) {
        mCurrentDate = currentDate;
    }

    public long getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(long timestamp) {
        mTimestamp = timestamp;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Logs(Date currentDate, long timestamp, String name) {
        mCurrentDate = currentDate;
        mTimestamp = timestamp;
        mName = name;
    }
}
