/*
 * Created by JFormDesigner on Sat Feb 12 11:24:23 CST 2022
 */

package downloadUI.regexUrlDownload;

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
public class RegexUrlDownloadUI extends JFrame {
    public RegexUrlDownloadUI() {
        initComponents();
        RegexUrltextArea.setText(GetClipbrd.getClipbrd());
    }

    boolean flag=true;
    private void okButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        UrlReader urlReader=new UrlReader();
        urlReader.getFromRegexUrl(RegexUrltextArea.getText(),Integer.parseInt(startTextField.getText()) ,Integer.parseInt(endTextField.getText()));
        String[] urls= urlReader.getUrlArray();
        if (IfLegal.ifLegalUrls(urls)){
            statusLabel.setText("正在下载！！！");
            DownloadControl downloadControl = new DownloadControl();
            flag= downloadControl.urlsRun(urls, FileContentReader.read("E:\\GitHub\\downloaderK\\1120191562\\Downloader\\downloader\\src\\setting\\settings.txt",1),Integer.parseInt(FileContentReader.read("E:\\GitHub\\downloaderK\\1120191562\\Downloader\\downloader\\src\\setting\\settings.txt",2)));
            if (flag){
                JOptionPane.showMessageDialog(null, "下载完成");
                statusLabel.setText("没有下载任务");
            }
            statusLabel.setText("没有下载任务");
        }else{
            JOptionPane.showMessageDialog(null, "存在非法链接，请重新输入");

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
        panel3 = new JPanel();
        label1 = new JLabel();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        RegexUrltextArea = new JTextArea();
        panel2 = new JPanel();
        label2 = new JLabel();
        startTextField = new JTextField();
        label3 = new JLabel();
        endTextField = new JTextField();
        buttonBar = new JPanel();
        statusLabel = new JLabel();
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
                contentPanel.setLayout(new GridLayout(3, 2));

                //======== panel3 ========
                {
                    panel3.setLayout(new GridLayout(1, 1));

                    //---- label1 ----
                    label1.setText(bundle.getString("RegexUrlDownloadUI.label1.text"));
                    panel3.add(label1);
                }
                contentPanel.add(panel3);

                //======== panel1 ========
                {
                    panel1.setLayout(new GridLayout(1, 1));

                    //======== scrollPane1 ========
                    {
                        scrollPane1.setViewportView(RegexUrltextArea);
                    }
                    panel1.add(scrollPane1);
                }
                contentPanel.add(panel1);

                //======== panel2 ========
                {
                    panel2.setLayout(new GridLayout(1, 4));

                    //---- label2 ----
                    label2.setText(bundle.getString("RegexUrlDownloadUI.label2.text"));
                    panel2.add(label2);
                    panel2.add(startTextField);

                    //---- label3 ----
                    label3.setText(bundle.getString("RegexUrlDownloadUI.label3.text"));
                    panel2.add(label3);
                    panel2.add(endTextField);
                }
                contentPanel.add(panel2);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- statusLabel ----
                statusLabel.setText(bundle.getString("RegexUrlDownloadUI.statusLabel.text"));
                buttonBar.add(statusLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- okButton ----
                okButton.setText(bundle.getString("RegexUrlDownloadUI.okButton.text"));
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
                cancelButton.setText(bundle.getString("RegexUrlDownloadUI.cancelButton.text"));
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
    private JPanel panel3;
    private JLabel label1;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTextArea RegexUrltextArea;
    private JPanel panel2;
    private JLabel label2;
    private JTextField startTextField;
    private JLabel label3;
    private JTextField endTextField;
    private JPanel buttonBar;
    private JLabel statusLabel;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
