package test.downloadUtil;

import downloadUtil.GetClipbrd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class GetClipbrdTest {

    @Test
    void getClipbrd() {
        Assertions.assertEquals("https://down5.huorong.cn/sysdiag-full-5.0.65.2-2022.2.11.1.exe", GetClipbrd.getClipbrd());
    }
}