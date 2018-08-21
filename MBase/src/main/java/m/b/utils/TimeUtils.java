package m.b.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DN on 2018/6/29.
 */

public class TimeUtils {

    /**
     * date类型转换为String类型
     * @param data Date类型的时间
     * @param formatType ormatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     * @return
     */
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }
    /**
     *long类型转换为String类型
     * @param currentTime 要转换的long类型的时间
     * @param formatType 要转换的string类型的时间格式
     * @return
     * @throws ParseException
     */
    public static String longToString(long currentTime, String formatType){
        Date date = null; // long类型转成Date类型
        try {
            date = longToDate(currentTime, formatType);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date==null){date = new Date();}
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    /**
     *date类型转换为long类型
     * @param date 要转换的date类型的时间
     * @return
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * String类型转换为long类型
     * strTime的时间格式和formatType的时间格式必须相同
     * @param strTime 要转换的String类型的时间
     * @param formatType 时间格式
     * @return
     * @throws ParseException
     */
    public static long stringToLong(String strTime, String formatType)throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }
    /**
     *long转换为Date类型
     * @param currentTime 要转换的long类型的时间
     * @param formatType 要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     * @return
     * @throws ParseException
     */
    public static Date longToDate(long currentTime, String formatType)throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    /**
     * string类型转换为date类型
     * strTime的时间格式必须要与formatType的时间格式相同
     * @param strTime 要转换的string类型的时间
     * @param formatType 要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String strTime, String formatType){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat(formatType);
            Date date = null;
            return formatter.parse(strTime);
        }catch ( ParseException pe){
            return new Date();
        }catch (Exception ex){
            return null;
        }
    }

}
