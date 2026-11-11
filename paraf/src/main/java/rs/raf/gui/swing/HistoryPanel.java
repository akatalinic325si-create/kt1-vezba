package rs.raf.gui.swing;

import rs.raf.model.Istorija;
import rs.raf.model.StavkaIstorije;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HistoryPanel extends JPanel
        implements PropertyChangeListener {

    private final DefaultListModel<StavkaIstorije> modelListe;
    private final JList<StavkaIstorije> lista;

    public HistoryPanel(Istorija istorija) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250, 150));

        modelListe = new DefaultListModel<>();
        lista = new JList<>(modelListe);

        JLabel naslov = new JLabel("History");

        add(naslov, BorderLayout.NORTH);
        add(new JScrollPane(lista), BorderLayout.CENTER);

        for (StavkaIstorije stavka : istorija.getStavke()) {
            modelListe.addElement(stavka);
        }

        istorija.dodajPosmatraca(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {

        if (!Istorija.NOVA_STAVKA.equals(
                event.getPropertyName())) {
            return;
        }

        if (!(event.getNewValue()
                instanceof StavkaIstorije novaStavka)) {
            return;
        }

        Runnable osvezi = () -> {
            modelListe.addElement(novaStavka);
            lista.ensureIndexIsVisible(
                    modelListe.getSize() - 1
            );
        };

        if (SwingUtilities.isEventDispatchThread()) {
            osvezi.run();
        } else {
            SwingUtilities.invokeLater(osvezi);
        }
    }
}
