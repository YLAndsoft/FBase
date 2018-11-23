/**
 *
 */
package confusion.ui.com.lib.up.project;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import confusion.ui.com.lib.up.log.Log;
import confusion.ui.com.lib.up.project.AddModule;
import confusion.ui.com.lib.up.tool.ModeRule;
import confusion.ui.com.lib.up.tool.ParseModule;


/**
 * @author WangLei
 * @date 	 2018年2月8日
 * @version 1.0
 */
public class ReadFileAddModule {
    public static Map<String,JComponent> maps=new HashMap<String,JComponent>();

    /**
     * 读取文件 加载控件
     * @param path
     * @param proAA
     */
    public int readFile(String path,JPanel proAA){
        File file=new File(path);
        if(!file.exists()){
            Log.appendErr("界面配置文件不存在！");
        }else{
            Log.appendErr("界面配置文件存在！");
        }
        ReadFileAddModule.maps.clear();
        try {
            //解决读取乱码问题
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), ModeRule.encoding);
            BufferedReader br=new BufferedReader(isr);
            String str=null;
            while((str=br.readLine())!=null){
                Log.appendErr("info："+str);
                //标题
                if(str.contains("标题")){
                    String[] arr=str.split("\\|");
                    if(arr[1].equals("标题")){
                        if(arr[3].equals("1")){
                            proAA.add(AddModule.addTitle(arr[2],1));
                        }
                        if(arr[3].equals("2")){
                            proAA.add(AddModule.addTitle(arr[2],2));
                        }
                    }
                }
                //标题和输入框
                if(str.contains("标题和输入框")){
                    String[] arr=str.split("\\|");
                    if(arr[1].equals("标题和输入框")){
                        List<Object> list= AddModule.addHintAndInput(arr[2], 2);
                        JTextField hintAndInput=new ParseModule().parseHintAndInput(list, proAA);
                        String key=arr[0];
                        if(hintAndInput!=null){
                            maps.put(key, hintAndInput);
                        }
                    }
                }
                //随机数
                if(str.contains("生成随机数")){
                    String[] arr=str.split("\\|");
                    if(arr[1].equals("生成随机数")){
                        List<Object> list= AddModule.addHintAndInputAndBtn(arr[2],arr[3]);
                        JTextField useFile=new ParseModule().parseHintAndBtnAndInput(list, proAA);
                        String key=arr[0];
                        if(useFile!=null){
                            maps.put(key, useFile);
                        }
                    }
                }
                //选择文件
                if(str.contains("选择文件")){
                    String[] arr=str.split("\\|");
                    if(arr[1].equals("选择文件")){
                        List<Object> list= AddModule.addHintAndFileAndInput(arr[2],2,arr[3]);
                        JTextField useFile=new ParseModule().parseHintAndFileAndInput(list, proAA);
                        String key=arr[0];
                        if(useFile!=null){
                            maps.put(key, useFile);
                        }
                    }
                }
                //选择文件框
                if(str.contains("选择文件夹")){
                    String[] arr=str.split("\\|");
                    if(arr[1].equals("选择文件夹")){
                        List<Object> list3= AddModule.addHintAndFloderAndInput(arr[2], 2, arr[3]);
                        JTextField useDirectory=new ParseModule().parseHintAndFloderAndInput(list3, proAA);
                        String key=arr[0];
                        if(useDirectory!=null){
                            maps.put(key, useDirectory);
                        }

                    }
                }
                //获取时间的
                if(str.contains("获取时间")){
                    String[] arr=str.split("\\|");
                    if(arr[1].equals("获取时间")){
                        List<Object> list5= AddModule.addHintAndTimeAndInput(arr[2],arr[3],2);
                        JTextField getTime=new ParseModule().parseHintAndTimeAndInput(list5,proAA);
                        String key=arr[0];
                        if(getTime!=null){
                            maps.put(key, getTime);
                        }

                    }

                }
                //隐藏参数
                if(str.contains("隐藏参数")){
                    String[] arr=str.split("\\|");
                    if(arr[1].equals("隐藏参数")){
                        String key=arr[0];
                        JTextField s = new JTextField();
                        s.setText(arr[2]);
                        maps.put(key, s);
                    }

                }
                //下拉选的
                if(str.contains("JComboBox下拉选")){
                    String[] arr=str.split("\\|");
                    List<String> valList=new ArrayList<String>();
                    for (int i = 3; i < arr.length; i++) {
                        valList.add(arr[i]);
                    }
                    if(arr[1].equals("JComboBox下拉选")){
                        List<Object> list= AddModule.addHintAndJComboBox(arr[2], valList);
                        JComboBox<String> jComboBox=new ParseModule().parseHintAndJComboBox(list, proAA);
                        String key=arr[0];
                        if(jComboBox!=null){
                            ReadFileAddModule.maps.put(key, jComboBox);
                        }
                    }
                }
                //checkBox 点击生成文件
                if(str.contains("checkBox")){
                    String[] arr=str.split("\\|");
                    if(arr[1].equals("checkBox")){
                        Map<String,JComponent> map= AddModule.addHintAndCheckBoxAndBtnAndInput(arr[2],arr[3],arr[4], 2);
                        JTextField checkBox=new ParseModule().parseHintAndCheckBoxAndBtnAndInput(proAA,map);
                        String key=arr[0];
                        if(checkBox!=null){
                            maps.put(key, checkBox);
                        }

                    }

                }
                //按钮
                if(str.contains("按钮")){
                    String[] arr=str.split("\\|");
                    if(arr[1].equals("混淆")){
                        JButton btn= AddModule.addUnpackBtn(arr[3]);
                        new ParseModule().parseUnpackBtn(btn, proAA);
                    }
                }
                //大文本框
                if(str.contains("文本框")){
                    String[] arr=str.split("\\|");
                    if(arr[1].equals("文本")){
                        JTextArea txt= AddModule.addBigTextInput();
                        String key=arr[0];
                        txt.setEditable(false);
                        txt.setBackground(new Color(238, 238, 238));
                        proAA.add(txt);
                        maps.put(key, txt);
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AddModule.moduleAdress.getY()+125;

    }

    /**
     * 获取值
     * @param key
     * @return
     */
    public static String getValue(String key){
        if(ReadFileAddModule.maps.get(key) != null){
            JComponent jcomponent = ReadFileAddModule.maps.get(key);
            if(jcomponent instanceof JTextComponent){
                return ((JTextComponent)jcomponent).getText();
            }else {
                return (String)((JComboBox<?>)jcomponent).getSelectedItem();
            }
        }
        System.out.println(key+",UiIsNull");
        return null;
    }
    /**
     * 获取目录
     * @param key
     * @return
     */
    public static String getDirectory(String key) {
        if(ReadFileAddModule.maps.get(key) != null){
            JComponent jcomponent = ReadFileAddModule.maps.get(key);
            if(jcomponent instanceof JTextComponent){
                return ((JTextComponent)jcomponent).getText()+File.separator;
            }else{
                return (String)((JComboBox<?>)jcomponent).getSelectedItem()+File.separator;
            }
        }
        System.out.println(key+",isNullDirectory");
        return null;
    }

    /**
     * 设置值
     * @param key
     * @return
     */
    public static void setText(String key,String text) {
        if(ReadFileAddModule.maps.get(key) != null){
            JComponent jcomponent = ReadFileAddModule.maps.get(key);
            if(jcomponent instanceof JTextComponent){
                ((JTextComponent)jcomponent).setText(text);
            }else{
                Log.appendErr(key+"isNotJTextComponent");
            }
        }
        else
            System.out.println(key+",isNullDirectory");
    }

}
