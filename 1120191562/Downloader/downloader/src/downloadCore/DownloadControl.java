package downloadCore;



import java.io.*;

import java.util.concurrent.*;

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
