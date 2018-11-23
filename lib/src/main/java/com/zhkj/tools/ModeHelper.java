package com.zhkj.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import confusion.ui.com.lib.up.log.Log;

/**
 * @author: FYL
 * @time: 2018/11/13
 * @email:347933430@qq.com
 * @describe: 判断规则复制类
 */
public class ModeHelper {

    /**
     * 2.0修改
     **/
    private static boolean isStartUpdate = false; //是否可以开始修改
    private static boolean isStartQuote = false; //是否可以开始检测调用方法的行
    private static boolean isStartInsertCode = false; //是否可以开始插入无用代码


    public static Map<String,String> methodMap = new HashMap<>();//保存方法名，修改后的方法名

    public static final String NUMBERS = "0123456789";
    public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static char [] letter = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
            ,'1','2','3','4','5','6','7','8','9','0','_','$'};

    public static void clear() {
        isStartUpdate = false;
        isStartQuote = false;
        isStartInsertCode = false;
    }

    /**
     * 寻找可修改定义的方法头
     *
     * @param line ：var _proto = Start.prototype;
     * @return
     */
    public static boolean checkMethodKey(String line,String clazzName) {
        if ("".equals(line) || line == null) return false;
        //var _proto = Start.prototype;
        if (line.contains(ModeRule.METHOD_KEY)&&line.contains(ModeRule.D)&&line.contains(ModeRule.DH)) { //检查一行是否包含var，prototype两个关键字
            int index = line.indexOf(ModeRule.METHOD_KEY);
            if (index > 0) {
                String keyFrist = line.charAt(index - 1) + ""; //得到关键字前面一位
                String keyLast = line.charAt(index + ModeRule.METHOD_KEY.length()) + ""; //得到关键字后面一位
                if (("".equals(keyFrist.trim()) || ".".equals(keyFrist.trim()) && //判断前面一位字符是否是空格或者“.”,如果是，返回true
                        ("".equals(keyLast.trim()) || ";".equals(keyLast.trim())))) {  //判断后面一位是否是空格或者";",如果是返回true
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    /**
     * 寻找可修改定义的方法头
     *
     * @param line ：var _proto = Start.prototype;
     * @return
     */
    public static boolean checkClazzKey(String line) {
        if ("".equals(line) || line == null) return false;
        //var _proto = Start.prototype;
        if (line.contains(ModeRule.METHOD_KEY)&&line.contains(ModeRule.D)&&line.contains(ModeRule.DH)) { //检查一行是否包含var，prototype两个关键字
            int index = line.indexOf(ModeRule.METHOD_KEY);
            char keyFrist,keyLast;
            if (index > 0) {
                try{
                    keyFrist = line.charAt(index - 1); //得到关键字前面一位
                    keyLast = line.charAt(index + ModeRule.METHOD_KEY.length()); //得到关键字后面一位
                }catch (Exception e){
                    keyFrist=' ';keyLast=' ';
                }
                if(!isLetterNumber(keyFrist)&&!isLetterNumber(keyLast)){ //前后不能有数字，字母，下划线，美元符号
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * 检查行是否有调用修改前的方法
     *
     * @param line
     * @param methodName
     * @return
     */
    public static boolean checkContains(String line, String methodName) {
        if (null == line || "".equals(line)) {
            return false;
        }
        if (line.trim().equals("")) {
            return false;
        }
        if (null == methodName || "".equals(methodName)) {
            return false;
        }
        boolean r1 = line.contains(methodName);
        if (r1) { //检查一行是否包含关键字
            try {
                int index = line.indexOf(methodName);
                String keyFrist = line.charAt(index - 1) + ""; //得到关键字后面一位
                String keyLast = line.charAt(index + methodName.length()) + ""; //得到关键字前面一位
                if (("".equals(keyFrist.trim()) || ".".equals(keyFrist.trim())) && //判断前面一位字符是否是空格或者“.”,如果是，返回true
                        ("".equals(keyLast.trim()) || "(".equals(keyLast.trim()))) {  //判断后面一位是否是空格或者"(",如果是返回true
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                Log.appendErr("修改调方异常：" + line + "\r\n" + "关键字是：" + methodName);
                return false;
            }
        }
        return false;
    }

    /**
     * 修改方法名所需
     * 判断传递过来的一行是否包含方法规则
     *
     * @param line
     * @return
     */
    public static boolean isMethodRule(String line) {
        //  _proto.initGenerator = function(_monsterBox,_tower)
        if ("".equals(line) || line == null) return false;
        if (line.contains(ModeRule.DH) && line.contains(ModeRule.KEY) && line.contains(ModeRule.ZKH) &&
                line.contains(ModeRule.FKH) && line.contains(ModeRule.ZDKH) && line.contains(ModeRule.D)) {
            //加这一判断是为了更精确判断是否是方法
            int index = line.indexOf(ModeRule.KEY);
            if (index > 0) {
                String keyFrist = line.charAt(index - 1) + ""; //得到关键字前面一位
                String keyLast = line.charAt(index + ModeRule.KEY.length()) + ""; //得到关键字后面一位
                if (("".equals(keyFrist.trim()) || "=".equals(keyFrist.trim())) && //判断前面一位字符是否是空格或者“=”,如果是，返回true
                        ("".equals(keyLast.trim()) || "(".equals(keyLast.trim()))) {  //判断后面一位是否是空格或者"(",如果是返回true
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * 判断传递过来的一行是否包含方法规则
     *
     * @param line
     * @return
     */
    public static boolean isRule1(String line) {
        if ("".equals(line) || line == null) return false;
        if (line.contains(ModeRule.DH) && line.contains(ModeRule.KEY) && line.contains(ModeRule.ZKH) &&
                line.contains(ModeRule.FKH) && line.contains(ModeRule.ZDKH) && line.contains(ModeRule.D)) {
            line.indexOf(ModeRule.DH + " " + ModeRule.KEY);
            //加这一判断是为了更精确判断是否是方法
            if (line.indexOf(ModeRule.DH + " " + ModeRule.KEY) > 0
                    || line.indexOf(ModeRule.DH + ModeRule.KEY) > 0) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * 是否插入无用代码
     *
     * @return
     */
    public static boolean isInsertCode(String line) {
        if (line == null) {
            return false;
        }
        if (line.contains(ModeRule.IF) && line.contains(ModeRule.ZKH) &&
                line.contains(ModeRule.FKH) && line.contains(ModeRule.ZDKH)) {
            if(line.trim().endsWith(ModeRule.ZDKH)){ //判断一行结束位置是否是"{"
                return true;
            }
        }
        return false;
    }

    /**
     * 2.0,是否可以从指定类开始修改
     *
     * @param line
     * @param clazzName
     * @return
     */
    public static boolean isStart(String line, String clazzName) {
        if (!isStartUpdate) {
            isStartUpdate = isStartUpdate(line, clazzName);
        }
        return isStartUpdate;
    }

    /**
     * 检查是否从指定类可以开始检测调用的方法行
     * @param line
     * @param clazzName
     * @return
     */
    public static boolean isStartQuote(String line, String clazzName) {
        if (!isStartQuote) {
            isStartQuote = isStartUpdate(line, clazzName);
        }
        return isStartQuote;
    }

    /**
     * 是否可以开始检查并插入无用代码
     * @param line
     * @param clazzName
     * @return
     */
    public static boolean isStartInsertCode(String line, String clazzName) {
        if (!isStartInsertCode) {
            isStartInsertCode = isStartUpdate(line, clazzName);
        }
        return isStartInsertCode;
    }

    /**
     * 是否可以开始修改
     *
     * @param line      需要判断的行
     * @param clazzName 从ClazzName类开始修改
     * @return
     */
    public static boolean isStartUpdate(String line, String clazzName) {
        if (line.contains(ModeRule.VAR) &&
                line.contains(clazzName) &&
                line.contains(ModeRule.DH) &&
                line.contains(ModeRule.KEY) &&
                line.contains(ModeRule.KEY2) &&
                line.contains(ModeRule.ZDKH)) {
            int index = line.indexOf(ModeRule.KEY2);
            if (index > 0) {
                String keyFrist = line.charAt(index - 1) + ""; //得到关键字前面一位
                String keyLast = line.charAt(index + ModeRule.KEY2.length()) + ""; //得到关键字后面一位
                if (("".equals(keyFrist.trim()) || "(".equals(keyFrist.trim())) && //判断前面一位字符是否是空格或者“(”,如果是，返回true
                        ("".equals(keyLast.trim()) || ")".equals(keyLast.trim()))) {  //判断后面一位是否是空格或者")",如果是返回true
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是类的开始
     *
     * @param line
     * @return
     */
    public static boolean isClazzStart(String line) {
        if (line.contains(ModeRule.VAR) &&
                line.contains(ModeRule.DH) &&
                line.contains(ModeRule.KEY) &&
                line.contains(ModeRule.KEY2) &&
                line.contains(ModeRule.ZDKH)) {
            int index = line.indexOf(ModeRule.KEY2);
            if (index > 0) {
                String keyFrist = line.charAt(index - 1) + ""; //得到关键字前面一位
                String keyLast = line.charAt(index + ModeRule.KEY2.length()) + ""; //得到关键字后面一位
                if (("".equals(keyFrist.trim()) || "(".equals(keyFrist.trim())) && //判断前面一位字符是否是空格或者“(”,如果是，返回true
                        ("".equals(keyLast.trim()) || ")".equals(keyLast.trim()))) {  //判断后面一位是否是空格或者")",如果是返回true
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是类的结束
     *
     * @param line
     * @return
     */
    public static boolean isClazzEnd(String line) {
        if(line.contains(ModeRule.ZKH)&&line.contains(ModeRule.FKH)&&line.contains(ModeRule.FDKH)&&line.contains(ModeRule.FH)){
            int index = line.indexOf(ModeRule.FKH);//截取")"，判断前面一个字符串是否是"}",后面一位是"(" 并且最后一位是";"
            //line.charAt();
            if (index > 0) {
                String keyFrist = line.charAt(index - 1) + ""; //得到关键字前面一位
                String keyLast = line.charAt(index + 1) + ""; //得到关键字后面一位
                if (("}".equals(keyFrist.trim())) &&  //判断前面一位字符是否是“}”,如果是，返回true
                        ("".equals(keyLast.trim()) || "(".equals(keyLast.trim()))&& //判断后面一位是否是空格或者"(",如果是返回true
                        (line.trim().endsWith(";"))&&line.trim().startsWith("}")) { //判断第一位是否是"}"，最后一位是否是";"
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是数字或者字母
     * @param str
     * @return
     */
    public static boolean isLetterNumber(char str){
        if(str==' ')return false;
        boolean isLetter=false;
        for(int i=0;i<letter.length;i++){
            if(letter[i]==str){
                isLetter=true;
                break;
            }
        }
        return isLetter;
    }

    /**
     * 判断方法名是否是关键方法名
     * @param methodKey 方法名
     * @return
     */
    public static boolean isMethodKey(String methodKey){
        boolean isMethodKey = false;
        if("".equals(methodKey)||methodKey==null){return isMethodKey;}
        for(int i = 0; i< ModeRule.methodKey.length; i++){
            if(ModeRule.methodKey[i].equals(methodKey)){
                isMethodKey=true;
                break;
            }
        }
        return isMethodKey;
    }

    /**
     * 根据方法名获取已经混淆后的方法
     * @param methods
     * @return
     */
    public static String getMapValue(String methods) {
        if(null==methodMap||methodMap.size()<=0){return null;}
        if(null==methods||"".equals(methods)){return null;}
        String mapMethod = null;
        for (Map.Entry<String, String> entry : methodMap.entrySet()) {
            String method = entry.getKey();//得到方法名
            if(methods.equals(method)){
                mapMethod = entry.getValue();
            }
        }
        return mapMethod;
    }

    /**
     * 保存修改的方法和修改后的方法
     * @param methods 方法名
     * @param newMethod 修改后的方法名
     * @return
     */
    public static void saveMap(String methods,String newMethod) {
        if(null==methodMap){return;}
        if(null==methods||"".equals(methods)){return;}
        boolean isSave=false;//是否已经保存
        for (Map.Entry<String, String> entry : methodMap.entrySet()) {
            String method = entry.getKey();//得到方法名
            //判断方法名是否已经保存
            if(methods.equals(method)){
                isSave = true;
                break;
            }
        }
        if(!isSave){
            methodMap.put(methods,newMethod);
        }
    }
    /**
     * 判断是否是引用方法的行
     * @param line 引用的行
     * @param methodName 引用修改之前的方法名
     * @return
     */
    public static boolean isQuoteMethod(String line,String methodName){
        //判断一行是否包含 "(" ，"方法名" ，";"
        if(line.contains(methodName)&&line.contains(ModeRule.D)){
            String frist;
            char methodLast;
            int methodIndex = line.indexOf(methodName);//得到方法名出现的下标
            if(methodIndex>0){
                methodLast= line.charAt(methodIndex+methodName.length());
                try{
                    frist  = line.charAt(methodIndex-1)+"";
                }catch (StringIndexOutOfBoundsException e){
                    frist = "";//说明方法前面没有字符了.
                }
                if((ModeRule.D.equals(frist)||"".equals(frist))&&!isLetterNumber(methodLast)){   //判断方法前面是不是""或者前面是不是"."
                    return true;
                }
                //判断后面是否还有第二次出现
                int index2 = line.indexOf(methodName,methodIndex+methodName.length());
                if(index2>0){
                    methodLast= line.charAt(index2+methodName.length());
                    try{
                        frist  = line.charAt(index2-1)+"";
                    }catch (StringIndexOutOfBoundsException e){
                        frist = "";//说明方法前面没有字符了.
                    }
                    boolean letter = isLetterNumber(methodLast);
                    if((ModeRule.D.equals(frist)||"".equals(frist))&&letter==false){   //判断方法前面是不是""或者前面是不是"."
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 生成无用方法
     *
     * @param menthodKey 方法头
     */
    public static String getRandomNoCode(String menthodKey) {
        StringBuilder str = new StringBuilder();
        int randomNumber = getRandomInt(3, 8);
        str.append(menthodKey + ModeRule.D);//_proto.
        str.append(getRandomString(randomNumber));//方法名
        str.append(" = function () { " + "\r\n");
        str.append(getRandomCode());//插入方法内容
        str.append("}\r\n");

        return str.toString();
    }

    /**
     * 生成随机字符串 大小写混合
     *
     * @param length 生成字符串的个数
     * @return
     */
    public static String getRandomString(int length) {
        return getRandom(LETTERS.toCharArray(), length);
    }

    /**
     * @param min 最小
     * @param max 最大
     * @return 返回一个范围的数值
     */
    public static int getRandomInt(int min, int max) {

        if (min > max) {
            return 0;
        }
        if (min == max) {
            return min;
        }
        return min + new Random().nextInt(max - min);
    }

    /**
     * 生成随机int数组
     *
     * @return
     */
    public static String getRandomIntArray() {
        int randomNumber = getRandomInt(3, 8);
        StringBuilder str = new StringBuilder();
        str.append("var ");
        str.append(getRandomString(randomNumber));
        str.append(" = [");
        for (int i = 1; i <= randomNumber; i++) {
            if (i == randomNumber) {
                str.append(getRandomInt(1, 100));
            } else {
                str.append(getRandomInt(1, 100) + ",");
            }
        }
        str.append("];");
        return str.toString() + "\r\n";
    }

    /**
     * 生成随机String数组
     *
     * @return
     */
    public static String getRandomStringArray() {
        int randomNumber = getRandomInt(3, 8);
        StringBuilder str = new StringBuilder();
        str.append("var ");
        str.append(getRandomString(randomNumber));
        str.append(" = [");
        for (int i = 1; i <= randomNumber; i++) {
            if (i == randomNumber) {
                str.append('"' + getRandomString(randomNumber) + '"');
            } else {
                str.append('"' + getRandomString(randomNumber) + '"' + ",");
            }
        }
        str.append("];");
        return str.toString() + "\r\n";
    }

    /**
     * 生成随机Char数组
     *
     * @return
     */
    public static String getRandomCharArray() {
        int randomNumber = getRandomInt(3, 8);
        StringBuilder str = new StringBuilder();
        str.append("var ");
        str.append(getRandomString(randomNumber));
        str.append(" = [");
        for (int i = 1; i <= randomNumber; i++) {
            if (i == randomNumber) {
                str.append("'" + getRandomString(randomNumber) + "'");
            } else {
                str.append("'" + getRandomString(randomNumber) + "',");
            }
        }
        str.append("];");
        return str.toString() + "\r\n";
    }

    /**
     * 生成随机定义int类型代码
     *
     * @return
     */
    public static String getRandomInt() {
        int randomNumber = getRandomInt(3, 8);
        StringBuilder str = new StringBuilder();
        str.append("var ");
        str.append(getRandomString(randomNumber));
        str.append(" = ");
        str.append(getRandomInt(0, 9));
        str.append(";");
        return str.toString() + "\r\n";
    }

    /**
     * 生成随机定义String类型代码
     *
     * @return
     */
    public static String getRandomString() {
        int randomNumber = getRandomInt(3, 8);
        StringBuilder str = new StringBuilder();
        str.append("var ");
        str.append(getRandomString(randomNumber));
        str.append(" = " + "'");
        str.append(getRandomString(randomNumber));
        str.append("'" + ";");
        return str.toString() + "\r\n";
    }

    /**
     * 生成随机定义double类型代码
     *
     * @return
     */
    public static String getRandomDouble() {
        int randomNumber = getRandomInt(3, 8);
        StringBuilder str = new StringBuilder();
        str.append("var ");
        str.append(getRandomString(randomNumber));
        str.append(" = ");
        str.append(getRandomInt(0, 9) + "." + getRandomInt(0, 9));
        str.append(";");
        return str.toString() + "\r\n";
    }

    /**
     * 生成随机定义bollean类型代码
     *
     * @return
     */
    public static String getRandomBoolean() {
        int randomNumber = getRandomInt(3, 8);
        StringBuilder str = new StringBuilder();
        str.append("var ");
        str.append(getRandomString(randomNumber));
        str.append(" = ");
        int random = getRandomInt(0, 9);
        if (random % 2 == 0) {
            str.append("true");
        } else {
            str.append("false");
        }
        str.append(";");
        return str.toString() + "\r\n";
    }

    /**
     * 生成随机定义float类型代码
     *
     * @return
     */
    public static String getRandomFloat() {
        int randomNumber = getRandomInt(3, 8);
        StringBuilder str = new StringBuilder();
        str.append("var ");
        str.append(getRandomString(randomNumber));
        str.append(" = ");
        str.append(getRandomInt(1, 9) + "." + getRandomInt(1, 9));
        str.append(";");
        return str.toString() + "\r\n";
    }

    /**
     * 生成随机定义对象类型代码
     *
     * @return
     */
    public static String getRandomObject() {
        int randomNumber = getRandomInt(3, 8);
        StringBuilder str = new StringBuilder();
        str.append("var ");
        str.append(getRandomString(randomNumber));
        str.append(" = {");
        for (int i = 0; i < randomNumber; i++) {
            str.append(getRandomString(randomNumber) + ":" + getRandomObject(getRandomInt(0, 3)) + "," + "\r\n");
        }
        str.append("};");
        return str.toString() + "\r\n";
    }

    private static Object getRandomObject(int random) {
        switch (random) {
            case 0:
                return "'" + getRandomString(4) + "'";//返回随机String
            case 1:
                return getRandomInt(0, 9);
            case 2:
                return getRandomInt(0, 9) % 2 == 0 ? true : false;
        }
        return "0";
    }

    private static String getRandom(char[] sourceChar, int length) {
        if (sourceChar == null || sourceChar.length == 0 || length < 0) {
            return null;
        }
        StringBuilder str = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str.append(sourceChar[random.nextInt(sourceChar.length)]);
        }
        return str.toString();
    }

    /**
     * 随机从9种无用代码中产生
     *
     * @param number //产生几条无用代码
     * @return
     */
    public static String getRandomCode(int number) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < number; i++) {
            str.append(getRandomCode());
        }
        return str.toString();
    }

    /**
     * 随机从9中无用代码中产生
     *
     * @return
     */
    public static String getRandomCode() {
        int random = getRandomInt(0, 9);
        switch (random) {
            case 0:
                return getRandomString();
            case 1:
                return getRandomInt();
            case 2:
                return getRandomFloat();
            case 3:
                return getRandomDouble();
            case 4:
                return getRandomBoolean();
            case 5:
                return getRandomObject();
            case 6:
                return getRandomIntArray();
            case 7:
                return getRandomStringArray();
            case 8:
                return getRandomCharArray();
            default:
                return getRandomString();
        }

    }

    /**
     * 截取类名
     *
     * @param line 类名出现的行
     * @return
     * @throws Exception
     */
    public static String getClazzName(String line) throws Exception {
        int indexDH = line.indexOf(ModeRule.DH);
        int indexV = line.indexOf(ModeRule.VAR);
        return line.substring(indexV+ ModeRule.VAR.length(),indexDH).trim();//得到=号后面需要拼接的字符串
    }


    public static void main(String args[]) {
        String ss = "  _proto.toMiniP = function(data) {";
//        String ss = "_proto.toMiniP9 = function() { ";
        String line = EditMthodTools.editMethodName(ss,"AAAAA");
        System.out.println(line);
    }

    /**
     * 特殊判断
     *
     * @param line
     * @param method
     * @return
     */
    public static boolean isUP(String line, String method) {
        if (method.contains("1")) { //判断要修改的方法名是否包含1
            //包含1直接修改，不管
            int indexOf = line.indexOf(method);
            if (indexOf < 0) {
                System.out.println("方法名：" + method + ",内容：" + line + "结果：不能修改");
                return false;
            }
            System.out.println("方法名：" + method + ",内容：" + line + "结果：可以修改");
        } else {
            int kf = line.indexOf(method);//得到出现的下标
            String result1 = line.substring(kf, kf + 1 + (method.length()));//得到要替换的字符
            if (kf < 0) {
                System.out.println("方法名：" + method + ",内容：" + line + "结果：不能修改");
                return false;
            } else {
                if (result1.contains("1")) {
                    System.out.println("方法名：" + method + ",内容：" + line + "结果：不能修改");
                    return false;
                } else {
                    System.out.println("方法名：" + method + ",内容：" + line + "结果：可以修改");
                    return true;
                }
            }
        }
        return false;
    }



}
