package cn.edu.zjvtit.empdemo.vo;

/**
 * Created by zeng on 2016-10-30.
 */

/**
 * 查询参数
 */
public class QueryParameter {
    /**
     * 当前分页
     */
    int number;
    /**
     * 分页大小
     */
    int size;
    /**
     * 搜索关键字
     */
    String keyValue;

    /**
     *
     * 获取查询关键字
     * @return 查询关键字
     */
    public String getKeyValue() {
        return keyValue;
    }

    /**
     * 设置查询关键字
     * @param keyValue  查询关键字
     */
    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    /**
     * 查询参数构造函数
     */
    public QueryParameter() {
    }

    /**
     * 查询参数构造函数
     * @param number 当前分页值
     * @param size 分页大小
     */
    public QueryParameter(int number, int size,String keyValue) {
        this.number = number;
        this.keyValue = keyValue;
        this.size = size;
    }

    /**
     * 获取当前分页值
     * @return 当前分页值
     */
    public int getNumber() {
        return number;
    }

    /**
     * 设置当前分页值
     * @param number 当前分页值
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * 获取分页大小
     * @return 分页大小
     */
    public int getSize() {
        return size;
    }

    /**
     * 设置分页大小
     * @param size 分页大小
     */
    public void setSize(int size) {
        this.size = size;
    }
}
