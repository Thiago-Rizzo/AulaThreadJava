package com.example.demo;

//@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);

    ContaBancaria conta = new ContaBancaria();
    LabThread thred1 = new LabThread("thread_1", conta);
    LabThread thred2 = new LabThread("thread_2", conta);

    thred1.start();
    thred2.start();
  }

}
class ContaBancaria {
  private int saldo = 10;

  public int deposito(int valor) {
    saldo = saldo + valor;
    return saldo;
  }
  
  public int saque(int valor) {
    int valor_sacado;
    if (saldo < valor) {
      System.out.println("nao foi possivel sacar " + valor + " de " + saldo);
      valor_sacado = 0;
    }
    else {
      System.out.println("saquei " + valor + " de " + saldo);
      valor_sacado = valor;
      saldo = saldo - valor;
    }
    return valor_sacado;
  }
}


class LabThread implements Runnable {
  private Thread LabThread;
  private String name;
  private int delay;
  private ContaBancaria conta;

  LabThread(String name, ContaBancaria conta) {
    this.name = name;
    this.conta = conta;
    this.delay = 200;
  }

  @Override
  public void run() {
    System.out.println("Thread em execucao: " + name);
    for (int i = 0; i < 8; i++) {
      conta.saque(1);
      try {
        Thread.sleep(delay);
      } catch (InterruptedException e) {
        System.out.println("Thread interruption " + name);
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
