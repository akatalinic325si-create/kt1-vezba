package rs.raf.controller;

import rs.raf.model.Document;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DocumentContentListener implements DocumentListener {

    private final Document dokument;
    private final JTextArea textArea;

    public DocumentContentListener(Document dokument, JTextArea textArea) {
        this.dokument = dokument;
        this.textArea = textArea;
    }

    @Override
    public void insertUpdate(DocumentEvent event) {
        azurirajModel();
    }

    @Override
    public void removeUpdate(DocumentEvent event) {
        azurirajModel();
    }

    @Override
    public void changedUpdate(DocumentEvent event) {
        azurirajModel();
    }

    private void azurirajModel() {
        String noviTekst = textArea.getText();
        if (!noviTekst.equals(dokument.getTekst())) {
            dokument.setTekst(noviTekst);
        }
    }
}