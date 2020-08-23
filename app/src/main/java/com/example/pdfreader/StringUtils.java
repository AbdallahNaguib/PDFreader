package com.example.pdfreader;

public class StringUtils {
    public static String readableFileSize(int size){
        return (size/1000000)+"MB";
    }
}
