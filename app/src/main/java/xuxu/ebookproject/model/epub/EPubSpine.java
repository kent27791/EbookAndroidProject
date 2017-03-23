package xuxu.ebookproject.model.epub;

public class EPubSpine {
    public EPubSpine(String id, String href, String mediaType){
        this.Id = id;
        this.Href = href;
        this.MediaType = mediaType;
    }
    public String Id;
    public String Href;
    public String MediaType;
}
