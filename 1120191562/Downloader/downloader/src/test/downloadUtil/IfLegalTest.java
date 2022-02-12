package test.downloadUtil;

import downloadUtil.IfLegal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IfLegalTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void ifLegalURL() {
        Assertions.assertFalse(IfLegal.ifLegalURL(""));
        Assertions.assertFalse(IfLegal.ifLegalURL(null));
        Assertions.assertFalse(IfLegal.ifLegalURL("wwwwwa"));
        Assertions.assertTrue(IfLegal.ifLegalURL("https://down5.huorong.cn/sysdiag-full-5.0.65.2-2022.2.10.1.exe"));
    }

    @Test
    void ifLegalSavePath() {
        Assertions.assertTrue(IfLegal.ifLegalSavePath("E:\\save"));
        Assertions.assertFalse(IfLegal.ifLegalSavePath("wwwas"));
    }
    @Test
    void ifLegalFileName() {
        Assertions.assertTrue(IfLegal.ifLegalFileName("E:\\save\\test.txt"));
        Assertions.assertFalse(IfLegal.ifLegalFileName("wwwas"));
    }
    @Test
    void ifLegalThreadNum() {
        Assertions.assertTrue(IfLegal.ifLegalThreadNum(1));
    }
    @Test
    void ifLegalUrls(){
        String[] urls1={"https://down5.huorong.cn/sysdiag-full-5.0.65.2-2022.2.11.1.exe","https://plc.jj20.com/up/allimg/1112/031319114916/1Z313114916-2.jpg","https://plc.jj20.com/up/allimg/1112/031319114916/1Z313114916-2.jpg" };
        String[] urls2={"fdgfhgjhkjluyitf","https://plc.jj20.com/up/allimg/1112/031319114916/1Z313114916-2.jpg","https://plc.jj20.com/up/allimg/1112/031319114916/1Z313114916-2.jpg" };

        Assertions.assertTrue(IfLegal.ifLegalUrls(urls1));
        Assertions.assertFalse(IfLegal.ifLegalUrls(urls2));
    }
}