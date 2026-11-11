package rs.raf.controller;

import rs.raf.core.ApplicationFramework;
import rs.raf.gui.swing.MainFrame;
import rs.raf.model.Document;
import rs.raf.model.Fascikla;
import rs.raf.model.PlainDocument;
import java.awt.event.ActionEvent;

public class NewDocumentAction extends ParafAbstractAction {

    public NewDocumentAction() {
        super("New Document", "Create a plain text document");
    }

    @Override
    protected void perform(ActionEvent event) {
        Fascikla roditelj = IzborFascikle.pronadjiRoditelja();
        Document dokument = new PlainDocument(
                Document.novoImeObicnogDokumenta()
        );

        roditelj.dodajDete(dokument);
        MainFrame.getInstance().getEditorPanel().otvoriDokument(dokument);
        ApplicationFramework.getInstance().getIstorija()
                .dodajStavku("Created \"" + dokument.getIme() + "\"");
    }
}