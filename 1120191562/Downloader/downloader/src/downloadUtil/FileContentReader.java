package downloadUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class FileContentReader {
    /**
     * 读取目标文件的第几行
     * @param fileName 文件名
     * @param index 第几行
     * @return 目标字符串
     */
    public static String read(String fileName, int index) {
        String str = null;//目标字符串
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            for (int i = 0; i < index; i++) {
                scanner.hasNextLine();
                str=scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("链接不能找到");
            e.printStackTrace();
        }
        return str;
    }
}