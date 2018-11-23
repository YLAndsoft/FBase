package confusion.ui.com.lib.up.log;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;


public class Log {
	public static int logGrade = 0;
	public static JTextPane jtp;
	private static FileWriter writer = null;
	static{
		try {
			//打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			writer = new FileWriter("this.log", false);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
		}
		Runtime run=Runtime.getRuntime();//当前 Java 应用程序相关的运行时对象。
		run.addShutdownHook(new Thread(){ //注册新的虚拟机来关闭钩子
			@Override
			public void run() {
				//程序结束时进行的操作
				try {
					writer.write("\r\n********************************************************** ");
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}
	public static void appendErr(String string){
		try {
			setString(string,Color.red);
			writer.write("\r\nErr【 " + string);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void appendInfo(String string){
		try {
			setString(string,Color.black);
			writer.write("\r\nInfo【 " + string);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void appendTest(String... strings) {
		if(logGrade < 1)return;
		String string = "";
		for (int i = 0; i < strings.length; i++) {
			string += strings[i];
		}
		try {
			setString(string,Color.blue);
			writer.write("\r\nTest【 " + string);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static int len =0;
	public static void appendShow(String string) {
		setString(string,Color.green);
		len = string.length();
	}
	public static void setString(String string,Color color) {
		if(jtp != null){
			Style def = Log.jtp.getStyledDocument().addStyle(null, null);
			Style s = Log.jtp.addStyle("red", def);
			StyleConstants.setForeground(s, color);
			/*: "normal"*/
			try {
				if(len != 0){
					len += 2;
					jtp.getDocument().remove(jtp.getDocument().getLength() - len,len);
				}
				jtp.getDocument().insertString(
						jtp.getDocument().getLength(),string+"\r\n", jtp.getStyle("red"));
				jtp.setCaretPosition(jtp.getDocument().getLength());
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
			len = 0;
		}
	}
}
