package cn.edu.zjvtit.empdemo.filter;

/**
 * Created by zeng on 2016-10-22.
 */

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;



public class EmpEncodingFilter implements Filter{

    /**
     * 字符集编码
     */
    private String endcoding;


    /**
     * 摧毁拦截器
     */
    @Override
    public void destroy() {
    }

    /**
     * 执行拦截
     * @param request 请求对象
     * @param response 回发对象
     * @param chain 拦截对象
     * @throws IOException 错误抛出
     * @throws ServletException 错误抛出
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        //System.out.println("CharsetEncodingFilter--->>>begin");
        //设置web.xml中配置的字符集
        request.setCharacterEncoding(endcoding);
        //System.out.println("CharsetEncodingFilter--->>>doing");
        response.setCharacterEncoding(endcoding);
        //继续执行
        chain.doFilter(request, response);
        //System.out.println("CharsetEncodingFilter--->>>end");
    }

    /**
     * 字符集设置拦截器初始化
     * @param filterConfig 拦截器初始化参数
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.endcoding = filterConfig.getInitParameter("endcoding");
        //System.out.println("CharsetEncodingFilter.init()-->> endcoding=" + endcoding);
    }

}
