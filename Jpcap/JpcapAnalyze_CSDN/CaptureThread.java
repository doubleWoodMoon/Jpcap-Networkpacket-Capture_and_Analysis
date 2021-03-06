package Jpcap_Test;

import jpcap.JpcapCaptor;
import jpcap.packet.*;

// 线程任务类,用来捕获数据包;
class CaptureThread implements Runnable {
    private JpcapCaptor jpcap;

    public CaptureThread(JpcapCaptor jpcap) {
        this.jpcap = jpcap;
    }

    boolean run = true;// 用来控制是否抓包;

    @Override
    public void run() {

        while (run) {
            // 抓包并分析
            try {
                printThreadInfo();
                // 开始抓包
                Packet packet = jpcap.getPacket();
                // 创建分析数据包对象 ap,分析数据包
                AnalyzePacket ap = new AnalyzePacket(packet);
                ap.analyzePacket();
                // 抓包线程休眠1秒;
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private static void printThreadInfo() {
        System.out.println("当前运行的线程名为： " + Thread.currentThread().getName());
    }
}
