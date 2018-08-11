package m.b.bean;

/**
 * Created by DN on 2018/7/9.
 */

public class EventBusEvent<T> {
    private int code;
    private T data;
    private int position;

    public EventBusEvent(int code) {
        this.code = code;
    }

    public EventBusEvent(int code, T data) {
        this.code = code;
        this.data = data;
    }
    public EventBusEvent(int code, int position, T data) {
        this.code = code;
        this.position = position;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
