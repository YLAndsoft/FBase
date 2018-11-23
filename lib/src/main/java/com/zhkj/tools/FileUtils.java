package com.zhkj.tools;

import java.io.File;

/**
 * @author: FYL
 * @time: 2018/11/22
 * @email:347933430@qq.com
 * @describe: 文件管理类
 */
public class FileUtils {


    /**
     * 判断文件是否存在
     * @param file
     * @return
     */
    public static boolean isExists(File file){
//        File file = new File(fi);
        if(file.exists())return true;
        return false;
    }
    /**
     * 创建要修改的文件
     * @param file
     */
    public static void createUpFile(File file){
        try {
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("创建文件异常："+e.getMessage());
        }
    }


}
