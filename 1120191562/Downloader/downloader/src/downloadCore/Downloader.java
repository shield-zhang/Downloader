package downloadCore;


import downloadUtil.Http;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public record Downloader(CountDownLatch countDownLatch, long beginSite, long endSite,
                         int num) implements Callable<Boolean> {

    @Override
    public Boolean call() {
        HttpURLConnection httpURLConnection = null;
        String tempFileName = DownloadInfo.fileName + ".temp" + num;
        try {
            httpURLConnection = Http.getHttpURLConnection(beginSite, endSite);//建立httpURLConnection
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
                byte[] buffer = new byte[DownloadInfo.BYTE_SIZE];
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

}


