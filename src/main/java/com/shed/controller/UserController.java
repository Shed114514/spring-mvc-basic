package com.shed.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shed.domain.User;
import com.shed.domain.UserListVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    // 直接返回字符串所对应的视图,默认为forward转发
    @RequestMapping("/test1")
    public String test1() {
        // return "/index.jsp";
        //return "forward:/jsp/index.jsp";
        return "index";
    }

    // redirect重定向,直接返回http://localhost:8080/jsp/index.jsp
    @RequestMapping("/test2")
    public String test2() {
        return "redirect:/jsp/index.jsp";
    }

    // ModelAndView
    @RequestMapping("/test3")
    public ModelAndView test3() {
        /**
         * Model 模型: 用于封装数据
         * View  视图: 用于展示数据
         */
        // 可不用手动创建ModelAndView对象,可直接在方法参数上加ModelAndView mv即可,SpringMVC会自动读取该参数
        ModelAndView mv = new ModelAndView();
        // 设置模型数据,存放到域当中
        mv.addObject("username","Allen");
        // 设置视图名称
        mv.setViewName("index");
        return mv ;
    }

    // Model
    @RequestMapping("/test4")
    public String test4(Model model) {
        /**
         * Model相当于把ModelAndView的Model和View拆开,进行数据的封装存放以及展示
         */
        model.addAttribute("username","Ben");
        return "index" ;
    }

    // 通过SpirngMVC注入的response对象,使用response.getWriter.print()回写数据,此时不需要返回视图
    @RequestMapping("/test5")
    public void test5(HttpServletResponse response) throws IOException {
        response.getWriter().print("Hello SpringMVC! --- response.getWriter().print()");
    }

    // @ResponseBody声明的方法所返回的字符串不是页面跳转,而是直接在Http响应体中返回
    // 而在实际项目中,客户端和服务端需要进行JSON格式的交互,因此使用jackson-core依赖进行字符串自动转换
    @RequestMapping("/test6")
    @ResponseBody
    public String test6() throws Exception {
        User user = new User("Allen",18);
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(user);
        return str ;
    }

    // 在spring-mvc.xml中配置了<mvc:annotation-driven/>就会自动把对象或集合转换成JSON格式
    @RequestMapping("/test7")
    @ResponseBody
    public Object test7() {
        User user = new User("Ben",20);
        return user ;
    }

    // 获取基本类型请求参数,http://localhost:8080/user/test8?name=Cindy&age=22
    @RequestMapping("/test8")
    @ResponseBody
    public void test8(String name,Integer age) {
        System.out.println(name);
        System.out.println(age);
    }

    // 获取POJO类型请求参数,所输入的参数名称要与POJO对象的属性名称一直,http://localhost:8080/user/test9?name=Dick&age=25
    @RequestMapping("/test9")
    @ResponseBody
    public Object test9(User user) {
        return user ;
    }

    // 获取数组类型请求参数,请求参数名称要与方法参数名称一致,http://localhost:8080/user/test10?strs=a&strs=b&strs=c
    @RequestMapping("/test10")
    @ResponseBody
    public Object test10(String[] strs) {
        return strs ;
    }

    // 获取集合请求参数时,需要把集合封装在POJO中
    @RequestMapping("/test11")
    @ResponseBody
    public Object test11(UserListVO userListVO) {
        return Arrays.asList(userListVO) ;
    }

    // @RequestParam可与方法参数名称绑定,@RequestParam中的value所定义的名称可以与方法参数名称不一样,还可以参数设置默认值
    @RequestMapping("/test12")
    @ResponseBody
    public Object test12(@RequestParam(value = "username",required = false,defaultValue = "Anonymous") String name,
                         @RequestParam(value = "userage",required = false,defaultValue = "18") Integer age) {
        return name + " " + age;
    }

    // RESTful风格的参数
    @RequestMapping("/test13/{name}/{age}")
    @ResponseBody
    public Object test13(@PathVariable(value = "name",required = false) String name,
                         @PathVariable(value = "age",required = false) Integer age) {
        return new User(name,age);
    }

    // 使用自定义的日期格式转换器,对请求参数进行格式转换,如: http://localhost:8080/user/test14?date=1999-10-13(默认格式为1999/10/13)
    @RequestMapping("/test14")
    @ResponseBody
    public Object test14(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        return dateStr ;
    }

    // SpringMVC原生支持Servlet的api获取
    @RequestMapping("/test15")
    @ResponseBody
    public void test15(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        System.out.println(request);
        System.out.println(response);
        System.out.println(session);
    }

    // 获取请求头
    @RequestMapping("/test16")
    @ResponseBody
    public String test16(@RequestHeader(value = "User-Agent",required = false) String headerValue) {
        return headerValue ;
    }

    // 获取Cookie的值
    @RequestMapping("/test17")
    @ResponseBody
    public String test17(@CookieValue(value = "JSESSIONID",required = false) String jsessionid) {
        return jsessionid ;
    }

    // 文件上传
    @RequestMapping("/test18")
    @ResponseBody
    public String test18(String fileName, MultipartFile[] files) {
        for (MultipartFile file : files){
            String originalFileName = file.getOriginalFilename();
            try {
                file.transferTo(new File("C:\\Users\\Administrator\\Desktop\\temp\\" + originalFileName));
            } catch (Exception e) {
                e.printStackTrace();
                return "File Uploading Failed!";
            }
        }
        return "File Uploaded Successfully!" ;
    }
}
