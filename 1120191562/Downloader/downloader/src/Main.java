import downloadCore.DownloadControl;
import downloadUtil.IfLegal;
import downloadUtil.UrlReader;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String url;
        String savePath;
        int threadNum;
        boolean flag = true;


        while (flag) {


            String temp;

            while (true) {
                //选择路径
                System.out.println("输入1保存路径为默认地址（即当前地址），输入2为自定义地址：");
                temp = scanner.next();
                scanner.nextLine();

                if (Objects.equals(temp, "1")) {
                    System.out.println("保存路径为默认地址： " + System.getProperty("user.dir"));
                    savePath = System.getProperty("user.dir");
                    break;

                } else if(Objects.equals(temp, "2")) {
                    System.out.println("请输入文件保存地址：");
                    savePath = scanner.next();
                    scanner.nextLine();
                    while (!IfLegal.ifLegalSavePath(savePath)) {
                        System.out.println("该路径不存在，请重新输入！");
                        savePath = scanner.next();
                        scanner.nextLine();
                    }
                    break;
                }else {
                    System.out.println("请重新输入！");
                }
            }
            while (true) {
                //选择线程数
                System.out.println("输入1为默认线程（即8线程），输入2选择自定义线程数(1~32)：");
                temp = scanner.next();
                scanner.nextLine();
                if (Objects.equals(temp, "1")) {
                    System.out.println("您选择了8线程下载！");
                    threadNum = 8;
                    break;
                } else if (Objects.equals(temp, "2")) {
                    System.out.println("请输入线程数（1~32）：");

                    threadNum = scanner.nextInt();
                    scanner.nextLine();
                    while (!IfLegal.ifLegalThreadNum(threadNum)) {
                        System.out.println("线程数目非法，请重新输入！");
                        threadNum = scanner.nextInt();
                        scanner.nextLine();
                    }
                    break;
                } else {
                    System.out.println("请重新输入！");
                }
            }
            while (true) {
                System.out.println("请输入1~3，选择下载方式：");
                System.out.println("1、可以在参数中指定多个要下载的文件地址，即允许使用多个url参数");
                System.out.println("2、可以将这些地址放入一个文本文件中，指定从该文件中读取。参数");
                System.out.println("3、支持使用正则表达式来生成多个下载地址");
                System.out.println("4、输入单个下载链接进行下载");
                String choose;//选择下载模式
                choose = scanner.next();
                scanner.nextLine();
                if (Objects.equals(choose, "4")) {
                    System.out.println("您选择了模式4");
                    //单个链接下载
                    System.out.println("请输入文件下载链接：");
                    url = scanner.next();
                    scanner.nextLine();
                    while (!IfLegal.ifLegalURL(url)) {
                        System.out.println("该链接无法访问，请重新输入:");
                        url = scanner.next();
                        scanner.nextLine();
                    }
                    DownloadControl downloadControl = new DownloadControl();
                    downloadControl.run(url, savePath, threadNum);
                    break;

                } else if (Objects.equals(choose, "1") || Objects.equals(choose, "2") || Objects.equals(choose, "3")) {

                    UrlReader urlReader = new UrlReader();
                    String tempStr;
                    if (choose.equals("1")) {
                        System.out.println("您选择了模式1");
                        System.out.println("请输入多个链接，中间用空格隔开，批量下载");
                        tempStr = scanner.nextLine();
                        urlReader.getFromVariableUrls(tempStr);
                    } else if (choose.equals("2")) {
                        System.out.println("您选择了模式2");

                        System.out.println("请输入文本文件(每行一个链接)的地址，批量下载");
                        tempStr = scanner.next();
                        scanner.nextLine();
                        while (!IfLegal.ifLegalSavePath(savePath)) {
                            System.out.println("该路径不存在，请重新输入！");
                            tempStr = scanner.next();
                            scanner.nextLine();
                        }
                        urlReader.getFromFileUrls(tempStr);
                    } else {
                        System.out.println("您选择了模式3");
                        System.out.println("使用正则表达式来生成多个下载地址，输入方式如http://www.aaa{}.com,{}为替换部分");
                        System.out.println("输入正则表达式");
                        tempStr = scanner.next();
                        scanner.nextLine();
                        int start, end;
                        System.out.println("输入start");
                        start = scanner.nextInt();
                        System.out.println("输入end");
                        end = scanner.nextInt();
                        urlReader.getFromRegexUrl(tempStr, start, end);
                    }


                    //获得url数组
                    String[] urls = urlReader.getUrlArray();
                    for (int i = 0; i < urls.length; i++) {
                        System.out.println(urls[i]);
                        if (!IfLegal.ifLegalURL(urls[i])) {
                            System.out.println(urls[i] + "无法访问,请重新输入:");
                            flag = false;
                            break;
                        }
                    }


                    if (flag) {
                        for (int i = 0; i < urls.length; i++) {
                            DownloadControl downloadControl = new DownloadControl();
                            downloadControl.run(urls[i], savePath, threadNum);
                        }

                    }

                    break;
                } else {
                    System.out.println("输入错误，重新输入");
                }
            }
            System.out.println("输入1继续，输入0退出程序：");
            if (scanner.nextInt() != 1) {
                flag = false;
            }

        }
    }
}