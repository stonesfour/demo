package com.caicloud.controller;

import com.caicloud.feign.UserFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Random;
import com.caicloud.entity.User;

@RefreshScope
@RestController
@Component
public class MovieController {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private UserFeignClient userFeignClient;

  @Value("${value}")
  private String value;

  @GetMapping("/invoke_hello")
  @HystrixCommand(fallbackMethod = "failToHello")
  public String helloHystrix() {
    // 设置随机3秒内延迟，hystrix默认延迟2秒未返回则熔断，调用回调方法
    int sleepMillis = new Random().nextInt(3000);
    System.out.println("----sleep-time:" + sleepMillis);

    try {
      Thread.sleep(sleepMillis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return this.restTemplate.getForEntity("http://caicloud-provider-user/hello/", String.class).getBody();
  }

  @GetMapping("invoke_config")
  @HystrixCommand(fallbackMethod = "failToHello")
  public String getConfig() {
    return value;
  }

  @GetMapping("/user/{id}")
  public User findByIdFeign(@PathVariable Long id) {
    return this.userFeignClient.findById(id);
  }

  @GetMapping("/user/info/{id}")
  @HystrixCommand(fallbackMethod = "findByIdFallback")
  public User findById(@PathVariable Long id) {
    return this.restTemplate.getForObject("http://caicloud-provider-user/getUser/" + id, User.class);
  }

  @GetMapping("/kuber/back/{id}")
  @HystrixCommand(fallbackMethod = "findByIdFallback")
  public String findByKuber(@PathVariable Long id) {
    return this.restTemplate.getForObject("http://demo-hello:8080/health/kuber+" + id, String.class);
  }

  @GetMapping("/kuber/{id}")
  public String findByKuberParam(@PathVariable Long id) {
    return this.restTemplate.getForObject("http://demo-hello:8080/health/kuber+" + id, String.class);
  }

  @GetMapping("/simple/{id}")
  public Long findId(@PathVariable Long id) {
    return id;
  }

  @GetMapping("/sleep/{times}")
  public Long findSleep(@PathVariable Long times) {

    long a = System.currentTimeMillis();
    try {
      Thread.sleep(times);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println((System.currentTimeMillis() - a) / 1000);

    return times;
  }

  public User findByIdFallback(Long id) {
    User user = new User();
    user.setId(0L);
    return user;
  }

  public String failToHello() {
    return "error";
  }
}
