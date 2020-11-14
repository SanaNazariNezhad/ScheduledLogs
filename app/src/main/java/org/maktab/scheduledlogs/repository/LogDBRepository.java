package org.maktab.scheduledlogs.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import org.maktab.scheduledlogs.database.LogDatabase;
import org.maktab.scheduledlogs.database.LogDatabaseDAO;
import org.maktab.scheduledlogs.model.Logs;
import org.maktab.scheduledlogs.remote.RandomNameService;
import org.maktab.scheduledlogs.remote.RetrofitInstance;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LogDBRepository implements IRepository {

    private static LogDBRepository sInstance;
    private static final String TAG = "LogDBRepository";
    private final RandomNameService mRandomNameService;
    private final MutableLiveData<List<Logs>> mLogItemsLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Logs>> getLogItemsLiveData() {
        return mLogItemsLiveData;
    }
    
    private LogDatabaseDAO mLogDAO;
    private Context mContext;

    public static LogDBRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new LogDBRepository(context);

        return sInstance;
    }

    private LogDBRepository(Context context) {
        mContext = context.getApplicationContext();
        Retrofit retrofit = RetrofitInstance.getInstance().getRetrofit();
        mRandomNameService = retrofit.create(RandomNameService.class);
        LogDatabase logDatabase = Room.databaseBuilder(mContext,
                LogDatabase.class,
                "log.db")
                .allowMainThreadQueries()
                .build();

        mLogDAO = logDatabase.getLogDatabaseDAO();
    }

    @Override
    public List<Logs> getLogs() {
        return mLogDAO.getLogs();
    }

    @Override
    public Logs getLog(long inputID) {
        return mLogDAO.getLog(inputID);
    }

    @Override
    public void updateLog(Logs logs) {
        mLogDAO.updateLog(logs);
    }

    @Override
    public void insertLog(Logs logs) {
        mLogDAO.insertLog(logs);
    }

    @Override
    public void insertLogs(Logs... logs) {
        mLogDAO.insertLogs(logs);
    }

    @Override
    public void deleteLog(Logs logs) {
        mLogDAO.deleteLog(logs);
    }

    public List<Logs> fetchLogsItems() {
        Call<List<Logs>> call = mRandomNameService.name();
        try {
            Response<List<Logs>> response = call.execute();
            return response.body();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    public void fetchItemsAsync() {
        Call<List<Logs>> call =
                mRandomNameService.name();

        call.enqueue(new Callback<List<Logs>>() {

            //this run on main thread
            @Override
            public void onResponse(Call<List<Logs>> call, Response<List<Logs>> response) {
                List<Logs> items = response.body();

                //update adapter of recyclerview
                mLogItemsLiveData.setValue(items);
            }

            //this run on main thread
            @Override
            public void onFailure(Call<List<Logs>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }
}
