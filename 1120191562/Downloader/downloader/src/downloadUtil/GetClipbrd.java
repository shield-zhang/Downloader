package downloadUtil;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class GetClipbrd {
    /**
     * 获取剪切板的内容
     * @return 剪切板内容
     */
    public static String getClipbrd() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 获取剪切板中的内容
        Transferable transferable = clipboard.getContents(null);

        if (transferable != null) {

            // 检查内容是否是文本类型
            if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    return (String) transferable.getTransferData(DataFlavor.stringFlavor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
