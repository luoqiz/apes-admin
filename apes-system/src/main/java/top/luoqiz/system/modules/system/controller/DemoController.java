package top.luoqiz.system.modules.system.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.excel.EasyExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.luoqiz.common.utils.LocalDateConverter;
import top.luoqiz.common.utils.LocalDateTimeConverter;
import top.luoqiz.system.modules.system.queryCriteria.SysDictQueryCriteria;
import top.luoqiz.user.entity.SysUserEntity;
import top.luoqiz.user.service.SysUserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: DemoController</p>
 *
 * @author luoqiz
 * @version 1.0
 * @description TODO
 * @date 2021/3/2 15:07
 * @since 1.0
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping
    private Map<String, String> aa() {
        HashMap map = new HashMap<>(3);
        map.put("a", "Aaa");
        map.put("5", "Aaa");
        return map;
    }

    @PreAuthorize("@al.check('dict:list')")
    @GetMapping("/auth")
    public Map<String, String> bb(SysDictQueryCriteria criteria) {
        HashMap map = new HashMap<>(3);
        map.put("b", "Abbbaa");
        map.put("5", "bb");
        map.put("ee", criteria);
        return map;
    }

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象 参照
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), SysUserEntity.class)
                .registerConverter(new LocalDateTimeConverter())
                .registerConverter(new LocalDateConverter())
                .sheet("模板")
                .doWrite(sysUserService.list());
    }

    @GetMapping("download1")
    public void download1(HttpServletResponse response) throws IOException {
        // 设置响应类型
        response.setContentType("application/vnd.ms-excel");
        // 设置字符编码
        response.setCharacterEncoding("utf-8");
        // 设置响应头信息
        String fileName = URLEncoder.encode("hutools", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition",
                "attachment;filename*=utf-8''" + fileName + ".xlsx");

//        List<Student> studentList = new ArrayList<Student>() {
//            {
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                add(new Student("1001", "张三", 23, "男", "陕西西安", dateFormat.parse("2020-09-01")));
//                add(new Student("1002", "李四", 22, "女", "陕西渭南", dateFormat.parse("2020-09-01")));
//            }
//        };
        List<SysUserEntity> studentList = sysUserService.list();

        // 写入文件
        ExcelWriter writer = ExcelUtil.getWriter();
        writer.addHeaderAlias("username", "username");
        writer.addHeaderAlias("deptId", "部門");
        writer.addHeaderAlias("userId", "学号");
        writer.addHeaderAlias("nickName", "姓名");
        writer.addHeaderAlias("phone", "手机号");
        writer.addHeaderAlias("email", "郵箱");
        writer.addHeaderAlias("enabled", "狀態");
        writer.addHeaderAlias("createTime", "入学时间");
        writer.addHeaderAlias("gender", "gender");
        writer.write(studentList, true);

        try {
            writer.flush(response.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    @GetMapping("/userinfo")
    public Authentication bb(Authentication authentication) {
        return authentication;
    }
}
