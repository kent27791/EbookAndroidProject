package xuxu.ebookproject.model.epub.content;


import com.google.gson.annotations.SerializedName;

public class PackageViewModel {
    @SerializedName("opf:metadata")
    public MetadataViewModel Metadata;
    @SerializedName("manifest")
    public ManifestViewModel Manifest;
    @SerializedName("spine")
    public SpineViewModel Spine;
}
