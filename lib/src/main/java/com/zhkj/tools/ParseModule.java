/**
 *
 */
package com.zhkj.tools;

import com.eltima.components.ui.DatePicker;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import confusion.ui.com.lib.up.exe.ExecuteDispose;


/**
 * @author WangLei
 * @date 	 2018年2月7日
 * @version 1.0
 */
public class ParseModule {

    /**
     * 1.解析 提示+输入框的方法
     */
    static JTextField txt;
    public JTextField parseHintAndInput(List<Object> list,JPanel  jpanel){
        for (int i = 0; i < list.size(); i++) {
            Object param = list.get(i);
            if(param instanceof JTextField){
                txt=((JTextField) param);
            }
            jpanel.add((Component) list.get(i));
        }
        return txt;

    }

    /**
     * 解析 提示+输入框+随机数的方法
     */
    JTextField text1=null;
    public JTextField parseHintAndBtnAndInput(List<Object> list,JPanel  jpanel){
        for (int i = 0; i < list.size(); i++) {
            Object param = list.get(i);
            if(param instanceof JTextField){
                text1=((JTextField) param);
            }
            if(param instanceof JButton){
                ((JButton) param).setFocusable(false);
                ((JButton) param).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        int num=5+(int)(Math.random()*5);
                        String num2= RandomStringUtril.randomLowSting(num);
                        text1.setText(num2);
                    }
                });
            }
            jpanel.add((Component) list.get(i));
        }
        return text1;
    }

    /**
     * 2.解析 提示 + 选择文件 + 输入框的方法
     */
    JTextField text=null;
    public JTextField parseHintAndFileAndInput(List<Object> list,JPanel  jpanel){
        for (int i = 0; i < list.size(); i++) {
            final Object param = list.get(i);
            if(param instanceof JTextField){
                text=((JTextField) param);
                ((JTextField) param).setEditable(false);
                ((JTextField) param).setFocusable(false);
            }
            if(param instanceof JButton){
                ((JButton) param).setFocusable(false);
                ((JButton) param).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        JFileChooser chooser = new JFileChooser();// 设置选择器
                        chooser.setMultiSelectionEnabled(true);// 设为多选
                        chooser.addChoosableFileFilter(new JAVAFileFilter(".js"));//导入可选择的文件的后缀名类型
                        int returnVal = chooser.showOpenDialog((JButton) param);
                        // 判断是否是打开按钮
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            String filepath = chooser.getSelectedFile().getAbsolutePath();// 获取绝对路径
                            text.setText(filepath);
                        }
                    }
                });
            }

            jpanel.add((Component) list.get(i));
        }
        return text;

    }

    /**
     *3. 解析 提示 + 选择文件夹 + 输入框的方法
     */
    JTextField text2=null;
    public JTextField parseHintAndFloderAndInput(List<Object> list,JPanel  jpanel){
        for (int i = 0; i < list.size(); i++) {
            final Object param = list.get(i);
            if(param instanceof JTextField){
                text2=((JTextField) param);
                ((JTextField) param).setEditable(false);
                ((JTextField) param).setFocusable(false);
            }
            if(param instanceof JButton){
                ((JButton) param).setFocusable(false);
                ((JButton) param).addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser chooser = new JFileChooser();// 设置选择器
                        chooser.setMultiSelectionEnabled(true);// 设为多选
                        chooser.setFileSelectionMode(1);// 设定只能选择到文件夹
                        int returnVal = chooser.showOpenDialog((JButton) param);
                        // 判断是否是打开按钮
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            String filepath = chooser.getSelectedFile().getAbsolutePath();// 获取绝对路径
                            text2.setText(filepath);
                        }
                    }
                });
            }
            jpanel.add((Component) list.get(i));
        }
        return text2;
    }

    /**
     * 4.解析 提示 + 选择时间控件 + 选择时间按钮 + 输入框的方法
     */
    JTextField text3=null;
    DatePicker dp=null;
    public JTextField parseHintAndTimeAndInput(List<Object> list,JPanel  jpanel){
        for (int i = 0; i < list.size(); i++) {
            Object param = list.get(i);
            if(param instanceof DatePicker){
                dp=(DatePicker) param;
            }
            if(param instanceof JTextField){
                text3=((JTextField) param);
                ((JTextField) param).setEditable(false);
                ((JTextField) param).setFocusable(false);
            }
            if(param instanceof JButton){
                ((JButton) param).setFocusable(false);
                ((JButton) param).addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String time=sdf.format(dp.getValue());
                        text3.setText(time);

                    }
                });
            }
            jpanel.add((Component) list.get(i));
        }
        return text3;
    }

    /**
     * 5.解析 一个打包按钮
     */
    public void parseUnpackBtn(final JButton btn, JPanel  jpanel){
        btn.setFocusable(false);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //点击打包监听
                ExecuteDispose.goOn();
            }
        });
        jpanel.add(btn);
    }

    /**
     * 6.解析 提示 + checkbox + 单击按钮 + 选择文件按钮 + input
     */
    public JTextField parseHintAndCheckBoxAndBtnAndInput(JPanel  jpanel,Map<String,JComponent> map){
        //提示
        JLabel hintLabel=(JLabel) map.get("hintLabel");
        jpanel.add(hintLabel);
        //文本框
        final JTextField input= (JTextField) map.get("input");
        input.setEditable(false);
        input.setFocusable(false);
        jpanel.add(input);
        //清空按钮
        JButton getAgainbtn=(JButton) map.get("getAgainbtn");
        getAgainbtn.setFocusable(false);
        getAgainbtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                input.setText(" ");
            }
        });
        jpanel.add(getAgainbtn);
        //获取文件按钮
        final JButton choseFilebtn=(JButton) map.get("choseFilebtn");
        getAgainbtn.setFocusable(false);
        choseFilebtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //选择文件
                JFileChooser chooser = new JFileChooser();// 设置选择器
                chooser.setMultiSelectionEnabled(true);// 设为多选
                int returnVal = chooser.showOpenDialog(choseFilebtn);
                // 判断是否是打开按钮
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String filepath = chooser.getSelectedFile().getAbsolutePath();// 获取绝对路径
                    input.setText(filepath);
                }
            }
        });
        jpanel.add(choseFilebtn);

        return input;
    }

    /**
     * 解析提示+ JComboBox下拉选
     */
    static JComboBox<String> txt5;
    @SuppressWarnings("unchecked")
    public JComboBox<String> parseHintAndJComboBox(List<Object> list,JPanel  jpanel){
        for (int i = 0; i < list.size(); i++) {
            Object param = list.get(i);
            if(param instanceof JComboBox){
                txt5=(JComboBox<String>) param;
                ((Component) param).setFocusable(false);
            }
            jpanel.add((Component) list.get(i));
        }
        return txt5;

    }

}
