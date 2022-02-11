package downloadUtil;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class UrlReader {
    private String[] urlArray;
    public String[] getUrlArray() {
        return urlArray;
    }

    /**
     * 根据参数获取url
     * @param urls urls，中间用“ ”隔开
     */
    public void getFromVariableUrls(String urls){
        urlArray=urls.split(" ");
    }

    /**
     * 根据文件获取url
     * @param fileName 文件名
     */
    public void getFromFileUrls(String fileName){

        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            int index=0;
            StringBuilder tempStr= new StringBuilder();
            while (scanner.hasNextLine()){
                tempStr.append(scanner.nextLine()).append(" ");
                urlArray= tempStr.toString().split(" ");
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件不能找到");
            e.printStackTrace();

        }
    }

    /**
     * 根据正则表达式获取url
     * @param url url
     * @param start 起始数字
     * @param end 结束数字
     */
    public void getFromRegexUrl(String url,int start, int end){
        String[] tempUlrs=new String[end-start+1];
        for (int i = 0; i < end -start+1; i++) {
            tempUlrs[i]=url;
          tempUlrs[i]= String.format(tempUlrs[i].replace("{}","%s"),Integer.toString(i+start) );
        }
      urlArray=tempUlrs;
    }
}
