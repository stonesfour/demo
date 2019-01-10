package com.caicloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RestController
public class CpuController {

  private static Logger log = LoggerFactory.getLogger(CpuController.class);

  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/memory")
  public Long findByMemory() {
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    final int loopCount=2000000;
    final CountDownLatch latch = new CountDownLatch(1);
    executorService.submit(new Runnable() {
      @Override
      public void run() {
        List myList=new ArrayList();
        for (int i=1;i<10000000; i++)
        {
          Object o=new Object();
          myList.add(o);
          log.info("---模拟---memory---:"+i);
        }
        latch.countDown();
      }
    });
    return 1L;
  }

  @GetMapping("/cpu")
  public Long findByCPU() {
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    final int loopCount=2000000;
    final CountDownLatch latch = new CountDownLatch(loopCount);
    for (int i=0;i<loopCount;i++){
      executorService.submit(new Runnable() {
        @Override
        public void run() {
          int a = loopCount;
          while(a>1){
            log.info("---模拟---cpu---:"+(a--));
          }

          System.out.println("Cpu Work"+latch.getCount());
          latch.countDown();
        }
      });
    }
    return 1L;
  }

  public static void main(String[] args) {
    log.trace("======trace");
    log.debug("======debug");
    log.info("======info");
    log.warn("======warn");
    log.error("======error");
  }
}
