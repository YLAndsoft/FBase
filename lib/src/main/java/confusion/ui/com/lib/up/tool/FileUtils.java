package confusion.ui.com.lib.up.tool;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import confusion.ui.com.lib.up.log.Log;

/**
 * @author: FYL
 * @time: 2018/11/13
 * @email:347933430@qq.com
 * @describe: 文件操作相关
 */
public class FileUtils {

    private static  String parentPath = "";
    private static final String tmpFileName = "/tmpFile.js";//修改方法名之后的文件
    private static final String upFileName = "/upFile.js";//修改调用方法名之后的文件
    private static final String newFileName = "/newFile.js";//插入无用代码之后的文件
    private static File tmpFile,upFile,newFile;

    //key:类名，value: map1:key:类变量名，value: map2:key:修改前的方法名，value:修改后的方法名
    //Map<类名,Map<修改之后的类变量名,Map<方法名，修改之后的方法名>>> map;
//    private static Map<String,Map<String,Map<String,String>>> map = new HashMap<>();
    private static Map<String,String> clazzMap = new HashMap<>();//保存类名，类变量名
    private static Map<String,String> map = new HashMap<>();//保存方法名，修改后的方法名

    /**
     * 2.0启动方法
     * @param fi 修改文件的路径
     * @param clazzName 指定开始修改的类
     */
    public static void start(String fi,String clazzName){
        //1.判断文件是否存在
        boolean exists = isExists(new File(fi));
        if(!exists){
            System.out.println("要修改的文件不存在！");
            return;
        }else{
            File file = new File(fi);
            parentPath = file.getParent();
        }
        reviseMethod(fi,clazzName);//修改方法
        reviseQuoteMethod(clazzName);//修改方法被引用的地方
        insertDeadCode(clazzName);//插入无用的代码
        deleteFile();
    }

    /**
     * 修改方法
     * @param fi 修改文件路径
     * @param clazz 指定从哪个类开始修改
     */
    private static void reviseMethod(String fi,String clazz){
        //1.创建修改方法之后的临时文件
        tmpFile = new File(parentPath+tmpFileName);
        createUpFile(tmpFile);
        BufferedReader br = null;
        FileOutputStream outputStream = null;
        OutputStreamWriter streamWriter = null;
        BufferedWriter w = null;
//        Map<String,String> methodMap = null;//用来保存方法名，修改之后的方法名
//        Map<String,Map<String,String>> clazzMap = null;//用来保存类变量名和methodMap
        try{
            //2.创建流
            br = new BufferedReader(new FileReader(new File(fi)));//构造一个BufferedReader类来读取文件
            outputStream = new FileOutputStream(tmpFile);
            streamWriter = new OutputStreamWriter(outputStream,ModeRule.encoding);
            w = new BufferedWriter(streamWriter);
            //3.读取行
            String s = null;
            String tmp = null;
            String clazzName = null; //类名
            String clazzValue=null;//类属性名
            String randomAttribute = null;//修改后的类属性名
            while((s = br.readLine())!=null) { //使用readLine方法，一次读一行
                tmp = s;
                //4.判断是否可以从指定类开始修改
                if(ModeHelper.isStart(s,clazz)){
                    if(ModeHelper.isClazzStart(s)){ //5.判断是否是类的开始
                        //5.1 截取，得到类名，并保存至map集合中
//                        methodMap = new HashMap<>();//初始化
//                        clazzMap = new HashMap<>();//初始化
                        clazzName = ModeHelper.getClazzName(s);//得到类名
                        clazzName = clazzName.trim();
//                        clazzMap.put(clazzName,"");//保存类名至map集合里面
                    }else if(ModeHelper.checkMethodKey(s,clazzName)){ //判断是否是定义类变量名的一行
                        //截取得到定义的类属性名 //  _proto = GameUILogic.prototype; var _proto = GameUILogic.prototype;
                        if(s.trim().startsWith(ModeRule.VAR)){ //判断开头是否是var
                            int varIndex = s.indexOf(ModeRule.VAR);
                            int indexDH = s.indexOf(ModeRule.DH);
                            clazzValue = s.substring(varIndex+ModeRule.VAR.length()+1,indexDH).trim();//截取得到类属性名,下标+1，主要是去掉"."
                        }else{
                            int indexDH = s.indexOf(ModeRule.DH);
                            clazzValue = s.substring(0,indexDH).trim();//截取得到类属性名,下标+1，主要是去掉"."
                        }
                        randomAttribute = ModeHelper.getRandomString(6);//生成类变量名
                        tmp = "var "+randomAttribute+" = "+clazzName+".prototype;";
                        Log.appendInfo("生成随机类变量名："+randomAttribute);
                        clazzMap.put(clazzName,randomAttribute);//保存类名,类变量名到集合里面
                    }else if(s.contains(ModeRule.D)&&s.contains(ModeRule.FH)){ //判断一行是否包含定义属性，   _proto.anim = null;
                        if(clazzValue!=null&&s.contains(clazzValue)){

                            int index = s.indexOf(clazzValue);
                            if(index>0){
                                char last,frist;
                                last = s.charAt(index+clazzValue.length());//判前面是否有字符
                                try{
                                    frist = s.charAt(index-1);//判断前面是否有字符
                                }catch (Exception e){
                                    frist=' ';
                                }
                                if(!ModeHelper.isLetterNumber(last)&&!ModeHelper.isLetterNumber(last)){
                                    //去做修改
                                    String first = s.substring(0,index);
                                    String lasts = s.substring(index+clazzValue.length(),s.length());
                                    tmp = first+randomAttribute+lasts;
                                }
                            }
                        }

                    }else if(ModeHelper.isMethodRule(s)){  //6.判断是否是方法
                        int indexDH = s.indexOf(ModeRule.DH);
                        int indexD = s.indexOf(ModeRule.D);
                        String last = s.substring(indexDH,s.length());//得到=号后面需要拼接的字符串
                        String methodValue = s.substring(indexD+1,indexDH).trim();//截取得到方法名,下标+1，主要是去掉"."
                            //生成随机4-6位数方法名
                            String randomMethodValue = ModeHelper.getRandomString(ModeHelper.getRandomInt(4,6));
                            if(s.indexOf(clazzValue)>0){ //说明方法前面还有内容
                                String fristStr = s.substring(0,s.indexOf(clazzValue));
                                if(ModeHelper.isMethodKey(methodValue)){ //判断是否属于关键方法，是就不去做修改
                                    tmp = fristStr+randomAttribute+"."+methodValue+last;
                                }else{
                                    tmp = fristStr+randomAttribute+"."+randomMethodValue+last;
                                    Log.appendInfo("类变量名："+randomAttribute+"^^方法名："+methodValue+">>>修改为："+randomAttribute+"."+randomMethodValue);
                                    map.put(methodValue,randomMethodValue);//保存方法和修改之后的方法至集合里面
                                }
                            }else{
                                if(ModeHelper.isMethodKey(methodValue)){ //关键字
                                    tmp = randomAttribute+"."+methodValue+last;
                                }else{
                                    tmp = randomAttribute+"."+randomMethodValue+last;
                                    Log.appendInfo("类变量名："+randomAttribute+"^^方法名："+methodValue+">>>修改为："+randomAttribute+"."+randomMethodValue);
                                    map.put(methodValue,randomMethodValue);//保存方法和修改之后的方法至集合里面
                                }
                            }
                    }else if(ModeHelper.isClazzEnd(s)){ //7.判断是否是类的结尾
//                        clazzMap.put(randomAttribute,methodMap);
//                        map.put(clazzName,clazzMap);
//                        clazzMap=null;//注销
//                        methodMap=null;//注销
                    }
                    //保存修改后的值
                    saveFile(tmp,w);
                }else{
                    //直接保存，不做任何修改
                    saveFile(tmp,w);
                }
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

    /**
     * 修改调用方法名的地方
     * @param appointClazzz 指定类开始查找
     */
    private static void reviseQuoteMethod(String appointClazzz) {
        BufferedReader br = null;
        FileOutputStream outputStream = null;
        OutputStreamWriter streamWriter = null;
        BufferedWriter w = null;
        try{
            if(!isExists(tmpFile)){
                Log.appendErr("修改调用方法名的文件不存在！");
                return;
            }
            upFile = new File(parentPath+upFileName);
            createUpFile(upFile);
            br = new BufferedReader(new FileReader(tmpFile));//构造一个BufferedReader类来读取文件
            outputStream = new FileOutputStream(upFile);
            streamWriter = new OutputStreamWriter(outputStream,ModeRule.encoding);
            w = new BufferedWriter(streamWriter);
            String s=null,tmpStr = null;
            while((s = br.readLine())!=null) { //使用readLine方法，一次读一行
                tmpStr = s;
                if(ModeHelper.isStartQuote(s,appointClazzz)){ //判断是否从指定类可以开始查找
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        String method = entry.getKey();//得到方法名
                        String newMethod = entry.getValue();//得到修改后的方法名
                        if(ModeHelper.isQuoteMethod(tmpStr,method)){ //判断是否有调用方法代码
                            int index = tmpStr.indexOf(method); //截取方法出现的下标
                            if(index>0){
                                //判断前后
                                String frist;
                                char methodLast= tmpStr.charAt(index+method.length());
                                try{
                                    frist  = tmpStr.charAt(index-1)+"";
                                }catch (StringIndexOutOfBoundsException e){
                                     frist = "";//说明方法前面没有字符了.
                                }
                                if((ModeRule.D.equals(frist)||"".equals(frist))&&!ModeHelper.isLetterNumber(methodLast)){   //判断方法前面是不是""或者前面是不是"."
                                    String fr = tmpStr.substring(0,index);//得到前部分
                                    String last = tmpStr.substring(index+method.length(),tmpStr.length());//得到后部分
                                    tmpStr = fr+newMethod+last;//拼接
                                }else{
                                    //判断后面是否还有第二次出现
                                    int index2 = tmpStr.indexOf(method,index+method.length());
                                    if(index2>0){
                                        methodLast= tmpStr.charAt(index2+method.length());
                                        try{
                                            frist  = tmpStr.charAt(index2-1)+"";
                                        }catch (StringIndexOutOfBoundsException e){
                                            frist = "";//说明方法前面没有字符了.
                                        }
                                        boolean letter = ModeHelper.isLetterNumber(methodLast);
                                        if((ModeRule.D.equals(frist)||"".equals(frist))&&letter==false){   //判断方法前面是不是""或者前面是不是"."
                                            String fr = tmpStr.substring(0,index2);//得到前部分
                                            String last = tmpStr.substring(index2+method.length(),tmpStr.length());//得到后部分
                                            tmpStr = fr+newMethod+last;//拼接
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //保存
                saveFile(tmpStr,w);
            }
        }catch (Exception ex){
            ex.printStackTrace();
            Log.appendErr("修改方法名调用时异常信息："+ex.getMessage());
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


    /**
     * 插入无用的代码
     * @param clazz 从指定类开始插入
     */
    private static void insertDeadCode(String clazz) {
        newFile = new File(parentPath+newFileName);
        if(newFile.exists())newFile.delete();
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = null;
        FileOutputStream outputStream = null;
        OutputStreamWriter streamWriter = null;
        BufferedWriter w = null;
        try{
            //2.获取文件流
            br = new BufferedReader(new FileReader(upFile));//构造一个BufferedReader类来读取文件
            outputStream = new FileOutputStream(newFile);
            streamWriter = new OutputStreamWriter(outputStream,ModeRule.encoding);
            w = new BufferedWriter(streamWriter);

            String line = null;
            boolean isInsert=false;//标记是否可以插入无用代码
            boolean isRunMethod = false;//是否运行遇到方法
            String clazzValue=null;//当前类的类变量名
            String upLine =null;//上一行
            while((line = br.readLine())!=null){ //使用readLine方法，一次读一行
                if(ModeHelper.isStartInsertCode(line,clazz)){  //判断是否可以从指定类插入无用代码
                    for (Map.Entry<String, String> entry : clazzMap.entrySet()) {
                        if(ModeHelper.isClazzStart(line)){ //判断是否是类的开始
                            String clazzN = ModeHelper.getClazzName(line);//得到类名
                            if(clazzN.equals(entry.getKey())){ //如果类名相等，开始执行插入代码
                                clazzValue = entry.getValue();//得到修改后的类变量名
                            }
                        }
                    }
                    //规则：加入无用的方法
                    if(ModeHelper.isMethodRule(line)){ //true:表示遇到方法
                        isRunMethod=true;
                    }else{
                        isRunMethod=false;
                    }
                    if(isRunMethod){
                        //保存上一行字符串到文件中
                        saveFile(upLine,w);
                        //得到无用的方法
                        String noCode = ModeHelper.getRandomNoCode(clazzValue);
                        //插入无用方法
                        saveFile(noCode,w);
                    }else{
                        if(upLine!=null){
                            saveFile(upLine,w);
                        }
                    }

                    //规则：在 if(***){ 后面加入无用随机代码
                    if(isInsert){
                        //插入2条无用代码
                        saveFile(ModeHelper.getRandomCode(2),w);
                        //标志设置为false;
                        isInsert=false;
                    }
                    if(ModeHelper.isInsertCode(line)){ //true:表示遇到if 判断了，标记为true.读取下一行的时候插入无用代码
                        isInsert=true;
                    }
                    upLine = line;//当前读取的一行变为上一行
                }else{
                    //直接保存
                    saveFile(line,w);
                }
            }
            //保存最后一行
            saveFile(upLine,w);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("读取文件流异常："+e.getMessage());
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

    /**
     * 保存修改后的字符串到文件中去
     * @param str
     */
    private static void saveFile(String str, BufferedWriter writer) {
        if(str==null){return;}
        try {
            writer.write(str+System.lineSeparator());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除多余的文件
     */
    private static void deleteFile() {
        try{
            if(isExists(upFile)){
                upFile.delete();
            }
            if(isExists(tmpFile)){
                tmpFile.delete();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * 清除
     */
    public static void clear(){
        tmpFile=null;upFile=null;newFile = null;
        parentPath="";map.clear();
        ModeHelper.clear();
    }


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
     * 复制文件
     * @param copyPath 要复制文件的地址
     * @param newPath 复制之后的文件地址
     */
    public static void copyFile(String copyPath,File newPath) {
        InputStream inStream = null;
        FileOutputStream fs = null;
        try {
            int byteread = 0;
            File oldfile = new File(copyPath);
            createUpFile(newPath);//创建修改后的文件
            if (oldfile.exists()) {
                inStream = new FileInputStream(copyPath);
                fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
            }else{
                System.out.println("要复制的文件不存在！");
            }
        } catch (IOException e) {
           e.printStackTrace();
        }finally {
            try {
                if(inStream!=null) inStream.close();
                if(fs!=null) fs.close();
            } catch (IOException e) {
                    e.printStackTrace();
            }
        }
    }

    /**
     * 创建要修改的文件，源文件不做修改
     * @param file
     */
    private static void createUpFile(File file){
        try {
//            File file = new File(newPath);
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
