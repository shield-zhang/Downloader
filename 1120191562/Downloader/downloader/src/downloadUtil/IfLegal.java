package downloadUtil;



import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;

public class IfLegal {
    /**
     * URL是否合法
     * @param url 传入的URL
     * @return 合法TRUE，非法false
     */
    public static boolean ifLegalURL(String url) {

        if (url == null || url.length() == 0) {
            return false;
        }

        try {
            URL url1 = new URL(url);
            URLConnection urlConnection = url1.openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断保存路径是否合法
     * @param savePath 保存路径
     * @return 合法TRUE，非法false
     */
    public static boolean ifLegalSavePath(String savePath) {
        File file = new File(savePath);
        if (file.exists()) { //用来测试此路径名表示的文件或目录是否存在
            return file.isDirectory();//测试该目录是否存在
        }
        return false;
    }
    /**
     * 判断线程数是否合法
     * @param threadNum 线程数
     * @return 合法TRUE，非法false
     */
    public static boolean ifLegalThreadNum(int threadNum) {
        return threadNum > 0 && threadNum <= 32;
    }

    /**
     * 文件名是否存在
     * @param fileName
     * @return 存在返回true，否则返回false
     */
    public static boolean ifLegalFileName(String fileName){
        File file = new File(fileName);
        if (file.exists()) { //用来测试此路径名表示的文件或目录是否存在
            return file.isFile();
        }
        return  false;
    }

    /**
     * 保存Url的数组中的每个url是否可以正常访问
     * @param urls
     * @return 是返回TRUE，否则返回false
     */
    public static boolean ifLegalUrls(String[] urls){
        for (int i = 0; i < urls.length; i++) {
            if (!ifLegalURL(urls[i])){
                return  false;
            }
        }
        return true;
    }
}
