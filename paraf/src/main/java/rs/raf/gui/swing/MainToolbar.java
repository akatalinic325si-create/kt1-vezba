package rs.raf.gui.swing;

import javax.swing.Action;
import javax.swing.JToolBar;

public class MainToolbar extends JToolBar {

    public MainToolbar(
            Action newDocumentAction,
            Action newCodeDocumentAction,
            Action duplicateDocumentAction,
            Action newFolderAction,
            Action exitAction) {

        setFloatable(false);

        add(newDocumentAction);
        add(newCodeDocumentAction);
        add(duplicateDocumentAction);
        add(newFolderAction);


        addSeparator();

        add(exitAction);
    }
}
