/**
 *
 */
package com.zhkj.ui;

import com.zhkj.log.Log;
import com.zhkj.project.ProjectDemo;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import confusion.ui.com.lib.up.tool.FileUtils;

/**
 * @author
 * @date 	 2018年2月6日
 * @version 1.0
 */
public class UITools extends JFrame{

    private static final long serialVersionUID = 1L;
    static JFrame f = null;
    //界面运行方法
    public static void run(final int width,final int height){
        if(f == null)f = new UITools(width, height);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Log.appendErr("开始绘制界面...");
                Toolkit kit=Toolkit.getDefaultToolkit();
                f.setTitle("混淆游戏工具 (V1.0)");
                int w = (kit.getScreenSize().width - width) / 2;
                int h = (kit.getScreenSize().height - height) / 2;
                f.setLocation(w, h);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(width, height);
                f.setVisible(true);
                f.setResizable(false);
            }
        });
    }

    public UITools(int width, int height){
        //设置整体布局模式(这里使用的绝对定位)
        setLayout(null);
        //设置一个容器
        final Container container=getContentPane();
        Log.appendErr("开始绘制提示框...");
        JLabel hintl=new JLabel("提示框 : ");
        hintl.setBounds(10, 50, 100, 20);
        add(hintl);
        Log.jtp = new JTextPane();
        Log.jtp.setVisible(true);
        Log.jtp.setEditable(false);
        Log.jtp.setFocusable(false);
        add(Log.jtp);
        JScrollPane hintjp=new JScrollPane(Log.jtp);
        hintjp.setBounds(80, 50, width - 100, 100);
        add(hintjp);

        //提示
        JLabel homeLabel=new JLabel("项目名称 : ");
        homeLabel.setBounds(10, 10, 100, 20);
        add(homeLabel);
        //选择项目
        JButton homeBtn=new JButton("更新");
        homeBtn.setFocusable(false);
        homeBtn.setBounds(width - 150, 10, 115, 30);
        add(homeBtn);
        //选择项目下拉选
        final JComboBox<String> homeComboBox=new JComboBox<String>();
        homeComboBox.setFocusable(false);
        homeComboBox.setBounds(80, 10, width - 260, 30);
        add(homeComboBox);
        Log.appendErr("提示框绘制完成...");
        homeComboBox.addItemListener(new ItemListener(){
            public JScrollPane jsA = null;
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    if(jsA!=null)container.remove(jsA);
                    Object text=homeComboBox.getSelectedItem();
                    if(text != null){
                        Log.appendErr("查找界面配置文件...");
                        final JScrollPane jsA= ProjectDemo.proDemo("config/project/"+text);
                        container.add(jsA);
                        this.jsA = jsA;
                        container.revalidate();

                        //监听
                        Toolkit tk = Toolkit.getDefaultToolkit();
                        tk.addAWTEventListener(new AWTEventListener(){
                            @Override
                            public void eventDispatched(AWTEvent event) {
                                if (event.getClass() == KeyEvent.class) {
                                    // 被处理的事件是键盘事件.
                                    KeyEvent keyEvent = (KeyEvent) event;
                                    if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
                                        //按下时你要做的事情
                                        keyPressed(keyEvent);
                                    } else if (keyEvent.getID() == KeyEvent.KEY_RELEASED) {
                                        //放开时你要做的事情
                                        keyReleased(keyEvent);
                                    }
                                }
                            }
                            private void keyPressed(KeyEvent event) {

                            }
                            //松开时响应
                            private void keyReleased(KeyEvent event) {
                                //监听tab	按键
                                if(event.getKeyCode()==9){
                                    JScrollBar sBar = jsA.getVerticalScrollBar();
                                    Component  com=getFocusOwner();
                                    if(com.getY()>300){
                                        sBar.setValue(com.getY()-300);
                                    }
                                }
                            }
                        }, AWTEvent.KEY_EVENT_MASK);
                    }
                }
            }
        });

        homeComboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                File file=new File("config/project/");
                if(file.exists()){
                    File[] fs=file.listFiles();
                    for(int j = 0; j < homeComboBox.getItemCount(); j++){
                        boolean b = true;
                        String proName=homeComboBox.getItemAt(j);
                        for (int i = 0; i < fs.length; i++) {
                            if(proName.equals(fs[i].getName())){
                                b = false;break;
                            }
                        }
                        if(b){
                            //homeComboBox里面有    fs里面没有
                            homeComboBox.removeItem(proName);
                        }
                    }
                    for (int i = 0; i < fs.length; i++) {
                        String proName=fs[i].getName();
                        boolean b = true;
                        for(int j = 0; j < homeComboBox.getItemCount(); j++){
                            if(proName.equals(homeComboBox.getItemAt(j))){
                                b = false;break;
                            }
                        }
                        if(b){
                            //fs里面有    homeComboBox里面没有
                            homeComboBox.addItem(proName);
                        }
                    }
                }
            }
        });

        Component xx=homeComboBox.getComponent(0);
        xx.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                File file=new File("config/project/");
                if(file.exists()){
                    File[] fs=file.listFiles();
                    for(int j = 0; j < homeComboBox.getItemCount(); j++){
                        boolean b = true;
                        String proName=homeComboBox.getItemAt(j);
                        for (int i = 0; i < fs.length; i++) {
                            if(proName.equals(fs[i].getName())){
                                b = false;break;
                            }
                        }
                        if(b){
                            //homeComboBox里面有    fs里面没有
                            homeComboBox.removeItem(proName);
                        }
                    }
                    for (int i = 0; i < fs.length; i++) {
                        String proName=fs[i].getName();
                        boolean b = true;
                        for(int j = 0; j < homeComboBox.getItemCount(); j++){
                            if(proName.equals(homeComboBox.getItemAt(j))){
                                b = false;break;
                            }
                        }
                        if(b){
                            //fs里面有    homeComboBox里面没有
                            homeComboBox.addItem(proName);
                        }
                    }
                }
            }
        });

        //更新按钮,选择项目
        homeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeComboBox.removeAllItems();
                File file=new File("config/project/");
                if(file.exists()){
                    File[] fs=file.listFiles();
                    for (int i = 0; i < fs.length; i++) {
                        String proName=fs[i].getName();
                        homeComboBox.addItem(proName);
                    }
                    FileUtils.clear();
                }
            }
        });
    }

    public static void main(String[] args) {
        run(600,800);
    }
}
