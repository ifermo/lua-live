package org.baron.lua;


import java.util.concurrent.TimeUnit;

public class LuaLiveDemo {

    public static void main(String[] args) throws InterruptedException {

        final ExtLibrary lib = ExtLibrary.INSTANCE;
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            s1.append("Hello");
            s2.append("Rust");
        }

        for (int i = 0; i < 10000; i++) {
            String ret = lib.concat(s1.toString(), s2.toString());
            TimeUnit.MILLISECONDS.sleep(100);
        }
        TimeUnit.SECONDS.sleep(120);
    }
}