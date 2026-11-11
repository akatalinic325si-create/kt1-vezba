package rs.raf.gui.swing;

import rs.raf.model.Document;
import rs.raf.controller.DocumentContentListener;
import javax.swing.*;
import java.awt.*;
import java.util.IdentityHashMap;
import java.util.Map;

public class EditorPanel extends JPanel {

    private static final int TEXT_MARGIN = 8;
    private static final int FONT_SIZE = 14;

    private final JTabbedPane tabovi;

    private final Map<Document, JScrollPane> otvoreniDokumenti;

    public EditorPanel() {
        setLayout(new BorderLayout());

        tabovi = new JTabbedPane();
        otvoreniDokumenti = new IdentityHashMap<>();

        add(tabovi, BorderLayout.CENTER);
    }

    public void otvoriDokument(Document dokument) {

        if (dokument == null) {
            throw new IllegalArgumentException("Dokument ne moze biti null.");
        }

        JScrollPane postojeciTab =
                otvoreniDokumenti.get(dokument);

        if (postojeciTab != null) {
            tabovi.setSelectedComponent(postojeciTab);
            return;
        }

        JTextArea textArea = napraviPoljeZaTekst(dokument);

        textArea.getDocument().addDocumentListener(
                new DocumentContentListener(dokument, textArea)
        );

        JScrollPane noviTab = new JScrollPane(textArea);

        otvoreniDokumenti.put(dokument, noviTab);

        tabovi.addTab(dokument.getIme(), noviTab);
        tabovi.setSelectedComponent(noviTab);

        dokument.dodajPosmatraca(event -> {

            if (!Document.PROMENA_TEKSTA.equals(
                    event.getPropertyName())) {
                return;
            }

            Runnable osvezi = () -> {
                String noviTekst = dokument.getTekst();

                if (!textArea.getText().equals(noviTekst)) {
                    textArea.setText(noviTekst);
                }
            };

            if (SwingUtilities.isEventDispatchThread()) {
                osvezi.run();
            } else {
                SwingUtilities.invokeLater(osvezi);
            }
        });
    }

    private JTextArea napraviPoljeZaTekst(Document dokument) {

        JTextArea textArea =
                new JTextArea(dokument.getTekst());

        textArea.setLineWrap(dokument.prelamajRedove());
        textArea.setWrapStyleWord(true);

        String nazivFonta = dokument.koristiMonospaceFont()
                ? Font.MONOSPACED
                : Font.SANS_SERIF;

        textArea.setFont(
                new Font(nazivFonta, Font.PLAIN, FONT_SIZE)
        );

        textArea.setBorder(
                BorderFactory.createEmptyBorder(
                        TEXT_MARGIN, TEXT_MARGIN,
                        TEXT_MARGIN, TEXT_MARGIN
                )
        );

        return textArea;
    }

    public JTextArea getPoljeZaTekst(Document dokument) {

        JScrollPane tab = otvoreniDokumenti.get(dokument);

        if (tab == null) {
            return null;
        }

        return (JTextArea) tab.getViewport().getView();
    }
}
