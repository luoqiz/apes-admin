package top.luoqiz.system.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import top.luoqiz.common.web.utils.SpringBeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 渲染到客户端的工具类
 *
 * @author luoqiz
 */
public class ServletUtils {

    /**
     * 渲染到客户端
     *
     * @param object 待渲染的实体类，会自动转为json
     */
    public static void render(HttpServletRequest request, HttpServletResponse response, Object object) throws IOException {
        // 允许跨域
//        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
//        response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(SpringBeanUtils.getBean(ObjectMapper.class).writeValueAsString(object));
    }
}