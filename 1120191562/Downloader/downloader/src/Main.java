import downloadCore.DownloadControl;
import downloadCore.DownloadInfo;
import downloadCore.Downloader;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url;
        String savePath;
        int threadNum;
        System.out.println("请输入文件下载链接：");
        Scanner scanner=new Scanner(System.in);
        url=scanner.next();
        System.out.println("请输入文件保存地址：");
        Scanner scanner2=new Scanner(System.in);
        savePath=scanner2.next();
        System.out.println("请输入线程数：");
        Scanner scanner3=new Scanner(System.in);
        threadNum=scanner3.nextInt();
        DownloadInfo.set(url,savePath,threadNum);
        System.out.println(DownloadInfo.threadNum);
        DownloadControl downloadControl = new DownloadControl();
        downloadControl.run();
    }

}