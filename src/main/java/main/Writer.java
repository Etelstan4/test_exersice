package main;

public class Writer {
  private volatile String res = "";   // строка с данными из потоков

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
}
