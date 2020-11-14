package org.maktab.scheduledlogs.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class QueryPreferences {
    private static final String PREF_LOG_QUERY = "logQuery";
    private static final String PREF_LAST_ID = "lastId";

    public static String getLogQuery(Context context) {
        return getSharedPreferences(context).getString(PREF_LOG_QUERY, null);
    }

    public static void setLogQuery(Context context, String query) {
        getSharedPreferences(context)
                .edit()
                .putString(PREF_LOG_QUERY, query)
                .apply();
    }

    public static String getLastId(Context context) {
        return getSharedPreferences(context).getString(PREF_LAST_ID, null);
    }

    public static void setLastId(Context context, String lastId) {
        getSharedPreferences(context)
                .edit()
                .putString(PREF_LAST_ID, lastId)
                .apply();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }
}
