package downloadCore;


import downloadUtil.Http;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.concurrent.Callable;

public class Downloader implements Callable<Boolean>  {
    private long beginSite;
    private long endSite;
    private int num;
    public Downloader(long beginSite, long endSite,int num)

    {
        this.beginSite=beginSite;
        this.endSite=endSite;
        this.num=num;
    }

    @Override
    public Boolean call() throws Exception {
        HttpURLConnection httpURLConnection = null;
        String tempFileName=DownloadInfo.fileName+".temp"+num;
        try {
            httpURLConnection = Http.getHttpURLConnection(beginSite,endSite);//建立httpURLConnection
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (
                //利用IO流
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);//读入内存
                RandomAccessFile randomAccessFile=new RandomAccessFile(tempFileName,"rw");

        ) {
            System.out.println(num+"号线程，文件开始下载！");
            byte[] buffer=new  byte[1024];
            int len = -1;
            while ((len = bufferedInputStream.read(buffer)) != -1) {
                randomAccessFile.write(buffer,0,len);
            }
            System.out.println(num+"号线程，文件下载完毕！");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(num+"号线程，找不到所要下载文件！");
            return false;
        } catch (Exception e) {
            System.out.println(num+"号线程，文件下载失败！");
            return false;
        } finally {
            //链接对象关闭
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return false;
        }
    }


    }


