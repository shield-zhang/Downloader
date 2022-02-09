package downloadCore;


import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DownloadControl {
    public void run(){
        ArrayList<Future> futureArrayList=new ArrayList<>();
        //采用线程池的方式
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(DownloadInfo.threadNum,DownloadInfo.threadNum,0, TimeUnit.SECONDS,new ArrayBlockingQueue<>(6));
        //进行任务切分
        for (int i = 0;i<DownloadInfo.threadNum;i++) {
            long beginSite = i * DownloadInfo.everySize;
            long endSite = beginSite + DownloadInfo.everySize;
            if (i == DownloadInfo.threadNum - 1) {
                endSite = -1;
            }
            if (i != 0) {
                beginSite++;
            }
            Downloader downloader = new Downloader(beginSite, endSite, i);
            Future<Boolean> future = threadPoolExecutor.submit(downloader);
            futureArrayList.add(future);
        }
        for (int i = 0; i < DownloadInfo.threadNum; i++) {
            futureArrayList.get(i);
        }
    }
}
