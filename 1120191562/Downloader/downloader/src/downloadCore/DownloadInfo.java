package downloadCore;

import downloadUtil.Http;

import java.io.IOException;
import java.net.HttpURLConnection;

public class DownloadInfo {
    public static int threadNum;//线程数
    public static int everySize;//每个线程负责的文件块大小
    public static String url;//下载链接
    public static String savePath;//文件保存路径
    public static String fileName;//文件名
    public static final int BYTE_SIZE = 1024 * 100;

    public static void set(String url, String savePath, int threadNum) {
        System.out.println("进入DownloadInfo.set");
        DownloadInfo.savePath = savePath;
        DownloadInfo.threadNum = threadNum;
        int index = url.lastIndexOf("/");
        DownloadInfo.fileName = DownloadInfo.savePath +"\\"+ url.substring(index + 1);
        DownloadInfo.url = url;
        DownloadInfo.everySize = calculateEverySize();

    }

    public static int calculateEverySize() {

        int FileSize;
        HttpURLConnection httpURLConnection = null;
        try {
            //获取下载文件的大小
            httpURLConnection = Http.getHttpURLConnection(DownloadInfo.url);
            FileSize = httpURLConnection.getContentLength();

            //计算切分后的文件大小
            everySize = FileSize / DownloadInfo.threadNum;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //链接对象关闭
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return everySize;
    }
}