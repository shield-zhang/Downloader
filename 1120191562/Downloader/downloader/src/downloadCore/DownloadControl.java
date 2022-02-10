package downloadCore;


import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
