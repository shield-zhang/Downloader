package downloadUtil;

import java.io.*;
import java.util.Scanner;

public class FileContentReader {
    /**
     * 读取目标文件的第几行
     * @param fileName 文件名
     * @param index 第几行
     * @return 目标字符串
     */
    public static String read(String fileName, int index) {
        System.out.println(System.getProperty("user.dir"));
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
    public static void write(String fileName,String savePath,String threadNum)  {
        try {
            File file= new  File(fileName);
            if (!file.exists()){//如果文件不存在，创建一个新的文件
                file.createNewFile();
            }
            FileWriter fileWriter= new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(savePath+"\n"+threadNum);
            bufferedWriter.flush(); //将数据更新至文件
            bufferedWriter.close();
            fileWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}