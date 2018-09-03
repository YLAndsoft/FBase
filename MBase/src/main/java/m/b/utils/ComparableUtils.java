package m.b.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * @author: FYL
 * @time: 2018/9/3
 * @email:347933430@qq.com
 * @describe: 排序相关
 */
public class ComparableUtils {
    private ComparableUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }
    /**
     * 对Integer排序来显示-0:降序 1:升序
     * @param integers
     */
    public static  void sortIntegerList(List<Integer> integers, final int type) {
        Comparator<Integer> itemComparator = new Comparator<Integer>() {
            public int compare(Integer integer1, Integer integer2){
                return type==0?integer2.compareTo(integer1):integer1.compareTo(integer2);
            }
        };
        Collections.sort(integers);
    }
    /**
     * 对String排序来显示-0:降序 1:升序
     * @param strs
     */
    public static  void sortStringList(List<String> strs, final int type) {
        Comparator<String> itemComparator = new Comparator<String>() {
            public int compare(String str1, String str2){
                return type==0?str2.compareTo(str1):str1.compareTo(str2);
            }
        };
        Collections.sort(strs);
    }


}
