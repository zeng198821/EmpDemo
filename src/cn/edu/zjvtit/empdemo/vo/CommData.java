package cn.edu.zjvtit.empdemo.vo;

/**
 * 交互数据
 * Created by zeng on 2016-10-23.
 */
public class CommData {

    /**
     * 交互数据构造函数
     */
    public CommData() {

    }


    /**
     * 交互数据构造函数
     * @param serversName 服务名称
     * @param submitData 提交的数据
     */
    public CommData(String serversName, String submitData) {
        this.serversName = serversName;
        this.submitData = submitData;
    }

    /**
     * 服务名称
     */
    String serversName;

    /**
     * 获取服务名称
     * @return  服务名称
     */
    public String getServersName() {
        return serversName;
    }

    /**
     * 设置服务名称
     * @param serversName 服务名称
     */
    public void setServersName(String serversName) {
        this.serversName = serversName;
    }


    /**
     * 提交数据(Json字符串格式)
     */
    String submitData;

    /**
     * 获取提交数据(Json字符串格式)
     * @return 提交数据(Json字符串格式)
     */
    public String getSubmitData() {
        return submitData;
    }

    /**
     * 设置提交数据(Json字符串格式)
     * @param submitData 提交数据(Json字符串格式)
     */
    public void setSubmitData(String submitData) {
        this.submitData = submitData;
    }





}
