package downloadUtil;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Http {

    /**
     * 将下载链接转换为HttpURLConnection
     * @return
     * @throws
     */
    public static HttpURLConnection getHttpURLConnection(String urlpath) throws IOException {
        URL url = new URL(urlpath);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
        return (HttpURLConnection) urlConnection;
    }

    /**
     * 获取要下载的文件名
     * @param
     * @return
     */
    public static String getFileName(String url){
        int index=url.lastIndexOf("/");
        return url.substring(index+1);
    }
}
