package com.example.baseline.util.constants;

import java.io.File;

public class AppUtil {
    public static String get_upload_path(String fileName){
        return new File("C:\\Users\\danil\\Desktop\\FullStackUdemyServlets\\Baseline5\\src\\main\\resources\\static\\images\\uploads").getAbsolutePath()+"\\"+fileName;
    }
}
