package test.downloadUtil;

import downloadUI.fileDownload.FileDownloadUI;
import downloadUtil.FileContentReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.JOptionPane;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileContentReaderTest {

    @Test
    void read() {
        String str= FileContentReader.read("src/setting/settings.txt",1);
        Assertions.assertEquals("E:\\save",str);
    }
    @Test
    void write() {
        FileContentReader.write("src/setting/settings.txt","E:\\save","8");
    }
}