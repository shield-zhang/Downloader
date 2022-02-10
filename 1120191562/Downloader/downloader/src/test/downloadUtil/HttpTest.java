package test.downloadUtil;

import downloadUtil.Http;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

class HttpTest {

    @Test
    HttpURLConnection getHttpURLConnection() throws IOException {
     return    Http.getHttpURLConnection("https://down5.huorong.cn/sysdiag-full-5.0.65.2-2022.2.10.1.exe");

    }

    @Test
    HttpURLConnection testGetHttpURLConnection() throws IOException {
       return  Http.getHttpURLConnection("https://down5.huorong.cn/sysdiag-full-5.0.65.2-2022.2.10.1.exe",0,1024*100);

    }
}