package downloadUtil;

import downloadCore.DownloadInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Http {
    /**
     * 获取每个分块的HttpURLConnection
     * @param url 下载链接
     * @param beginSite 分块始位置
     * @param endSite 分块结束位置
     * @return
     * @throws IOException
     */
    public static HttpURLConnection getHttpURLConnection(long beginSite,long endSite) throws IOException {
        HttpURLConnection httpURLConnection=getHttpURLConnection();
        if (endSite!=-1){
            httpURLConnection.setRequestProperty("RANGE","bytes="+beginSite+"-"+endSite);
        }else {
            httpURLConnection.setRequestProperty("RANGE","bytes="+beginSite+"-");
        }


        return httpURLConnection;
    }
    /**
     * 将下载链接转换为HttpURLConnection
     * @return
     * @throws
     */
    public static HttpURLConnection getHttpURLConnection() throws IOException {
        URL url = new URL(DownloadInfo.url);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
        return (HttpURLConnection) urlConnection;
    }

}