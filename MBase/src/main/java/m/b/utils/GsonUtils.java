package m.b.utils;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author: FYL
 * @time: 2018/9/3
 * @email:347933430@qq.com
 * @describe: GSON解析相关
 */
public class GsonUtils {

    private GsonUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * String转gson
     * @param data
     * @param mClass
     * @param <T>
     * @return T
     */
    public static <T> T getGsonObject(String data, Class<T> mClass) {
        try{
            T result = new Gson().fromJson(data, mClass);
            return result;
        }catch (Exception ex){
            return null;
        }
    }

    /**
     * String转gson
     * @param data
     * @param klass
     * @param <T>
     * @return List<T>
     */
    public static <T> List<T> getGsonList(String data, Class<T> klass) {
        try{
            return new Gson().fromJson(data, new ListOfSomething<T>(klass));
        }catch (Exception ex){
            return null;
        }
    }

    public static class ListOfSomething<X> implements ParameterizedType {
        private Class<?> wrapped;
        public ListOfSomething(Class<X> wrapped) {
            this.wrapped = wrapped;
        }
        public Type[] getActualTypeArguments() {
            return new Type[] {wrapped};
        }
        public Type getRawType() {
            return List.class;
        }
        public Type getOwnerType() {
            return null;
        }
    }

}
