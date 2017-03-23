package xuxu.ebookproject.helper;

import java.util.ArrayList;

/**
 * Created by phanx on 31/10/2016.
 */

public class StringHelper {
    public static <T> String join(ArrayList<T> arrayList, String fieldName, CharSequence delimiter) throws NoSuchFieldException, IllegalAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        if(arrayList.size() > 1){
            for(T t : arrayList){
                stringBuilder.append(delimiter);
                stringBuilder.append(t.getClass().getDeclaredField(fieldName).get(t));
            }
        }else{
            T t = arrayList.get(0);
            stringBuilder.append(t.getClass().getDeclaredField(fieldName).get(t));
        }
        return stringBuilder.toString();
    }
}
