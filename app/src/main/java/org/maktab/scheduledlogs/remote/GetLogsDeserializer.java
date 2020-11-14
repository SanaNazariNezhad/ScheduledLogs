package org.maktab.scheduledlogs.remote;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.maktab.scheduledlogs.model.Logs;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GetLogsDeserializer implements JsonDeserializer<List<Logs>> {

    @Override
    public List<Logs> deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        String stringName;
        List<Logs> logsList = new ArrayList<>();

        JsonObject bodyObject = json.getAsJsonObject();
        String name = bodyObject.get("name").getAsString();

        stringName = name;
        long timeStamp = System.currentTimeMillis()/1000;
        Date currentDate = Calendar.getInstance().getTime();

        Logs logs = new Logs(currentDate,timeStamp,stringName);
        logsList.add(logs);
        return logsList;
    }


}
