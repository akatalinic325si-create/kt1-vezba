
package rs.raf.gui.swing;

import rs.raf.model.Cvor;
import rs.raf.model.Fascikla;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class ProjectTree extends JTree
        implements PropertyChangeListener {

    private final Cvor koren;
    private final DefaultTreeModel modelStabla;

    public ProjectTree(Cvor koren) {
        super(new DefaultTreeModel(napraviCvor(koren)));

        this.koren = koren;
        this.modelStabla = (DefaultTreeModel) getModel();

        setRootVisible(true);
        setShowsRootHandles(true);

        koren.dodajPosmatraca(this);

        expandRow(0);
    }

    private static DefaultMutableTreeNode napraviCvor(Cvor cvor) {
        DefaultMutableTreeNode prikaz =
                new DefaultMutableTreeNode(cvor);

        for (Cvor dete : cvor.getDeca()) {
            prikaz.add(napraviCvor(dete));
        }

        return prikaz;
    }

    public Cvor getIzabraniCvor() {
        Object izabrani = getLastSelectedPathComponent();

        if (izabrani instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode cvorPrikaza =
                    (DefaultMutableTreeNode) izabrani;

            return (Cvor) cvorPrikaza.getUserObject();
        }

        return null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!Fascikla.PROMENA_DECE.equals(evt.getPropertyName())) {
            return;
        }

        if (SwingUtilities.isEventDispatchThread()) {
            osveziStablo();
        } else {
            SwingUtilities.invokeLater(this::osveziStablo);
        }
    }

    private void osveziStablo() {
        Cvor prethodnoIzabran = getIzabraniCvor();

        List<Cvor> prosireni = new ArrayList<>();

        for (int i = 0; i < getRowCount(); i++) {
            TreePath putanja = getPathForRow(i);

            if (putanja != null && isExpanded(putanja)) {
                DefaultMutableTreeNode cvor =
                        (DefaultMutableTreeNode)
                                putanja.getLastPathComponent();

                prosireni.add((Cvor) cvor.getUserObject());
            }
        }

        DefaultMutableTreeNode noviKoren = napraviCvor(koren);
        modelStabla.setRoot(noviKoren);

        for (Cvor cvor : prosireni) {
            TreePath putanja = pronadjiPutanju(noviKoren, cvor);

            if (putanja != null) {
                expandPath(putanja);
            }
        }

        if (prethodnoIzabran != null) {
            TreePath putanja =
                    pronadjiPutanju(noviKoren, prethodnoIzabran);

            if (putanja != null) {
                setSelectionPath(putanja);
            }
        }

        expandRow(0);
    }

    private TreePath pronadjiPutanju(
            DefaultMutableTreeNode trenutni,
            Cvor trazeni) {

        if (trenutni.getUserObject() == trazeni) {
            return new TreePath(trenutni.getPath());
        }

        for (int i = 0; i < trenutni.getChildCount(); i++) {
            DefaultMutableTreeNode dete =
                    (DefaultMutableTreeNode) trenutni.getChildAt(i);

            TreePath pronadjena = pronadjiPutanju(dete, trazeni);

            if (pronadjena != null) {
                return pronadjena;
            }
        }

        return null;
    }
}
