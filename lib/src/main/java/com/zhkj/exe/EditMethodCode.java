package com.zhkj.exe;

import com.zhkj.tools.EditMthodTools;
import com.zhkj.tools.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import confusion.ui.com.lib.up.log.Log;
import confusion.ui.com.lib.up.tool.ModeHelper;

/**
 * @author: FYL
 * @time: 2018/11/22
 * @email:347933430@qq.com
 * @describe: 修改方法
 */
public class EditMethodCode {

    private static String parentPath;//当前选择修改文件的父目录，用于保存修改完成后的文件
    private static File tmpFile;//保存修改方法名之后的文件
    private static String tmpFileName;//保存修改方法名之后的文件名称

    private static String startClazzName;//开始修改的类名
    private static String randomAttribute;//随机生成的类属性名
    /**
     * 执行
     * @param file 需要修改的JS文件路径
     * @param clazzName 从哪个类开始修改
     */
    public static void exe(String file,String clazzName){
        boolean exists = FileUtils.isExists(new File(file));
        if(!exists){
            System.out.println("要修改的文件不存在！");
            return;
        }else{
            parentPath = new File(file).getParent();
        }
        startClazzName = clazzName;
        editMethodName(file);
    }

    /**
     * 修改方法名
     */
    private static void editMethodName(String file){
        //1.创建修改方法之后的临时文件
        tmpFile = new File(parentPath+tmpFileName);
        FileUtils.createUpFile(tmpFile);
        BufferedReader br = null;
        FileOutputStream outputStream = null;
        OutputStreamWriter streamWriter = null;
        BufferedWriter w = null;
        try{
            //2.创建流
            br = new BufferedReader(new FileReader(new File(file)));//构造一个BufferedReader类来读取文件
            outputStream = new FileOutputStream(tmpFile);
            streamWriter = new OutputStreamWriter(outputStream);
            w = new BufferedWriter(streamWriter);
            //3.读取行
            String line = null;
            String tmp = null;
            randomAttribute = ModeHelper.getRandomString(6);//生成类属性名
            Log.appendInfo("生成随机类变量名："+randomAttribute);
            String attribute = null;//修改之前的类属性名
            boolean isSpecial = false;
            while((line = br.readLine())!=null) {
                tmp = line;
                //4.1.判断是否可以开始修改
                if(EditMthodTools.isStartEdit(tmp,startClazzName)){
                    //4.2.特殊类，做特殊处理
                    if(EditMthodTools.isSpecialClazz(tmp)) isSpecial=true;//判断是否是特殊类
                    if(EditMthodTools.isClazzEnd(tmp)) isSpecial=false;//当前一行是一个类的结束，设置为flase
                    if(isSpecial){ //是否是特殊类里面的内容
                        EditMthodTools.saveFile(tmp,w);
                        continue;
                    }
                    tmp = EditMthodTools.editClazzKey(line,randomAttribute);//修改类属性名的一行
                    tmp = EditMthodTools.editMethodName(line,randomAttribute);//修改方法名
                    tmp = EditMthodTools.editAttribute(line,randomAttribute);//修改类名点属性名

                }
                //保存修改之后或者未修改的行
                EditMthodTools.saveFile(tmp,w);
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.appendErr("修改方法出现异常："+e.getMessage());
        }finally {
            try {
                if(br!=null) {
                    br.close();
                }
                if(outputStream!=null){
                    outputStream.close();
                }
                if(w!=null){
                    w.close();
                }
                if(streamWriter!=null){
                    streamWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



}
