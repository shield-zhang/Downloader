package downloadCore;

import downloadUtil.Http;

import java.io.*;
import java.net.HttpURLConnection;

public class Downloader {
    /**
     * @param url      下载链接
     * @param FileName 文件名，默认保存在下载器当前地址
     */
    public void download(String url, String FileName) {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = Http.getHttpURLConnection(url);//根据url建立httpURLConnection
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (
                //利用IO流
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                FileOutputStream fileOutputStream = new FileOutputStream(FileName);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        ) {
            int len=-1;
            while ((len=bufferedInputStream.read())!=-1){
                bufferedOutputStream.write(len);
            }
        } catch (FileNotFoundException e) {
            System.out.println("要下载的文件不存在");
        } catch (Exception e) {
            System.out.println("文件下载失败");
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

    }
}
