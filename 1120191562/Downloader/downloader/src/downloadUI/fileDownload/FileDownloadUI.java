/*
 * Created by JFormDesigner on Sat Feb 12 11:23:27 CST 2022
 */

package downloadUI.fileDownload;

import downloadCore.DownloadControl;
import downloadUtil.FileContentReader;
import downloadUtil.GetClipbrd;
import downloadUtil.IfLegal;
import downloadUtil.UrlReader;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author unknown
 */
public class FileDownloadUI extends JFrame {
    public FileDownloadUI() {
        initComponents();
        fileNametextArea.setText(GetClipbrd.getClipbrd());
    }

    private void okButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        boolean flag;
        if(IfLegal.ifLegalFileName(fileNametextArea.getText())){
            System.out.println("进入okButtonMouseClicked");
            UrlReader urlReader = new UrlReader();
            urlReader.getFromFileUrls(fileNametextArea.getText());
            String[] urls= urlReader.getUrlArray();

            if (IfLegal.ifLegalUrls(urls)){
                label1.setText("正在下载！！！");
                DownloadControl downloadControl = new DownloadControl();
                flag= downloadControl.urlsRun(urls,FileContentReader.read("E:\\GitHub\\downloaderK\\1120191562\\Downloader\\downloader\\src\\setting\\settings.txt",1),Integer.parseInt(FileContentReader.read("E:\\GitHub\\downloaderK\\1120191562\\Downloader\\downloader\\src\\setting\\settings.txt",2)));

                if (flag){
                    JOptionPane.showMessageDialog(null, "下载完成");
                    label1.setText("没有下载任务");
                }
            }else {
                JOptionPane.showMessageDialog(null, "存在非法url，请重新输入");
            }

            label1.setText("没有下载任务");
        }else{
            JOptionPane.showMessageDialog(null, "文件路径不存在，请重新输入");
        }


    }

    private void cancelButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        this.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("downloadUI.main.main");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        fileNametextArea = new JTextArea();
        buttonBar = new JPanel();
        label1 = new JLabel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridLayout());

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(fileNametextArea);
                }
                contentPanel.add(scrollPane1);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- label1 ----
                label1.setText(bundle.getString("FileDownloadUI.label1.text"));
                buttonBar.add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- okButton ----
                okButton.setText(bundle.getString("FileDownloadUI.okButton.text"));
                okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        okButtonMouseClicked(e);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText(bundle.getString("FileDownloadUI.cancelButton.text"));
                cancelButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cancelButtonMouseClicked(e);
                    }
                });
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JTextArea fileNametextArea;
    private JPanel buttonBar;
    private JLabel label1;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
