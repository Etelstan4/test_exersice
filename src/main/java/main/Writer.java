package main;

public class Writer {
  private volatile String res = "";   // строка с данными из потоков
  private volatile boolean current;   // переключатель потоков

  public String getRes() {
    return res;
  }

  public void setRes(String res) {
    this.res = res;
  }

  private synchronized void add(int num) {
    res += num;
    System.out.println(res);
  }

  public void startThread(String name, int value, boolean active) {
    Thread thread = new Thread(() -> {
      while (true) {
        if (active ^ current) {
          for (int i = 0; i < 10; i++) {
            add(value);
            try {
              Thread.sleep(20);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          current = !current;
        }
      }
    });
    thread.setName(name);
    thread.start();
  }
}