package rs.raf.gui.swing;

import javax.swing.Action;
import javax.swing.JToolBar;

public class MainToolbar extends JToolBar {

    public MainToolbar(
            Action newDocumentAction,
            Action newCodeDocumentAction,
            Action newFolderAction,
            Action exitAction) {

        setFloatable(false);

        add(newDocumentAction);
        add(newCodeDocumentAction);
        add(newFolderAction);

        addSeparator();

        add(exitAction);
    }
}
