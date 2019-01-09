package com.caicloud.controller;

import com.caicloud.entity.User;
// import com.caicloud.repository.UserRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

  // @Autowired
  // private UserRepository userRepository;

  @Qualifier("eurekaClient")
  @Autowired
  private EurekaClient eurekaClient;


  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }

  /**
   * 查询用户信息
   * @param id
   * @return
   */
  @GetMapping("/getUser/{id}")
  public User findById(@PathVariable Long id) {
    // Optional<User> userCategory = userRepository.findById(id);
    // if(userCategory.isPresent()){
    //   return userCategory.get();
    // }else {
    //   return new User(999L, "不存在");
    // }
    return new User(id, id.toString());
  }

  /**
   * 接口提交-json
   * @param user
   * @return
   */
  @PostMapping("/user")
  public User postUser(@RequestBody User user) {
    // return userRepository.save(user);
    return user;
  }

  /**
   *
   * 表单提交
   * @param user
   * @return
   */
  @RequestMapping(value = "/user.from")
  public User fromUser(@ModelAttribute("form") User user) {
    // return userRepository.save(user);
    return user;
  }

  /**
   *
   * 删除用户
   * @param id
   * @return
   */
  @RequestMapping(value = "/delete/{id}")
  public String deleteUser(@PathVariable Long id) {
    // userRepository.deleteById(id);
    return "sucess";
  }

  @GetMapping("get-all")
  public List<User> usersAll() {
    // return userRepository.findAll();
    ArrayList<User> list = new ArrayList();
    User user = new User(1L, "zhangsan");
    User user2 = new User(2L, "zhangsan");
    User user3 = new User(3L, "zhangsan");
    list.add(user);
    list.add(user2);
    list.add(user3);
    return list;
  }

  @GetMapping("/eureka-instance")
  public String serviceUrl() {
    InstanceInfo instance = this.eurekaClient.getNextServerFromEureka("CAICLOUD-PROVIDER-USER", false);
    return instance.getHomePageUrl();
  }

  @GetMapping("list-all")
  public List<User> listAll() {
    ArrayList<User> list = new ArrayList();
    User user = new User(1L, "zhangsan");
    User user2 = new User(2L, "zhangsan");
    User user3 = new User(3L, "zhangsan");
    list.add(user);
    list.add(user2);
    list.add(user3);
    return list;
  }
}
