package rs.raf.controller;

import rs.raf.core.ApplicationFramework;
import rs.raf.gui.swing.MainFrame;
import rs.raf.model.Cvor;
import rs.raf.model.Document;
import rs.raf.model.Fascikla;
import java.awt.event.ActionEvent;

public class DuplicateDocumentAction extends ParafAbstractAction {

    public DuplicateDocumentAction() {
        super("Duplicate Document", "Create a duplicate plain text document");
    }

    @Override
    protected void perform(ActionEvent event) {
        Fascikla roditelj = IzborFascikle.pronadjiRoditelja();
        Cvor izabraniCvor = MainFrame.getInstance().getProjectTree().getIzabraniCvor();
        if (izabraniCvor instanceof Document izabraniDocument) {
            Document dokument = izabraniDocument.getDuplikat();

            roditelj.dodajDete(dokument);
            MainFrame.getInstance().getEditorPanel().otvoriDokument(dokument);
            ApplicationFramework.getInstance().getIstorija()
                    .dodajStavku("Duplicated \"" + dokument.getIme() + "\"");
        }
    }
}