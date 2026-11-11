package rs.raf.controller;

import rs.raf.core.ApplicationFramework;
import rs.raf.gui.swing.MainFrame;
import rs.raf.model.Document;
import rs.raf.model.Fascikla;
import rs.raf.model.PlainDocument;
import java.awt.event.ActionEvent;

public class DuplicateDocumentAction extends ParafAbstractAction {

    public DuplicateDocumentAction() {
        super("Duplicate Document", "Create a duplicate plain text document");
    }

    @Override
    protected void perform(ActionEvent event) {
        Fascikla roditelj = IzborFascikle.pronadjiRoditelja();
        Cvor izabraniDocument = MainFrame.getProjectTree().getIzabraniCvor();
        Document dokument = new PlainDocument(
                izabraniDocument.getIme();

        );
        dokument.setText(izabraniDocument.getText());

        roditelj.dodajDete(dokument);
        MainFrame.getInstance().getEditorPanel().otvoriDokument(dokument);
        ApplicationFramework.getInstance().getIstorija()
                .dodajStavku("duplicated \"" + dokument.getIme() + "\"");
    }
}