package com.example.demo;

//@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);

    ShareData shared = new ShareData();
    LabThread thred1 = new LabThread("thread1", shared, 2000);
    LabThread thred2 = new LabThread("thread2", shared, 4000);

    thred1.start();
    thred2.start();
  }

}
class ShareData {
  private int count = 0;

  public int add() {
    return ++this.count;
  }
}


class LabThread implements Runnable {
  private Thread LabThread;
  private final String name;
  private final int delay;
  private final ShareData data;

  LabThread(String name, ShareData data, int delay) {
    this.name = name;
    this.data = data;
    this.delay = delay;
  }

  @Override
  public void run() {
    System.out.println("Thread em execucao: " + name);
    for (int i = 0; i < 8; i++) {
      System.out.println(data.add() + " " + name);
      try {
        Thread.sleep(delay);
      } catch (InterruptedException e) {
        System.out.println("Thread interruption" + name);
      }
    }
  }

  public void start() {
    System.out.println("Thread comecou: " + name);

    if (LabThread == null) {
      LabThread = new Thread(this, name);
      LabThread.start();
    }
  }
}
