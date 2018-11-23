package com.zhkj.tools;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.zhkj.tools.ModeHelper.getMapValue;
import static com.zhkj.tools.ModeHelper.getRandomString;

/**
 * @author: FYL
 * @time: 2018/11/22
 * @email:347933430@qq.com
 * @describe: 修改方法所需的条件判断类
 */
public class EditMthodTools {
    public static final boolean isLog = false;//是否打印日志
    private static  boolean isStartEdit;//是否可以开始修改
    /**
     * 是否可以开始修改
     * @param line 判断的行
     * @param startClazzName 开始修改的类
     *  例子：var GameUI = (function(_super) {
     * @return
     */
    public static boolean isStartEdit(String line,String startClazzName){
        if(isStartEdit){return isStartEdit;}
        Matcher matcher = isStartClazz(line);
        if(matcher.find()){
            String clazzName = matcher.group(2);
            if(startClazzName.equals(clazzName)){
                isStartEdit=true;
            }
        }
        return isStartEdit;
    }

    /**
     * 判断是否是定义类属性的一行
     * @param line
     * @return
     *   _proto = GameScene.prototype;或者 var _proto = GameScene.prototype;
     */
    private static Matcher checkClazzKey(String line) {
        String regex = "(\\s*|var\\s+)(\\w+)(\\s*=\\s*\\w+\\.prototype(\\s*;?))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        if(isLog) System.out.println("行："+line+"---匹配结果："+matcher.find());
        return matcher;
    }

    /**
     * 修改定义类属性名的一行
     * @param line
     * @param randomAttribute
     * @return
     */
    public static String editClazzKey(String line,String randomAttribute){
        Matcher matcher  = checkClazzKey(line);
        if(matcher.find()){
            line = matcher.group(1)+randomAttribute+matcher.group(3);
        }
        return line;
    }

    /**
     * 检测一行是否是方法
     * 备注：区分特殊方法不做修改
     * //createChildren //特殊方法
     //_proto.toMiniP = function(data) {
     //_proto.toMiniP9 = function() {
     //var fuc=this.toMiniP9();
     * @param line
     */
    public static Matcher getMatcher(String line,String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        return matcher;
    }

    /**
     * 修改方法
     * @param line 需要判断的行
     * @param randomAttribute 修改的类名
     * @return
     */
    public static String editMethodName(String line, String randomAttribute) {
//        _proto.toMiniP = function(data) {  //第一种规则匹配
        String regex1 = "(\\s*|var\\s*)(\\w+)(\\s*\\.\\s*)(\\w+\\s*)(=\\s*function\\s*\\(\\s*\\w*\\)\\s*\\{\\s*.*)";
        Matcher matcher = getMatcher(line,regex1);
        if(matcher.find()){ //是方法行
            String methodValue = matcher.group(2);
            String randomMethodValue = createMethodName(methodValue);
            ModeHelper.saveMap(methodValue,randomMethodValue);//保存方法和修改之后的方法至集合里面
            line = matcher.group(1)+randomAttribute+matcher.group(3)+randomMethodValue+matcher.group(5);
            return line;
        }
//        String txt3 = "var fuc = this.toMiniP9(); "; //第二种规则匹配
        String regex2 = "(\\s*var\\s*)(\\w+)(\\s*=\\s*this\\s*\\.|=\\s*that\\s*\\.)(\\s*\\w+\\s*)(\\(\\s*\\w*\\s*\\)\\s*;\\s*.*)";
        Matcher matcher2 = getMatcher(line,regex2);
        if(matcher2.find()){
            String methodValue = matcher2.group(2);
            String randomMethodValue = createMethodName(methodValue);
            ModeHelper.saveMap(methodValue,randomMethodValue);//保存方法和修改之后的方法至集合里面
            line = matcher2.group(1)+randomAttribute+matcher2.group(3)+randomMethodValue+matcher2.group(5);
            return line;
        }
        return line;
    }
    public static String editAttribute(String line, String randomAttribute) {
//        String txt1 = " _proto.anim = null; ";
        String regex1 = "(\\s*\\w+\\s*)(\\.\\s*\\w+\\s*=\\s*\\w*\\s*;)";
        Matcher matcher = getMatcher(line,regex1);
        if(matcher.find()) {

        }
        return "";
    }
    /**
     * 创建随机方法名
     * @param methodValue
     * @return
     */
    private static String createMethodName(String methodValue){
        String randomMethodValue= getMapValue(methodValue);
        if(randomMethodValue==null){ //判断方法是否已经存在于Map集合里面,存在返回修改后的方法名，不存在返回NULL
            //生成随机4-6位数方法名
            randomMethodValue = getRandomString(ModeHelper.getRandomInt(4,6));
        }
        return randomMethodValue;
    }


    /**
     * 判断是否是特殊类
     * @param line 行
     * @return
     */
    public static boolean isSpecialClazz(String line){
        boolean isSpecial = false;//是否是特殊类
        Matcher matcher =isStartClazz(line);
        if(matcher.find()){
            String clazzName = matcher.group(2);
            for(int i=0;i<ModeRule.special.length;i++){
                if(ModeRule.special[i].equals(clazzName)){
                    isSpecial=true;
                    break;
                }
            }
        }
        return isSpecial;
    }

    /**
     * 是否是类的开始行
     * @param line
     * @return
     */
    public static Matcher isStartClazz(String line){
        //判断是否是类开始的正则
        String regex = "(\\s*+var\\s*+)(\\w+)(\\s*=\\s*\\(\\s*function\\s*\\(\\s*_super\\s*\\)\\s*\\{)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        if(isLog) System.out.println("行："+line+"---匹配结果："+matcher.find());
        return matcher;
    }


    /**
     * 判断是否是类的结束
     * @param line 行
     * @return
     */
    public static boolean isClazzEnd(String line){
        // })(Laya.Sprite);
        String regex = "\\s*}\\s*\\)\\s*\\(\\s*.*\\s*\\)\\s*;?\\s*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        if(isLog) System.out.println("行："+line+"---匹配结果："+matcher.find());
        return matcher.find();
    }


    /**
     * 保存修改后的字符串到文件中去
     * @param str
     */
    public static void saveFile(String str, BufferedWriter writer) {
        if(str==null){return;}
        try {
            writer.write(str+System.lineSeparator());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
