package cn.edu.zjvtit.empdemo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by zeng on 2016-10-23.
 */
public class JsonHelp {

    /**
     *
     */
    static ObjectMapper mapper = new ObjectMapper();

    /**
     * 将Java对象转换成Json格式的字符串数据
     * @param Obj 待转换对象
     * @return 转换完毕的Json格式字符串数据
     * @throws JsonProcessingException Json运行转换错误
     */
    public  static  String makeJson (Object Obj) throws JsonProcessingException {
        return mapper.writeValueAsString(Obj);
    }

    /**
     * 将Json格式字符串数据转换成Java对象
     * @param jsonStr 待转换的Json格式字符串数据
     * @param classname 转换后的Java对象的Class类型
     * @return 转换完毕的Java对象
     * @throws IOException IO错误
     */
    public  static  Object formatJson (String jsonStr,Class<?> classname) throws IOException {
        return mapper.readValue(jsonStr, classname);
    }

}
