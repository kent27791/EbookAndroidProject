package xuxu.ebookproject.retrofit;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class JsonAndXmlConverters {

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Json {
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Xml {
    }
}
