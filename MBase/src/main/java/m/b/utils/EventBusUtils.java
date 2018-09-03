package m.b.utils;

import org.greenrobot.eventbus.EventBus;
import m.b.bean.EventBusEvent;
/**
 * Created by DN on 2018/7/9.
 */
/**
 * @author: FYL
 * @time: 2018/9/3
 * @email:347933430@qq.com
 * @describe: EventBus广播相关
 */
public class EventBusUtils {
    /**
     * 注册
     * @param subscriber
     */
    public static void register(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber);
        }
    }

    /**
     * 取消注册
     * @param subscriber
     */
    public static void unregister(Object subscriber) {
        if(EventBus.getDefault().isRegistered(subscriber)){
            EventBus.getDefault().unregister(subscriber);
        }
    }


    /**
     * 发送消息
     * @param event
     */
    public static void sendEvent(EventBusEvent event) {
        EventBus.getDefault().post(event);
    }
    /**
     * 发送消息
     * @param event
     */
    public static void sendStickyEvent(EventBusEvent event) {
        EventBus.getDefault().postSticky(event);
    }


}
