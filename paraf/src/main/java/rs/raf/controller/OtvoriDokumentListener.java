package rs.raf.controller;

import rs.raf.gui.swing.MainFrame;
import rs.raf.model.Document;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OtvoriDokumentListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent event) {
        if (event.getButton() != MouseEvent.BUTTON1 || event.getClickCount() != 2) {
            return;
        }

        TreePath putanja = MainFrame.getInstance().getProjectTree()
                .getPathForLocation(event.getX(), event.getY());
        if (putanja == null) {
            return;
        }

        DefaultMutableTreeNode izabrani =
                (DefaultMutableTreeNode) putanja.getLastPathComponent();

        if (izabrani.getUserObject() instanceof Document dokument) {
            MainFrame.getInstance().getEditorPanel().otvoriDokument(dokument);
        }
    }
}