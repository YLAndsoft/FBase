package com.zhkj.exe;


import com.zhkj.log.Log;
import com.zhkj.project.ReadFileAddModule;
import com.zhkj.ui.UITools;

import confusion.ui.com.lib.up.tool.FileJSUtils;
import confusion.ui.com.lib.up.tool.FileUtils;
import confusion.ui.com.lib.up.tool.ValueUtils;

/**
 * @author: FYL
 * @time: 2018/11/16
 * @email:347933430@qq.com
 * @describe: confusion.ui.com.lib.up.exe confusion.ui.com.lib.up.exe.ExecuteDispose
 */
public class ExecuteDispose {

    public static void main(String[] args){
        //初始化 UI界面 并显示
        UITools.run(600, 800);
    }

    /**
     * 开始混淆
     */
    public static void startConfusion(){
        try{
            String js_path = ReadFileAddModule.getValue(ValueUtils.JS_PATH);
            String appProDirectory = ReadFileAddModule.getValue("appProDirectory");
            String clazzName = ReadFileAddModule.getValue(ValueUtils.CLAZZ_NAME);
            String isOpen = ReadFileAddModule.getValue(ValueUtils.IS_OPEN);
            Log.appendInfo("js目录："+js_path);//C:\Users\DN\Desktop\code.js
            Log.appendInfo("开始混淆....");
            if(js_path.isEmpty()){
                Log.appendErr("混淆失败！JS文件路径不能为空");
                return;
            }
            if("true".equals(isOpen)){
                FileUtils.start(js_path,clazzName);
            }else{
                FileJSUtils.start(js_path,clazzName);
            }
//                Log.appendInfo("图片目录："+appProDirectory);
//                Log.appendInfo("输入内容："+appFileName);
            Log.appendInfo("图片混淆正在开发中...");
            Log.appendInfo("混淆完成！");
        }catch (Exception e){
            e.printStackTrace();
            Log.appendErr(e.getMessage()+"");
            Log.appendErr("混淆异常！");
        }
    }

    public static void pause(){
        synchronized (UITools.class) {
            try {
                Log.appendInfo("等待输入！");
                UITools.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void goOn(){
        synchronized (UITools.class) {
            Log.appendInfo("启动！");
//            UITools.class.notify();
            startConfusion();
        }
    }

}
