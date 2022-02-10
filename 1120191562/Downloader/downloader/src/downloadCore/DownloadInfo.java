package downloadCore;

import downloadUtil.Http;

import java.io.IOException;
import java.net.HttpURLConnection;

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