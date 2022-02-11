package test.downloadUtil;

import downloadUtil.UrlReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class UrlReaderTest {


    @Test
    void getFromVariableUrls() {
        UrlReader urlReader = new UrlReader();
        String testString = "https://www.baidu.com/0 https://www.baidu.com/1 https://www.baidu.com/2";
        urlReader.getFromVariableUrls(testString);
        String[] urls = urlReader.getUrlArray();
        for (int i = 0; i < 3; i++) {
            System.out.println(urls[i]);
        }
        Assertions.assertEquals("https://www.baidu.com/0", urls[0]);
        Assertions.assertEquals("https://www.baidu.com/1", urls[1]);
        Assertions.assertEquals("https://www.baidu.com/2", urls[2]);
    }

    @Test
    void getFromFileUrls() {
        UrlReader urlReader = new UrlReader();
        urlReader.getFromFileUrls("D:\\save\\testUrls.txt");
        String[] urls = urlReader.getUrlArray();
        System.out.println(urls[0]);
        for (int i = 0; i < 3; i++) {
            System.out.println(urls[i]);
        }
        Assertions.assertEquals("https://www.baidu.com/0", urls[0]);
        Assertions.assertEquals("https://www.baidu.com/0", urls[0]);
        Assertions.assertEquals("https://www.baidu.com/1", urls[1]);
        Assertions.assertEquals("https://www.baidu.com/2", urls[2]);

    }

    @Test
    void getFromRegexUrl() {
        UrlReader urlReader = new UrlReader();
        urlReader.getFromRegexUrl("https://www.baidu.com/{}", 0, 2);
        String[] urls = urlReader.getUrlArray();
        Assertions.assertEquals("https://www.baidu.com/0", urls[0]);
        Assertions.assertEquals("https://www.baidu.com/1", urls[1]);
        Assertions.assertEquals("https://www.baidu.com/2", urls[2]);
    }
}


