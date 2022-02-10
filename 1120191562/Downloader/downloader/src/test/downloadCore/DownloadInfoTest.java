package test.downloadCore;

import downloadCore.DownloadInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DownloadInfoTest {

    @Test
    void getFileName() {
        Assertions.assertEquals(DownloadInfo.getFileName("https://down5.huorong.cn/sysdiag-full-5.0.65.2-2022.2.10.1.exe","E:\\save"),"E:\\save\\sysdiag-full-5.0.65.2-2022.2.10.1.exe");
    }

    @Test
    void calculateEverySize() {
        DownloadInfo.calculateEverySize("https://down5.huorong.cn/sysdiag-full-5.0.65.2-2022.2.10.1.exe",8);

        Assertions.assertEquals(DownloadInfo.calculateEverySize("sdfaeqewwwqqqa",8),0);
    }
}