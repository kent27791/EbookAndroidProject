package xuxu.ebookproject.console;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Program {
    public static void main(String [ ] args){
        //String json = "{\"item\":[{\"@href\":\"cover.jpeg\",\"@id\":\"cover\",\"@media-type\":\"image/jpeg\"},{\"@href\":\"OEBPS/text/thoi_tho_au__maxim_gorki_0001.html\",\"@id\":\"story_thoi_tho_au__maxim_gorki_0001\",\"@media-type\":\"application/xhtml+xml\"},{\"@href\":\"OEBPS/text/thoi_tho_au__maxim_gorki_0002.html\",\"@id\":\"story_thoi_tho_au__maxim_gorki_0002\",\"@media-type\":\"application/xhtml+xml\"},{\"@href\":\"OEBPS/text/thoi_tho_au__maxim_gorki_0003.html\",\"@id\":\"story_thoi_tho_au__maxim_gorki_0003\",\"@media-type\":\"application/xhtml+xml\"},{\"@href\":\"OEBPS/text/thoi_tho_au__maxim_gorki_0004.html\",\"@id\":\"story_thoi_tho_au__maxim_gorki_0004\",\"@media-type\":\"application/xhtml+xml\"},{\"@href\":\"OEBPS/text/thoi_tho_au__maxim_gorki_0005.html\",\"@id\":\"story_thoi_tho_au__maxim_gorki_0005\",\"@media-type\":\"application/xhtml+xml\"},{\"@href\":\"OEBPS/text/thoi_tho_au__maxim_gorki_0006.html\",\"@id\":\"story_thoi_tho_au__maxim_gorki_0006\",\"@media-type\":\"application/xhtml+xml\"},{\"@href\":\"OEBPS/text/thoi_tho_au__maxim_gorki_0007.html\",\"@id\":\"story_thoi_tho_au__maxim_gorki_0007\",\"@media-type\":\"application/xhtml+xml\"},{\"@href\":\"OEBPS/text/thoi_tho_au__maxim_gorki_0008.html\",\"@id\":\"story_thoi_tho_au__maxim_gorki_0008\",\"@media-type\":\"application/xhtml+xml\"},{\"@href\":\"OEBPS/text/thoi_tho_au__maxim_gorki_0009.html\",\"@id\":\"story_thoi_tho_au__maxim_gorki_0009\",\"@media-type\":\"application/xhtml+xml\"},{\"@href\":\"OEBPS/text/thoi_tho_au__maxim_gorki_0010.html\",\"@id\":\"story_thoi_tho_au__maxim_gorki_0010\",\"@media-type\":\"application/xhtml+xml\"},{\"@href\":\"OEBPS/text/thoi_tho_au__maxim_gorki_0011.html\",\"@id\":\"story_thoi_tho_au__maxim_gorki_0011\",\"@media-type\":\"application/xhtml+xml\"},{\"@href\":\"OEBPS/text/thoi_tho_au__maxim_gorki_0012.html\",\"@id\":\"story_thoi_tho_au__maxim_gorki_0012\",\"@media-type\":\"application/xhtml+xml\"},{\"@href\":\"OEBPS/text/thoi_tho_au__maxim_gorki_0013.html\",\"@id\":\"story_thoi_tho_au__maxim_gorki_0013\",\"@media-type\":\"application/xhtml+xml\"},{\"@href\":\"OEBPS/title.html\",\"@id\":\"title\",\"@media-type\":\"application/xhtml+xml\"},{\"@href\":\"page_styles.css\",\"@id\":\"page_css\",\"@media-type\":\"text/css\"},{\"@href\":\"stylesheet.css\",\"@id\":\"css\",\"@media-type\":\"text/css\"},{\"@href\":\"titlepage.xhtml\",\"@id\":\"titlepage\",\"@media-type\":\"application/xhtml+xml\"},{\"@href\":\"toc.ncx\",\"@id\":\"ncx\",\"@media-type\":\"application/x-dtbncx+xml\"}],\"itemref\":[{\"@idref\":\"cover\"},{\"@idref\":\"title\"},{\"@idref\":\"story_thoi_tho_au__maxim_gorki_0001\"},{\"@idref\":\"story_thoi_tho_au__maxim_gorki_0002\"},{\"@idref\":\"story_thoi_tho_au__maxim_gorki_0003\"}]}";
        //GsonBuilder gsonBuilder = new GsonBuilder();
        /*gsonBuilder.registerTypeAdapter(EPub.class, new JsonDeserializer<EPub>() {
            @Override
            public EPub deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonArray itemJsonArray = json.getAsJsonObject().getAsJsonArray("item");
                JsonArray itemRefArray = json.getAsJsonObject().getAsJsonArray("itemref");
                ArrayList<ItemGson> a = new ArrayList<ItemGson>();
                for (JsonElement itemRef : itemRefArray){
                    for(JsonElement item : itemJsonArray){
                        if(itemRef.getAsJsonObject().get("@idref").getAsString().equals(item.getAsJsonObject().get("@id").getAsString())){
                            a.add(new ItemGson(item.getAsJsonObject().get("@id").getAsString(), item.getAsJsonObject().get("@href").getAsString()));
                        }
                    }
                }
                return new EPub(a);
            }
        });*/

        /*Gson gson = gsonBuilder.create();
        EPub ePub = gson.fromJson(json, EPub.class);
        ArrayList<ItemGson> a = new ArrayList<>();
        for (ItemRef itemRef : ePub.ItemsRef){
            for (Item item : ePub.Items){
                if(item.Id.equals(itemRef.IdRef)){
                    a.add(new ItemGson(item.Id, item.Href));
                    break;
                }
            }
        }*/


        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", new Locale("vn", "vi"));
            Date a = dateFormat.parse("Mon, 26 Dec 2016 08:20:57 GMT");
            System.out.print(a.toString());
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

    }
}
