package org.baron.lua;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface ExtLibrary extends Library {
    ExtLibrary INSTANCE = Native.load("libext.dylib", ExtLibrary.class);

    int char_cnt(String str);

    void free_str(String str);

    String concat(String str1,String str2);

    String eval(String str);

    String deliver(Pointer data,int len);
}