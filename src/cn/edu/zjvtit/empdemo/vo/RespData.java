package cn.edu.zjvtit.empdemo.vo;

/**
 * 增删改等操作返回对象
 * Created by zeng on 2016-10-23.
 */
public class RespData {
    /**
     * 执行结果
     */
    boolean result;

    /**
     * 消息内容
     */
    String message;

    /**
     * 构造函数
     * @param result 执行结果
     * @param message 消息内容
     */
    public RespData(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    /**
     * 构造函数
     */
    public RespData() {
    }

    /**
     * 获取执行结果
     * @return 执行结果
     */
    public boolean isResult() {
        return result;
    }

    /**
     * 设置执行结果
     * @param result 执行结果
     */
    public void setResult(boolean result) {
        this.result = result;
    }

    /**
     * 获取执行结果
     * @return 消息内容
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置 执行结果
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
