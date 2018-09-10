package m.b.base;

import android.support.annotation.NonNull;

/**
 * @author: FYL
 * @time: 2018/9/3
 * @email:347933430@qq.com
 * @describe: base相关
 */
public abstract class BaseComparable implements Comparable{

    /**
     * 排序抽象类
     * @param o1 即将比较的实体类，也可以是属性
     * @return  负数：意味着“o1比o2小”
     *           零：意味着“o1等于o2”
     *           正数:意味着“o1大于o2”
     */
    public abstract int compare(Object o1);
    @Override
    public int compareTo(@NonNull Object o) {
        try {
            return compare(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 例子
     */
   /*
    public class Age extends BaseComparable{
        int type = 1;
        int age;
        String time;

        public Age(int age,String time){
            this.age = age;
            this.time=time;
        }
        @Override
        public int compare(Object o) {
            Age o1 = (Age)o;
            int o2 = this.age;
            if(type==0){
                return o2-o1.age;
            }else {
                //时间比较
                Date data1 = TimeUtils.stringToDate(o1.time,"yyyy-MM-dd HH:mm:ss");
                Date data2 = TimeUtils.stringToDate(this.time,"yyyy-MM-dd HH:mm:ss");
                return data1.compareTo(data2);
            }
        }
    }

    public void main(String[] args){
        List<Age> list = new ArrayList<>();
        for(int i = 1;i<10;i++){
            list.add(new Age(i,"2018-08-0"+i+" 12:12:00"));
        }
        //需要排序的地方调用这行代码即可
        Collections.sort(list);
        for(Age age:list){
            System.out.println("结果："+age.age+"=="+age.time);
        }



    }
*/
}
