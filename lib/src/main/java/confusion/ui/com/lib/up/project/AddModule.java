/**
 *
 */
package confusion.ui.com.lib.up.project;

import com.eltima.components.ui.DatePicker;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import confusion.ui.com.lib.up.entity.ModuleAdress;


/**
 * @author WangLei
 * @date 	 2018年2月6日
 * @version 1.0
 */
public class AddModule{

	static ModuleAdress moduleAdress=new ModuleAdress();

	/**
	 * 1.添加标题的方法
	 * title : 需要展现的名字
	 * 	flag : 1 是代表放在第一位  其他任意数字 代表不是第一位
	 */
	public static JLabel addTitle(String title,int flag){
		ModuleAdress moduleAdress1 =new ModuleAdress();
		if(flag==1){
			moduleAdress1.setY(25);
		}else{
			moduleAdress1.setY(moduleAdress.getY()+80);
		}
		JLabel titleLabel=new JLabel(title);
		titleLabel.setBounds(240, moduleAdress1.getY(), 200, 20);
		titleLabel.setFont(new java.awt.Font("Dialog",0,20));
		moduleAdress.setY(moduleAdress1.getY());
		return titleLabel;
	}
	/**
	 *8. 添加一个打包按钮
	 *name 需要展现的按钮名字
	 * moduleAdress 是一个对象 传入具体的坐标
	 */
	public static  JButton addUnpackBtn(String name){
		ModuleAdress moduleAdress8=new ModuleAdress();
		moduleAdress8.setY(moduleAdress.getY()+70);
		JButton btn=new JButton(name);
		btn.setBounds(195, moduleAdress8.getY(),150,70);
		moduleAdress.setY(moduleAdress8.getY());
		return btn;
	}

	/**
	 * 将所有值存入对象
	 * @return
	 */
	public static  JButton addSaveDataBtn(){
		ModuleAdress moduleAdress9=new ModuleAdress();
		JButton btn=new JButton("保存数据");
		moduleAdress9.setY(moduleAdress.getY()-40);
		btn.setBounds(120, moduleAdress9.getY(),100,30);
		moduleAdress.setY(moduleAdress9.getY());
		return btn;
	}
	/**
	 * 7.添加一个大的文字输入框
	 *  moduleAdress 是一个对象 传入具体的坐标
	 */
	public static JTextArea addBigTextInput(){
		ModuleAdress moduleAdress10=new ModuleAdress();
		moduleAdress10.setY(moduleAdress.getY());
		JTextArea area=new JTextArea();
		area.setFocusable(false);
		area.setBounds(10, moduleAdress10.getY(),300, 300);
		moduleAdress.setY(moduleAdress10.getY());
		return area;
	}
	/**
	 * 添加提示和普通按钮和输入框
	 * @param hintName
	 * @param btnName
	 * @return
	 */
	public static List<Object> addHintAndInputAndBtn(String hintName,String btnName){
		ModuleAdress moduleAdress3 =new ModuleAdress();
		List<Object> list=new ArrayList<>();
		moduleAdress3.setY(moduleAdress.getY()+45);
		JLabel hintLabel=new JLabel(hintName);
		hintLabel.setFont(new java.awt.Font("Dialog",0,15));
		hintLabel.setBounds(10, moduleAdress3.getY(), 300, 20);
		list.add(hintLabel);
		JButton btn=new JButton(btnName);
		btn.setBounds(410, moduleAdress3.getY(), 100, 30);
		list.add(btn);
		JTextField input=new JTextField(30);
		input.setBounds(10, moduleAdress3.getY()+40, 500, 30);
		list.add(input);
		moduleAdress.setY(moduleAdress3.getY()+40);
		return list;
	}
	/**
	 * 2.添加提示+输入框的方法
	 * hintName : 需要展示的提示语
	 * flag : 1 是代表放在第一位  其他任意数字代表不是第一位
	 */
	public static List<Object> addHintAndInput(String hintName,int flag){
		ModuleAdress moduleAdress2 =new ModuleAdress();
		List<Object> list=new ArrayList<>();
		if(flag==1){
			moduleAdress2.setY(25);
		}else{
			moduleAdress2.setY(moduleAdress.getY()+50);
		}
		JLabel hintLabel=new JLabel(hintName);
		hintLabel.setFont(new java.awt.Font("Dialog",0,15));
		hintLabel.setBounds(10, moduleAdress2.getY(), 500, 20);
		list.add(hintLabel);
		JTextField input=new JTextField(30);
		input.setBounds(10, moduleAdress2.getY()+30, 500, 30);
		list.add(input);
		moduleAdress.setY(moduleAdress2.getY()+30);
		return list;
	}

	/**
	 * 3.添加提示 + 选择文件 + 输入框的方法
	 * hintName : 需要展现的提示语
	 *  flag : 1 是代表放在第一位  其他任意数字代表不是第一位
	 *  btnName : 按钮的名字
	 */
	public static List<Object> addHintAndFileAndInput(String hintName,int flag,String btnName){
		ModuleAdress moduleAdress3 =new ModuleAdress();
		List<Object> list=new ArrayList<>();
		if(flag==1){
			moduleAdress3.setY(25);
		}else{
			moduleAdress3.setY(moduleAdress.getY()+45);
		}
		JLabel hintLabel=new JLabel(hintName);
		hintLabel.setFont(new java.awt.Font("Dialog",0,15));
		hintLabel.setBounds(10, moduleAdress3.getY(), 300, 20);
		list.add(hintLabel);
		JButton btn=new JButton(btnName);
		btn.setBounds(410, moduleAdress3.getY(), 100, 30);
		list.add(btn);
		JTextField input=new JTextField(30);
		input.setBounds(10, moduleAdress3.getY()+40, 500, 30);
		list.add(input);
		moduleAdress.setY(moduleAdress3.getY()+40);
		return list;

	}
	/**
	 * 4.添加提示 + 选择文件夹 + 输入框的方法
	 * hintName : 需要展示的提示语
	 * 	flag : 1 是代表放在第一位  其他任意数字代表不是第一位
	 * btnName : 需要展现的按钮的名字
	 */
	public static List<Object> addHintAndFloderAndInput(String hintName,int flag,String btnName){
		List<Object> list=new ArrayList<>();
		ModuleAdress moduleAdress4 =new ModuleAdress();
		if(flag==1){
			moduleAdress4.setY(25);
		}else{
			moduleAdress4.setY(moduleAdress.getY()+45);
		}
		JLabel hintLabel=new JLabel(hintName);
		hintLabel.setFont(new java.awt.Font("Dialog",0,15));
		hintLabel.setBounds(10, moduleAdress4.getY(), 300, 20);
		list.add(hintLabel);
		JButton btn=new JButton(btnName);
		btn.setBounds(410, moduleAdress4.getY(), 100, 30);
		list.add(btn);
		JTextField input=new JTextField(30);
		input.setBounds(10, moduleAdress4.getY()+40, 500, 30);
		list.add(input);
		moduleAdress.setY(moduleAdress4.getY()+40);
		return list;
	}

	/**
	 * 9. 添加一个提示 + checkbox + 单击按钮 + 选择文件按钮 + input
	 * hintName : 需要展示的提示语
	 *	checkName : checkbox 展示的名字
	 *createName : 按钮的名字
	 *	flag : 1 是代表放在第一位  其他任意数字代表不是第一位
	 */
	public static Map<String,JComponent> addHintAndCheckBoxAndBtnAndInput(String hintName,String getAgainName,
																		  String choseFileName,int flag){
		Map<String,JComponent> maps=new HashMap<String,JComponent>();
		ModuleAdress moduleAdress6=new ModuleAdress();
		if(flag==1){
			moduleAdress6.setY(25);
		}else{
			moduleAdress6.setY(moduleAdress.getY()+45);
		}
		JLabel hintLabel=new JLabel(hintName);
		hintLabel.setFont(new java.awt.Font("Dialog",0,15));
		hintLabel.setBounds(10, moduleAdress6.getY(), 300, 20);
		maps.put("hintLabel", hintLabel);
		JButton getAgainbtn=new JButton(getAgainName);
		getAgainbtn.setBounds(290, moduleAdress6.getY(), 100, 30);
		maps.put("getAgainbtn", getAgainbtn);
		JButton choseFilebtn=new JButton(choseFileName);
		choseFilebtn.setBounds(410, moduleAdress6.getY(), 100, 30);
		maps.put("choseFilebtn", choseFilebtn);
		JTextField input=new JTextField(30);
		input.setBounds(10, moduleAdress6.getY()+40, 500, 30);
		maps.put("input", input);
		moduleAdress.setY(moduleAdress6.getY()+40);
		return maps;
	}

	/**
	 *  添加一个 JComboBox<String> 下拉选的控件
	 * @param hintName  提示语
	 * @param valList     值组合
	 * @return
	 */
	public static List<Object> addHintAndJComboBox(String hintName,List<String> valList){
		List<Object> list=new ArrayList<>();
		ModuleAdress moduleAdress12=new ModuleAdress();
		moduleAdress12.setY(moduleAdress.getY()+45);
		//设置提示语
		JLabel hintLabel=new JLabel(hintName);
		hintLabel.setFont(new java.awt.Font("Dialog",0,15));
		hintLabel.setBounds(10, moduleAdress12.getY(), 300, 20);
		list.add(hintLabel);
		//设置JComboBox
		JComboBox<String> homeComboBox=new JComboBox<String>();
		homeComboBox.setBounds(10, moduleAdress12.getY()+30, 500, 30);
		for(String str:valList){
			homeComboBox.addItem(str);
		}
		list.add(homeComboBox);
		moduleAdress.setY(moduleAdress12.getY()+30);
		return list;
	}

	/**
	 * 5.添加提示 + 选择时间控件 + 选择时间按钮 + 输入框的方法
	 *  hintName : 需要展示的提示语
	 * 	flag : 1 是代表放在第一位  其他任意数字代表不是第一位
	 */
	public static List<Object> addHintAndTimeAndInput(String hintName,String btnName,int flag){
		List<Object> list=new ArrayList<>();
		ModuleAdress moduleAdress5 =new ModuleAdress();
		if(flag==1){
			moduleAdress5.setY(25);
		}else{
			moduleAdress5.setY(moduleAdress.getY()+45);
		}
		JLabel hintLabel=new JLabel(hintName);
		hintLabel.setFont(new java.awt.Font("Dialog",0,15));
		hintLabel.setBounds(10, moduleAdress5.getY(), 300, 20);
		list.add(hintLabel);
		DatePicker datepick=AddModule.getDatePicker();
		datepick.setBounds(240, moduleAdress5.getY(), 170, 30);
		list.add(datepick);
		JButton btn=new JButton(btnName);
		btn.setBounds(410, moduleAdress5.getY(), 100, 30);
		list.add(btn);
		JTextField input=new JTextField(30);
		input.setBounds(10, moduleAdress5.getY()+40, 500, 30);
		list.add(input);
		moduleAdress.setY(moduleAdress5.getY()+40);
		return list;

	}

	/**
	 * 一个时间控件 只在本类中使用
	 * @return
	 */
	private static DatePicker getDatePicker() {
		final DatePicker datepick;
		// 格式
		String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
		// 当前时间
		Date date = new Date();
		// 字体
		Font font = new Font("Times New Roman", Font.BOLD, 14);
		Dimension dimension = new Dimension(177, 24);
		datepick = new DatePicker(date, DefaultFormat, font, dimension);
		datepick.setLocation(260, 530);
		// 设置国家
		datepick.setLocale(Locale.CHINA);
		// 设置时钟面板可见
		datepick.setTimePanleVisible(true);
		return datepick;
	}

}
