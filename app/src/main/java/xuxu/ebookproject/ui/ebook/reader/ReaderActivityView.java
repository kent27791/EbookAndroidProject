package xuxu.ebookproject.ui.ebook.reader;


import java.util.ArrayList;

import xuxu.ebookproject.model.epub.EPubSpine;

public interface ReaderActivityView {
    void setViewPager(ArrayList<EPubSpine> ePubSpines);

    String getFileEPub();
}
