package confusion.ui.com.lib.up.tool;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomStringUtril {
	private static Map<String, String> autoMap = new HashMap<String, String>();
	public static void clear(){
		autoMap.clear();
	}
	public static Random random = new Random();
	/**
	 * 生成小写字母
	 * @param length
	 * @return
	 */
	public static String randomLowSting(int length){
		return randomCharSting(length,97,26);

	}
	/**
	 * 生成大写字母
	 * @param length
	 * @return
	 */
	public static String randomUpperSting(int length){
		return randomCharSting(length,65,26);
	}
	/**
	 * 生成某一个区段的字符串
	 * @param length			字符串长度
	 * @param start				开始点
	 * @param choiceLength		选择范围
	 * @return
	 */
	public static String randomCharSting(int length, int start, int choiceLength){
		StringBuffer stringBf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			stringBf.append((char) (start + random.nextInt(choiceLength)));
		}
		return stringBf.toString();
	}
	/**
	 * 生产任意字符串
	 * @param length
	 * @return
	 */
	public static String randomUpperLowLetter(int length){
		StringBuffer stringBf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			if(random.nextInt()%2 == 0)
				stringBf.append((char) (65 + random.nextInt(26)));
			else{
				stringBf.append((char) (97 + random.nextInt(26)));
			}
		}
		return stringBf.toString();
	}
	/**
	 * 按规则生成字符串
	 * @param lengthA
	 * @param lengthB
	 * @return
	 */
	public static String randomStingToAbc(int lengthA, int lengthB){
		StringBuffer stringBf = new StringBuffer();
		String[] stringA = new String[]{"a","b","c","d","e","f","g","h","i","j"};
		String[] stringB = new String[]{"k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		for (int i = 0; i < lengthA; i++) {
			int index = random.nextInt(stringA.length);
			stringBf.append(stringA[index]);
		}
		for (int i = 0; i < lengthB; i++) {
			int index = random.nextInt(stringB.length);
			stringBf.append(stringB[index]);
		}
		return toUpperCase4Index(stringBf.toString(),0);
	}
	/**
	 * 随机选中一个字符串
	 * @param stringS
	 * @return
	 */
	public static String randomSting(String[] stringS){
		int index = random.nextInt(stringS.length);
		return stringS[index];
	}
	/**
	 * 生成首字母小写 其它 任意的字符串
	 * @return
	 */
	public static String getRandomTOabCd(int leng){
		int indexT = 3+random.nextInt(leng);
		return toUpperCase4Index(RandomStringUtril.randomLowSting(indexT),1+random.nextInt(indexT-1));
	}

	public static String getRandomAbc(int leng) {
		return toUpperCase4Index(randomLowSting(leng), 0);
	}
	/**
	 * 首字母大写
	 *
	 * @param string
	 * @return
	 */
	public static String toUpperCase4Index(String string,int index) {
		char[] methodName = string.toCharArray();
		methodName[index] = toUpperCase(methodName[index]);
		return String.valueOf(methodName);
	}

	/**
	 * 字符转成大写
	 *
	 * @param chars
	 * @return
	 */
	public static char toUpperCase(char chars) {
		if (97 <= chars && chars <= 122) {
			chars ^= 32;
		}
		return chars;
	}

	public static void main(String[] args) {
//		RandomStringUtril rsu = new RandomStringUtril();
//		System.out.println(rsu.randomStingToAbc(2, 3));
//
//		System.out.println(rsu.randomLowSting(1));
//		System.out.println(getPackage("com.ali"));
//		System.out.println(getPackage("com.ali"));
//		System.out.println(getPackage("com.ali1"));
//		System.out.println(pathS[0]);
		for (int i = 0; i < 30; i++) {
			System.out.println(getPackage("com.ab.cs"+i));
			System.out.println(autoImitateString("com"+i));
			System.out.println(autoImitateString("Zom"+i));
		}
	}
	//目录等级    目录数       参数0-1
	private static String[][][] pathS = new  String[20][20][2];
	public static String getPackage(String packagName) {
		String[] packagNameS = packagName.split("\\."); //  修改前  修改后pathS
		for (int j = 0; j < packagNameS.length; j++) {
			for (int i = 0; i < pathS[j].length; i++) {
				//com.ab.cs
				if(packagNameS[j].equals(pathS[j][i][0]) ){
					packagNameS[j] = pathS[j][i][1];
					break;
				}else if(null == pathS[j][i][0]){
					pathS[j][i][0] = packagNameS[j];
					pathS[j][i][1] = randomLowSting(random.nextInt(4) + 4);
					packagNameS[j] = pathS[j][i][1];
					break;
				}
			}
		}
		return addComma(packagNameS).toString();
	}
	public static String autoImitateString(String key){
		if(key==null)return null;
		if(autoMap.get(key) != null){return autoMap.get(key);}
		StringBuffer stringBf = new StringBuffer();
		int len = 3 + random.nextInt(key.length());
		if (Character.isUpperCase(key.charAt(0))) {
			stringBf.append(randomUpperSting(1)).append(randomLowSting(1));
		}else {
			stringBf.append(randomLowSting(2));
		}
		stringBf.append(randomUpperLowLetter(len));
		autoMap.put(key, stringBf.toString());
		return stringBf.toString();
	}

	public static String autoImitateStringToLowerCase(String key){
		if(key==null)return null;
		key = "lowstring" +key;
		if(autoMap.get(key) != null){return autoMap.get(key);}
		StringBuffer stringBf = new StringBuffer();
		int len = 3 + random.nextInt(key.length());
		stringBf.append(randomLowSting(len));
		autoMap.put(key, stringBf.toString());
		return stringBf.toString();
	}


	/**
	 * @param javaCodeB 会改变其值
	 * @param arr
	 * @return
	 */
	public static StringBuffer addComma(String[] arr){
		StringBuffer javaCodeB = new StringBuffer();
		for (int i = 0; i < arr.length-1; i++) {
			javaCodeB.append(arr[i]).append(".");
		}
		javaCodeB.append(arr[arr.length-1]);
		return javaCodeB;
	}

	public static boolean randomSpecie(){
		return random.nextInt()%2==0?true:false;
	}

}
