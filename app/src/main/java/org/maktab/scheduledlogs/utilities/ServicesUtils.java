package org.maktab.scheduledlogs.utilities;

import android.content.Context;
import android.util.Log;
import org.maktab.scheduledlogs.model.Logs;
import org.maktab.scheduledlogs.repository.LogDBRepository;

import java.util.List;

public class ServicesUtils {

    public static void poll(Context context, String tag) {
//        String query = QueryPreferences.getLogQuery(context);


        LogDBRepository repository = LogDBRepository.getInstance(context);
        List<Logs> logsList;

        logsList = repository.fetchLogsItems();


        if (logsList == null || logsList.size() == 0) {
            Log.d(tag, "Items from server not fetched");
            return;
        }
        for (int i = 0; i <logsList.size() ; i++) {
            repository.insertLog(logsList.get(i));
        }

    }

}
