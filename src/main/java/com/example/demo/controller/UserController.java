package com.example.demo.controller;

import com.example.demo.admin.update.domain.RoadInformation;
import com.example.demo.common.base.AjaxResult;
import com.example.demo.common.base.BaseController;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <h3>demo</h3>
 * <p>User 控制层</p>
 *
 * @author : CTL
 * @date : 2020-10-14 14:33
 */
@RestController
//@RequestMapping("/testController")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;   //service层
//    @RequestMapping("/get.action")   //路径映射
//    //加上了session登录校验 ,已经没人用了
//    public UserModel get(@RequestParam("id") int id, HttpSession session){
//        UserModel user = (UserModel)session.getAttribute("user");
//        if(user==null){
//            return null;
//        }
//        UserModel userModel = userService.get(id);
//        return userModel;
//    }
   @RequestMapping("/get.action")   //路径映射
    public UserModel get(@RequestParam("id") int id){
        UserModel userModel = userService.get(id);
        return userModel;
    }
    //可行..
    @RequestMapping("/getEmail")
    public List<String> getEmail(@RequestParam("username") String username){
        List<String> list = userService.getEmail(username);
        return list;
    }
    @RequestMapping("/getUser")
    public List<UserModel> getUserByAge(@RequestParam("age") int age){
        List<UserModel> users = userService.getUserByAge(age);
        return users;
    }
    //获取所有的数据
    @RequestMapping("/getAll")
    public List<UserModel> getAll(){
        PageHelper.startPage(0,15);
        System.out.println("--------------到这里来了！");
        List<UserModel> users = userService.getAll();
        System.out.println("--------------到这里来了2！！");
        PageInfo<UserModel> page = new PageInfo<UserModel>(users);
        System.out.println("总数量：" + page.getTotal());
        System.out.println("当前页查询记录：" + page.getList().size());
        System.out.println("当前页码：" + page.getPageNum());
        System.out.println("每页显示数量：" + page.getPageSize());
        System.out.println("总页：" + page.getPages());
        return users;
    }

    //获取name
    @RequestMapping("/getName")
    public String getName(@RequestParam("id") int id){
        String name = userService.getName(id);
        return name;
    }
    //Post方法，ajax请求，中间部分是获取接受的数据信息
    @PostMapping("/addUser")
    public void addUser(@RequestParam("id") int id,
                        @RequestParam("name") String name,
                        @RequestParam("age") int age,
                        @RequestParam("email") String email,
                        @RequestParam("address") String address,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password
                        ){
        UserModel userModel = new UserModel(id,name,age,email,address,username,password);//初始化一个实体类对象
        System.out.println("user看看"+userModel);
        //增加user
        userService.addUser(userModel);
    }
    //根据id删除user,可以执行
    @RequestMapping("/delete")
    public void delete(@RequestParam("id") int id){
        UserModel user = userService.get(id); //根据Id获取到user对象
        userService.deleteUser(user);  //调用方法删除userd
    }
    @PostMapping("/update")
    public void update(@RequestParam("id") int id,
                       @RequestParam("name") String name,
                       @RequestParam("age") int age,
                       @RequestParam("email") String email,
                       @RequestParam("address") String address,
                       @RequestParam("username") String username,
                       @RequestParam("password") String password){
        UserModel user = new UserModel(id,name,age,email,address,username,password);  //根据传递的数据新建一个userMode对象
        userService.updateUser(user); //将对象注入到update方法中
    }
    @RequestMapping("/")
    public String nice(){
        return "hello sakura";
    }
    //resultMap测试类
    @RequestMapping("/getUsersAndAuthor")
    public List<UserModel> getUsersAndAuthor(){
       List<UserModel> userModel = userService.getUsersAndAuthor();
       for (UserModel u:userModel){
           System.out.println(u);
       }
       AjaxResult json = success("成功!了！");
       System.out.println("json会是什么呢？"+json);
       return userModel;
    }

    @RequestMapping("/getUsersAndAuthor1")
    public AjaxResult getUsersAndAuthor1(){
        List<UserModel> userModel = userService.getUsersAndAuthor();
        for (UserModel u:userModel){
            System.out.println(u);
        }
        AjaxResult json = success("成功!了！",userModel);
        System.out.println("json会是什么呢？"+json);
        return json;
    }
}
