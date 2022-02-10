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

```

```

## 三、思路

### 第1阶段 实现单个文件的下载功能

第一阶段的要求是，输入url，保存路径，线程数目后实现多线程下载。

大体思路是根据url，获取下载文件的大小。然后根据线程数目，算出来每个线程所要下载的文件块大小。当每个线程负责的文件块下载完毕后，对所有文件按照次序进行合并，最后清除临时文件。

主要用到java中的多线程（线程池），http，IO流，file等方面的知识，主要参考java官方文档和各类博客。

## 四、设计实现过程

详见分析设计文档。

## 五、性能分析

### 第1阶段 实现单个文件的下载功能

#### 改进思路：

1.起初使用普通多线程，后来将其改成线程池。

2.起初将url，下载路径，线程数目，分块大小等信息都记录在了DownloadInfo中，作为public static使用。但这样做破坏了封装性，故后来对代码进行了重构。

#### 使用jprofiler性能分析结果如下:

下载qq.exe，使用8线程，保存在E:\save

![image-20220210184158984](E:\GitHub\downloaderK\1120191562\blog\image\第一阶段性能测试1.png)

![image-20220210184223304](E:\GitHub\downloaderK\1120191562\blog\image\第一阶段性能测试2.png)

![image-20220210184250324](E:\GitHub\downloaderK\1120191562\blog\image\第一阶段性能测试3.png)

![image-20220210185604296](E:\GitHub\downloaderK\1120191562\blog\image\第一阶段性能测试4.png)

根据结果进行分析，消耗最大的函数是类Downloader里的call()函数，因为它负责具体的下载任务。

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

```
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
