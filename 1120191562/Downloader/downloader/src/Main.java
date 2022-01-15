import downloadCore.Downloader;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url;
        System.out.println("请输入下载链接：");
        Scanner scanner=new Scanner(System.in);
        url=scanner.next();
        String downloadPath;
        System.out.println("请输入文件保存地址：");
        Scanner scanner2=new Scanner(System.in);
        downloadPath=scanner2.next();
        //开始下载
        Downloader downloader=new Downloader();
        downloader.download(url,downloadPath);

    }

}