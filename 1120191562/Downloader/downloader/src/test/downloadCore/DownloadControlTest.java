package test.downloadCore;

import downloadCore.DownloadControl;
import org.junit.jupiter.api.Test;

class DownloadControlTest {
    private final DownloadControl downloadControl=new DownloadControl();
    @Test
    void run() {
        downloadControl.run("https://down5.huorong.cn/sysdiag-full-5.0.65.2-2022.2.10.1.exe","E:\\save",3);

        downloadControl.run("https://down5.huorong.cn/sysdiag-full-5.0.65.2-2022.2.10.1.xxxe","E:\\save",3);
    }
}