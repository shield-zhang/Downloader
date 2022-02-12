/*
 * Created by JFormDesigner on Sat Feb 12 10:29:04 CST 2022
 */

package downloadUI.main;

import downloadUI.fileDownload.FileDownloadUI;
import downloadUI.regexUrlDownload.RegexUrlDownloadUI;
import downloadUI.urlsDownload.UrlsDownloadUI;
import downloadUtil.FileContentReader;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class MainUI extends JFrame {
    public MainUI() {
        initComponents();
        writeTextField();
    }

    private void urlsButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        UrlsDownloadUI urlsDownloadUI=new UrlsDownloadUI();
        urlsDownloadUI.setVisible(true);
    }

    private void fileNameButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        FileDownloadUI fileDownloadUI=new FileDownloadUI();
        fileDownloadUI.setVisible(true);
        // TODO add your code here
    }

    private void regexUrlButtonMouseClicked(MouseEvent e) {
        RegexUrlDownloadUI regexUrlDownloadUI=new RegexUrlDownloadUI();
        regexUrlDownloadUI.setVisible(true);
        // TODO add your code here
    }

    private void saveSettingsButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        System.out.println(savePathTextArea.getText());
        FileContentReader.write("E:\\GitHub\\downloaderK\\1120191562\\Downloader\\downloader\\src\\setting\\settings.txt",savePathTextArea.getText(),threadNumTextField.getText());

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("downloadUI.main.main");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        panel3 = new JPanel();
        urlsButton = new JButton();
        fileNameButton = new JButton();
        regexUrlButton = new JButton();
        panel4 = new JPanel();
        panel7 = new JPanel();
        panel8 = new JPanel();
        label2 = new JLabel();
        threadNumTextField = new JTextField();
        panel9 = new JPanel();
        panel12 = new JPanel();
        panel13 = new JPanel();
        label1 = new JLabel();
        savePathTextArea = new JTextArea();
        panel10 = new JPanel();
        panel11 = new JPanel();
        saveSettingsButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridLayout(3, 1));

                //======== panel1 ========
                {
                    panel1.setLayout(new GridLayout(2, 1));

                    //======== panel3 ========
                    {
                        panel3.setLayout(new GridLayout(1, 7));

                        //---- urlsButton ----
                        urlsButton.setText(bundle.getString("MainUI.urlsButton.text"));
                        urlsButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                urlsButtonMouseClicked(e);
                            }
                        });
                        panel3.add(urlsButton);

                        //---- fileNameButton ----
                        fileNameButton.setText(bundle.getString("MainUI.fileNameButton.text"));
                        fileNameButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                fileNameButtonMouseClicked(e);
                            }
                        });
                        panel3.add(fileNameButton);

                        //---- regexUrlButton ----
                        regexUrlButton.setText(bundle.getString("MainUI.regexUrlButton.text"));
                        regexUrlButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                regexUrlButtonMouseClicked(e);
                            }
                        });
                        panel3.add(regexUrlButton);
                    }
                    panel1.add(panel3);
                }
                contentPanel.add(panel1);

                //======== panel4 ========
                {
                    panel4.setLayout(new GridLayout(2, 1));

                    //======== panel7 ========
                    {
                        panel7.setLayout(new GridLayout(1, 1));
                    }
                    panel4.add(panel7);

                    //======== panel8 ========
                    {
                        panel8.setLayout(new GridLayout(1, 2));

                        //---- label2 ----
                        label2.setText(bundle.getString("MainUI.label2.text"));
                        panel8.add(label2);

                        //---- threadNumTextField ----
                        threadNumTextField.setText(bundle.getString("MainUI.threadNumTextField.text"));
                        panel8.add(threadNumTextField);
                    }
                    panel4.add(panel8);
                }
                contentPanel.add(panel4);

                //======== panel9 ========
                {
                    panel9.setLayout(new GridLayout(2, 1));

                    //======== panel12 ========
                    {
                        panel12.setLayout(new GridLayout(1, 1));

                        //======== panel13 ========
                        {
                            panel13.setLayout(new GridLayout(1, 2));

                            //---- label1 ----
                            label1.setText(bundle.getString("MainUI.label1.text"));
                            panel13.add(label1);

                            //---- savePathTextArea ----
                            savePathTextArea.setPreferredSize(new Dimension(3, 15));
                            savePathTextArea.setText(bundle.getString("MainUI.savePathTextArea.text"));
                            panel13.add(savePathTextArea);
                        }
                        panel12.add(panel13);
                    }
                    panel9.add(panel12);

                    //======== panel10 ========
                    {
                        panel10.setLayout(new GridLayout(1, 2));

                        //======== panel11 ========
                        {
                            panel11.setLayout(new GridLayout(1, 1));
                        }
                        panel10.add(panel11);

                        //---- saveSettingsButton ----
                        saveSettingsButton.setText(bundle.getString("MainUI.saveSettingsButton.text"));
                        saveSettingsButton.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                saveSettingsButtonMouseClicked(e);
                            }
                        });
                        panel10.add(saveSettingsButton);
                    }
                    panel9.add(panel10);
                }
                contentPanel.add(panel9);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

    }
    private void writeTextField(){
        System.out.println(System.getProperty("user.dir"));

        String fileName="E:\\GitHub\\downloaderK\\1120191562\\Downloader\\downloader\\src\\setting\\settings.txt";

            //向文本框写入设置数据
            String str= FileContentReader.read(fileName,1);
            System.out.println(str);
            savePathTextArea.setText(str);
            str= FileContentReader.read(fileName,2);
            threadNumTextField.setText(str);



    }
    private void fileNameButtonMouseDragged(MouseEvent e) {
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel panel1;
    private JPanel panel3;
    private JButton urlsButton;
    private JButton fileNameButton;
    private JButton regexUrlButton;
    private JPanel panel4;
    private JPanel panel7;
    private JPanel panel8;
    private JLabel label2;
    private JTextField threadNumTextField;
    private JPanel panel9;
    private JPanel panel12;
    private JPanel panel13;
    private JLabel label1;
    private JTextArea savePathTextArea;
    private JPanel panel10;
    private JPanel panel11;
    private JButton saveSettingsButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
