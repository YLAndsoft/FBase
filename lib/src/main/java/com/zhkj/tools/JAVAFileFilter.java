package com.zhkj.tools;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * @author: FYL
 * @time: 2018/11/17
 * @email:347933430@qq.com
 * @describe: 文件过滤器
 */
public class JAVAFileFilter extends FileFilter {

    String ext;

    public JAVAFileFilter(String ext) {
        this.ext = ext;
    }

    /* 在accept()方法中,当程序所抓到的是一个目录而不是文件时,我们返回true值,表示将此目录显示出来. */
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }else
        {
            String name = file.getName();
            if(name.endsWith(".js"))
                return true;
            else
                return false;
        }
    }

    // 实现getDescription()方法,返回描述文件的说明字符串!!!
    public String getDescription() {
        if (ext.equals(".js")){
            return "JS(*.js)";
        }
        return "";
    }

}
