import downloadCore.DownloadControl;
import downloadUtil.IfLegal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
            String url;
            String savePath;
            int threadNum;
            System.out.println("请输入文件下载链接：");
            Scanner scanner = new Scanner(System.in);
            url = scanner.next();

            while (!IfLegal.ifLegalURL(url)) {
                System.out.println("该链接无法访问，请重新输入:");
                url = scanner.next();
            }
            System.out.println("输入1保存路径为默认地址（即当前地址），输入2为自定义地址：");
            int  temp=scanner.nextInt();
            if(temp==1){
                System.out.println("保存路径为默认地址： "+System.getProperty("user.dir"));
                savePath=System.getProperty("user.dir");
            }else{
                System.out.println("请输入文件保存地址：");
                savePath = scanner.next();
                while (!IfLegal.ifLegalSavePath(savePath)) {
                    System.out.println("该路径不存在，请重新输入！");
                    savePath = scanner.next();
                }
            }
            System.out.println("输入1保存路径为默认线程（即8线程），输入2选择自定义线程数(1~32)：");
            temp=scanner.nextInt();
            if (temp==1){
                System.out.println("您选择了8线程下载！");
                threadNum=8;
            }else {
                System.out.println("请输入线程数（1~32）：");

                threadNum = scanner.nextInt();
                while (!IfLegal.ifLegalThreadNum(threadNum)) {
                    System.out.println("线程数目非法，请重新输入！");
                    threadNum = scanner.nextInt();
                }
            }


            DownloadControl downloadControl = new DownloadControl();
            downloadControl.run(url,savePath,threadNum);
            //阻塞
            System.out.println("输入1继续，输入0退出程序：");

            if (scanner.nextInt() != 1) {
                flag = false;
            }
        }
    }

}