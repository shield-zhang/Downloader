

# 个人项目-并发文件下载助手

## 零、任务

实现一个可以并发下载文件的软件。

## 一、需求：

### 第1阶段 实现单个文件的下载功能

可以通过参数指定要下载的文件地址，下载的并发线程数（默认线程数为8），存放文件的地址（默认为当前目录）。

包括以下参数：

```
--url URL, -u URL                URL to download
--output filename, -o filename   Output filename
--concurrency number, -n number  Concurrency number (default: 8)
```

注意：并不是所有网站都支持多线程并发下载，需要先进行检测。
### 第2阶段 实现批量多协议文件下载功能

指定下载地址时

- 可以在参数中指定多个要下载的文件地址，即允许使用多个url参数
- 可以将这些地址放入一个文本文件中，指定从该文件中读取。参数

```
--input filename, -i filename filename with multiple URL
```

- 支持使用正则表达式来生成多个下载地址，

同时，除支持http/https协议外，也能够支持如ftp, bittorrent，磁力链等。

### 第3阶段 用户界面

目前的下载助手只有命令行版本，使用起来不是很方便，可以给用户提供GUI（图形用户界面）。至少提供三个功能：

- 添加下载链接：单个或多个、批量生成
- 可自动从系统剪贴板得到下载链接
- 下载设置：包括并发数、输出位置，并可保存设置信息，在下次启动应用时可自动加载

## 二、PSP表格： 

### 第1阶段 实现单个文件的下载功能
| PSP2.1 | Personal Software Process Stages | 预估耗时（分钟） | 实际耗时（分钟） |
| --- | --- | --- | --- |
| Planning | 计划 | 30               | 30               |
| · Estimate | · 估计这个任务需要多少时间 | 1050 | 2200         |
| Development | 开发 | 900 | 1900 |
| · Analysis | · 需求分析 (包括学习新技术) | 200 | 800 |
| · Design Spec | · 生成设计文档 | 100 | 100 |
| · Design Review | · 设计复审 (和同事审核设计文档) | 10 | 20 |
| · Coding Standard | · 代码规范 (为目前的开发制定合适的规范) | 30 | 20 |
| · Design | · 具体设计 | 60 | 100 |
| · Coding | · 具体编码 | 400 | 500 |
| · Code Review | · 代码复审 | 30 | 30 |
| · Test | · 测试（自我测试，修改代码，提交修改） | 100 | 330 |
| Reporting | 报告 | 150              | 300 |
| · Test Report | · 测试报告 | 120 | 280 |
| · Size Measurement | · 计算工作量 | 20 | 10 |
| · Postmortem & Process Improvement Plan | · 事后总结, 并提出过程改进计划 | 30 | 10 |
|  | 合计 | 1050 | 2200 |

### 第2阶段 实现单个文件的下载功能
| PSP2.1 | Personal Software Process Stages | 预估耗时（分钟） | 实际耗时（分钟） |
| --- | --- | --- | --- |
| Planning | 计划 | 10             | 10 |
| · Estimate | · 估计这个任务需要多少时间 | 700             | 600              |
| Development | 开发 | 550 | 500 |
| · Analysis | · 需求分析 (包括学习新技术) | 100 | 150 |
| · Design Spec | · 生成设计文档 | 50 | 30 |
| · Design Review | · 设计复审 (和同事审核设计文档) | 10 | 10 |
| · Coding Standard | · 代码规范 (为目前的开发制定合适的规范) | 10 | 10 |
| · Design | · 具体设计 | 30 | 20 |
| · Coding | · 具体编码 | 250 | 180 |
| · Code Review | · 代码复审 | 30 | 20 |
| · Test | · 测试（自我测试，修改代码，提交修改） | 70 | 100 |
| Reporting | 报告 | 150              | 100 |
| · Test Report | · 测试报告 | 120 | 60 |
| · Size Measurement | · 计算工作量 | 10 | 10 |
| · Postmortem & Process Improvement Plan | · 事后总结, 并提出过程改进计划 | 20               | 30 |
|  | 合计 | 700 | 600 |

### 第3阶段 用户界面
| PSP2.1 | Personal Software Process Stages | 预估耗时（分钟） | 实际耗时（分钟） |
| --- | --- | --- | --- |
| Planning | 计划 | 10             | 10 |
| · Estimate | · 估计这个任务需要多少时间 | 500           | 550             |
| Development | 开发 | 350 | 400 |
| · Analysis | · 需求分析 (包括学习新技术) | 100 | 150 |
| · Design Spec | · 生成设计文档 | 30 | 20               |
| · Design Review | · 设计复审 (和同事审核设计文档) | 10 | 10 |
| · Coding Standard | · 代码规范 (为目前的开发制定合适的规范) | 10 | 10 |
| · Design | · 具体设计 | 30 | 20 |
| · Coding | · 具体编码 | 240 | 220 |
| · Code Review | · 代码复审 | 10 | 10 |
| · Test | · 测试（自我测试，修改代码，提交修改） | 30 | 100 |
| Reporting | 报告 | 150              | 100 |
| · Test Report | · 测试报告 | 120 | 60 |
| · Size Measurement | · 计算工作量 | 10 | 10 |
| · Postmortem & Process Improvement Plan | · 事后总结, 并提出过程改进计划 | 20               | 30 |
|  | 合计 | 700 | 600 |

```

```

## 三、思路

### 第1阶段 实现单个文件的下载功能

第一阶段的要求是，输入url，保存路径，线程数目后实现多线程下载。

大体思路是根据url，获取下载文件的大小。然后根据线程数目，算出来每个线程所要下载的文件块大小。当每个线程负责的文件块下载完毕后，对所有文件按照次序进行合并，最后清除临时文件。

主要用到java中的多线程（线程池），http，IO流，file等方面的知识，主要参考java官方文档和各类博客。

### 第2阶段 实现批量多协议文件下载功能

第二阶段主要任务是从文本中获取各类下载链接。

主要思路是，新建一个类UrlReader，专门从输入中获取下载的url。并将获取到的url保存为https的链接形式，保存在一个私有属性String[] urls 中。类UrlReader其下应该包含三个方法：

#### getFromVariableUrls(String urls)

从输入的参数中获取url。输入的参数为一个字符串，不同的链接之间用空格分开，利用split函数分割后，保存在私有属性String[] urls 中。

#### getFromFileUrls(String fileName)

根据文件内容获取url。输入的参数保存有下载链接的文本文档，每行为一个下载链接。采用IO流的方式，利用Scanner类读取文件，利用其中的hasNextLine()和nextLine()方法，将其保存在私有属性String[] urls 中。

#### getFromRegexUrl(String urls，int start, int end)

根据正则表达式获取Url，用“{}”表示url中可以被替换的部分。 start和end表示批量下载链接中有规律连续的部分的头尾。

另外，再根据需要，对Main函数做出相应修改。

#### 单元测试设计：

根据每个函数的特点，设计相应的单元测试。详见 [软件测试.md](软件测试.md)

### 第3阶段 用户界面

第3阶段界面初步设计如下

![](E:\GitHub\downloaderK\1120191562\blog\image\3\界面\1.png)

打开项目后，会自动获取剪切板内容到 “本地文件名或者url” 中，根据剪切板中的内容，选择不同的下载按钮。同时，可显示当前的下载状态（完成？失败？正在下载？）。

输入文件保存地址和线程数目，可以点击保存，这样再次打开项目时还会保存当前设置。

关于自动获取剪切板内容，可以新建一个类，ClipboardGet，应该有方法getClipboard。

保存设置时，在项目中建立一个txt文件，利用文件读写，每次打开时从文件中读出保存地址和线程数；保存设置时，再将保存地址和线程数写入文件。

#### 单元测试设计：

根据每个函数的特点，设计相应的单元测试。详见 [软件测试.md](软件测试.md)



## 四、设计实现过程

### 详见[分析设计文档](分析设计文档.md)。

## 五、性能分析

### 第1阶段 实现单个文件的下载功能

#### 改进思路：

1.起初使用普通多线程，后来将其改成线程池。

2.起初将url，下载路径，线程数目，分块大小等信息都记录在了DownloadInfo中，作为public static使用。但这样做破坏了封装性，故后来对代码进行了重构。

#### 使用jprofiler性能分析结果如下:

下载qq.exe，使用8线程，保存在E:\save

![image-20220210184158984](.\image\1\第一阶段性能测试1.png)

![image-20220210184223304](.\image\1\第一阶段性能测试2.png)

![image-20220210184250324](.\image\1\第一阶段性能测试3.png)

![image-20220210185604296](.\image\1\第一阶段性能测试4.png)

![image-20220210232741493](.\image\1\第一阶段性能测试5.png)



根据结果进行分析，消耗最大的函数是类DownloadControl里的run函数，因为它负责全局的调控。



### 第2阶段 实现批量多协议文件下载功能

#### 使用jprofiler性能分析结果如下:

![image-20220211185021618](E:\GitHub\downloaderK\1120191562\blog\image\2\性能分析\1.png)

![image-20220211185142551](E:\GitHub\downloaderK\1120191562\blog\image\2\性能分析\2.png)

![image-20220211185444011](E:\GitHub\downloaderK\1120191562\blog\image\2\性能分析\3.png)

可见，新增类的性能优良。在随后的测试过程中又调整了相应的函数，优化了性能。

## 六、代码说明

### 第1阶段 实现单个文件的下载功能

#### 类Http：

用于获取HttpURLConnection对象。有两个重载函数HttpURLConnection getHttpURLConnection(String url，long beginSite,long endSite)和HttpURLConnection getHttpURLConnection(String url1)。前者根据起始位置和结束位置获取目标文件的HttpURLConnection，用于分块文件下载；后者直接根据url获取目标文件的HttpURLConnection。

```java

public class Http {
    /**
     * 获取每个分块的HttpURLConnection
     */
    public static HttpURLConnection getHttpURLConnection(String url,long beginSite,long endSite) throws IOException {
        HttpURLConnection httpURLConnection=getHttpURLConnection(url);
        if (endSite!=-1){
            httpURLConnection.setRequestProperty("RANGE","bytes="+beginSite+"-"+endSite);
        }else {
            httpURLConnection.setRequestProperty("RANGE","bytes="+beginSite+"-");
        }


        return httpURLConnection;
    }
    /**
     * 将下载链接转换为HttpURLConnection
     */
    public static HttpURLConnection getHttpURLConnection(String url1) throws IOException {
        URL url = new URL(url1);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
        return (HttpURLConnection) urlConnection;
    }

}
```

#### 类DownloadInfo：

该类包含getFileName函数和calculateEverySize函数，前者getFileName用于获取文件名，calculateEverySize用于计算每个线程所要下载的文件块的大小。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    

```java
public class DownloadInfo {
    public static  final int Byte_Size=1024*100;

    /**
     * 获取保存路径的文件名
     * @param url 链接
     * @param savePath 保存路径
     * @return 文件名
     */
    public static String getFileName(String url, String savePath) {
        int index = url.lastIndexOf("/");
        return savePath +"\\"+ url.substring(index + 1);

    }

    /**
     * 计算每个线程要下载文件块的大小
     * @param url 链接
     * @param threadNum 线程数目
     * @return 每个文件块的大小
     */
    public static long calculateEverySize(String url,int threadNum) {

        long FileSize;
        HttpURLConnection httpURLConnection = null;
        long everySize;
        try {
            //获取下载文件的大小
            httpURLConnection = Http.getHttpURLConnection(url);
            FileSize = httpURLConnection.getContentLength();

            //计算切分后的文件大小
            everySize = FileSize / threadNum;
            return everySize;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //链接对象关闭
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return 0;
    }
}
```

#### 类DownloadControl：

DownloadControl负责全局掌控。其下有三个方法：

run负责全局掌控，进行下载任务的切分，给每个线程都分配一定的任务，然后利用线程池进行多线程下载。最后调用mergeTemp和clearTemp方法，按照次序合并临时文件，合并完成后清除临时文件。

```java
public class DownloadControl {
    //CountDownLatch用于使线程同步

    public void run(String url,String savePath, int threadNum){
        final CountDownLatch countDownLatch=new CountDownLatch(threadNum);
        String fileName=DownloadInfo.getFileName(url,savePath);
        long everySize=DownloadInfo.calculateEverySize(url,threadNum);
        //采用线程池的方式
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(threadNum,threadNum,0, TimeUnit.SECONDS,new ArrayBlockingQueue<>(6));
        //进行任务切分
        for (int i = 0;i<threadNum;i++) {
            long beginSite = (long) i * everySize;
            long endSite = beginSite + everySize;
            if (i == threadNum - 1) {
                endSite = -1;
            }
            if (i != 0) {
                beginSite++;
            }
            Downloader downloader = new Downloader(fileName,url,countDownLatch,beginSite, endSite, i);
           threadPoolExecutor.submit(downloader);

        }
        //阻塞分块文件，等所有文件下载完成后再合并
        try {
            countDownLatch.await();
        }catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("线程出现异常阻塞！");
        }

        //合并文件
        mergeTemp(fileName,threadNum);
        clearTemp(fileName,threadNum);
        //关闭线程池
       threadPoolExecutor.shutdown();


    }

    /**
     * 合并临时文件
     */
    public void mergeTemp(String fileName,int threadNum){
        System.out.println("开始合并文件");
        byte[] buffer=new  byte[DownloadInfo.Byte_Size];

        int len;
        try (RandomAccessFile randomAccessFile=new RandomAccessFile(fileName,"rw")){
            for (int i = 0; i < threadNum; i++) {
                try (
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileName + ".temp" + i))//读入内存
                        ){
                    while ((len=bufferedInputStream.read(buffer))!=-1){
                        randomAccessFile.write(buffer,0,len);
                    }
                }
            }
            System.out.println("文件合并完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除临时文件
     */
    public void clearTemp(String fileName,int threadNum){
        for (int i = 0; i < threadNum; i++) {
            File file=new File(fileName+".temp"+i);
            file.delete();
        }
        System.out.println("已清除临时文件");
    }

}
public class DownloadControl {
    //CountDownLatch用于使线程同步
    private final CountDownLatch countDownLatch=new CountDownLatch(DownloadInfo.threadNum);
    public void run(){
        //采用线程池的方式
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(DownloadInfo.threadNum,DownloadInfo.threadNum,0, TimeUnit.SECONDS,new ArrayBlockingQueue<>(6));
        //进行任务切分
        for (int i = 0;i<DownloadInfo.threadNum;i++) {
            long beginSite = (long) i * DownloadInfo.everySize;
            long endSite = beginSite + DownloadInfo.everySize;
            if (i == DownloadInfo.threadNum - 1) {
                endSite = -1;
            }
            if (i != 0) {
                beginSite++;
            }
            Downloader downloader = new Downloader(countDownLatch,beginSite, endSite, i);
           threadPoolExecutor.submit(downloader);

        }
        //阻塞分块文件，等所有文件下载完成后再合并
        try {
            countDownLatch.await();
        }catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("线程出现异常阻塞！");
        }

        //合并文件
        mergeTemp();
        clearTemp();
        //关闭线程池
       threadPoolExecutor.shutdown();


    }

    /**
     * 合并临时文件
     */
    public void mergeTemp(){
        System.out.println("开始合并文件");
        byte[] buffer=new  byte[DownloadInfo.BYTE_SIZE];

        int len;
        try (RandomAccessFile randomAccessFile=new RandomAccessFile(DownloadInfo.fileName,"rw")){
            for (int i = 0; i < DownloadInfo.threadNum; i++) {
                try (
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(DownloadInfo.fileName + ".temp" + i))//读入内存
                        ){
                    while ((len=bufferedInputStream.read(buffer))!=-1){
                        randomAccessFile.write(buffer,0,len);
                    }
                }
            }
            System.out.println("文件合并完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除临时文件
     */
    public void clearTemp(){
        for (int i = 0; i < DownloadInfo.threadNum; i++) {
            File file=new File(DownloadInfo.fileName+".temp"+i);
            file.delete();
        }
        System.out.println("已清除临时文件");
    }
}
```

#### 类Downloader：

Downloader负责具体的下载任务。根据起始位置，结束位置，和线程的编号，利用IO流实现每个文件块的下载。由于使用了线程池的submit，本类需要实现Callable。

```java
//record作为Downloader的顶级类
public record Downloader(String fileName,String url,CountDownLatch countDownLatch, long beginSite, long endSite,
                         int num) implements Callable<Boolean> {

    @Override
    public Boolean call() {

        HttpURLConnection httpURLConnection = null;
        //临时文件名
        String tempFileName = fileName + ".temp" + num;
        try {
            httpURLConnection = Http.getHttpURLConnection(url,beginSite, endSite);//建立httpURLConnection
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert httpURLConnection != null;
            try (//利用IO流,读入内存
                 InputStream inputStream = httpURLConnection.getInputStream();
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                 //随机读写
                 RandomAccessFile randomAccessFile = new RandomAccessFile(tempFileName, "rw")

            ) {
                System.out.println(num + "号线程，文件开始下载！");
                byte[] buffer = new byte[DownloadInfo.Byte_Size];
                int len;
                while ((len = bufferedInputStream.read(buffer)) != -1) {
                    randomAccessFile.write(buffer, 0, len);
                }
                System.out.println(num + "号线程，文件下载完毕！");
                return true;
            }
        } catch (FileNotFoundException e) {
            System.out.println(num + "号线程，找不到所要下载文件！");
            return false;
        } catch (Exception e) {
            System.out.println(num + "号线程，文件下载失败！");
            return false;
        } finally {
            countDownLatch.countDown();
            //链接对象关闭
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return true;
        }

    }

```

#### 类IfLegal:

主要判断输入的合法性。即URL，保存路径，线程数是否合法。

```java
public class IfLegal {
    /**
     * URL是否合法
     * @param url 传入的URL
     * @return 合法TRUE，非法false
     */
    public static boolean ifLegalURL(String url) {

        if (url == null || url.length() == 0) {
            return false;
        }

        try {
            URL url1 = new URL(url);
            URLConnection urlConnection = url1.openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }

    /**
     * 判断保存路径是否合法
     * @param savePath 保存路径
     * @return 合法TRUE，非法false
     */
    public static boolean ifLegalSavePath(String savePath) {
        File file = new File(savePath);
        if (file.exists()) { //用来测试此路径名表示的文件或目录是否存在
            return file.isDirectory();//测试该目录是否存在
        }
        return false;
    }

    /**
     * 判断线程数是否合法
     * @param threadNum 线程数
     * @return 合法TRUE，非法false
     */
    public static boolean ifLegalThreadNum(int threadNum) {
        return threadNum > 0 && threadNum < 32;
    }
}
```

### 第2阶段 实现单个文件的下载功能

第二阶段主要任务是从文本中获取各类下载链接。

主要思路是，新建一个类UrlReader，专门从输入中获取下载的url。并将获取到的url保存为https的链接形式，保存在一个私有属性String[] urls 中。类UrlReader其下应该包含三个方法：

**getFromVariableUrls(String urls)**

从输入的参数中获取url。输入的参数为一个字符串，不同的链接之间用空格分开，利用split函数分割后，保存在私有属性String[] urls 中。

**getFromFileUrls(String fileName)**

根据文件内容获取url。输入的参数保存有下载链接的文本文档，每行为一个下载链接。采用IO流的方式，利用Scanner类读取文件，利用其中的hasNextLine()和nextLine()方法，将其保存在私有属性String[] urls 中。

**getFromRegexUrl(String urls，int start, int end)**

根据正则表达式获取Url，用“{}”表示url中可以被替换的部分。 start和end表示批量下载链接中有规律连续的部分的头尾。

另外，再根据需要，对Main函数做出相应修改。

#### 类UrlReader

```java
public class UrlReader {
    private String[] urlArray;
    public String[] getUrlArray() {
        return urlArray;
    }

    /**
     * 根据参数获取url
     * @param urls urls，中间用“ ”隔开
     */
    public void getFromVariableUrls(String urls){
        urlArray=urls.split(" ");
    }

    /**
     * 根据文件获取url
     * @param fileName 文件名
     */
    public void getFromFileUrls(String fileName){

        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            int index=0;
            StringBuilder tempStr= new StringBuilder();
            while (scanner.hasNextLine()){
                tempStr.append(scanner.nextLine()).append(" ");
                urlArray= tempStr.toString().split(" ");
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件不能找到");
            e.printStackTrace();

        }
    }

    /**
     * 根据正则表达式获取url
     * @param url url
     * @param start 起始数字
     * @param end 结束数字
     */
    public void getFromRegexUrl(String url,int start, int end){
        String[] tempUlrs=new String[end-start+1];
        for (int i = 0; i < end -start+1; i++) {
            tempUlrs[i]=url;
          tempUlrs[i]= String.format(tempUlrs[i].replace("{}","%s"),Integer.toString(i+start) );
        }
      urlArray=tempUlrs;
    }
}
```

#### 类Main

```java
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
```
