package downloadUtil;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Http {




    /**
     *
     * @return 下载链接的HttpURLConnection
     * @throws IOException
     */
    public static HttpURLConnection getHttpURLConnection(String urlpath) throws IOException{
        URL url=new URL(urlpath);
        URLConnection urlConnection = (URLConnection) url.openConnection();
        urlConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
        return (HttpURLConnection) urlConnection;
    }
}
