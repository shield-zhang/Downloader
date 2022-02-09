import downloadCore.DownloadControl;
import downloadCore.DownloadInfo;
import downloadCore.Downloader;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url;
        String savePath;
        System.out.println("请输入文件下载链接：");
        Scanner scanner=new Scanner(System.in);
        url=scanner.next();
        System.out.println("请输入文件保存地址：");
        Scanner scanner2=new Scanner(System.in);
        savePath=scanner2.next();
        DownloadInfo.set(url,savePath,8);
        System.out.println(DownloadInfo.threadNum);
        DownloadControl downloadControl = new DownloadControl();
        downloadControl.run();
    }

}