package rs.raf;


import rs.raf.core.ApplicationFramework;
import javax.swing.SwingUtilities;

public class AppCore {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ApplicationFramework.getInstance().initialize();
        });
    }
}

