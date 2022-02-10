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
    void ifLegalThreadNum() {
        Assertions.assertTrue(IfLegal.ifLegalThreadNum(1));
    }
}