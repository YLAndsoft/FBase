package com.zhkj.tools;

/**
 * @author: FYL
 * @time: 2018/11/13
 * @email:347933430@qq.com
 * @describe: confusion.ui.com.lib.up
 */
public class ModeRule {
    public static final String DH = "="; //等号
    public static final String KEY = "function"; //方法关键字
    public static final String ZKH = "("; //正括号
    public static final String FKH = ")"; //反括号
    public static final String ZDKH = "{"; //正大括号
    public static final String FDKH = "}"; //反大括号
    public static final String FH = ";"; //反大括号
    public static final String D = "."; //DIAN
    public static final String METHOD_KEY = "prototype"; //方法头的部分规则
    public static final String KEY2 = "_super"; //开始修改的地方规则
    public static final String VAR = "var"; //方法头的部分规则
    public static final String IF = "if";     //匹配插入无用代码规则


    //引用界面控件关键方法
    //,"onDestroy","onUpdate","destroy","addHeroSpeed","addHpPorp"
    public static final String[] methodKey = {"createChildren","init","destroy","onUpdate","onDestroy","createProp"};

    public static String encoding = "UTF-8";

    public static String [] special = {"GameScene"}; //特殊类，不做方法混淆


}
