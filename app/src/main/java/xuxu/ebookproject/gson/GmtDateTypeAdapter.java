package xuxu.ebookproject.gson;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class GmtDateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
    private final DateFormat dateFormat;

    public GmtDateTypeAdapter() {
        dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    @Override
    public synchronized JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        synchronized (dateFormat) {
            String dateFormatAsString = dateFormat.format(date);
            return new JsonPrimitive(dateFormatAsString);
        }
    }

    @Override
    public synchronized Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        try {
            synchronized (dateFormat) {
                return dateFormat.parse(jsonElement.getAsString());
            }
        } catch (ParseException e) {
            throw new JsonSyntaxException(jsonElement.getAsString(), e);
        }
    }
}
