package rs.raf.controller;

import rs.raf.core.ApplicationFramework;
import rs.raf.gui.swing.MainFrame;
import rs.raf.model.Document;
import rs.raf.model.Fascikla;
import rs.raf.model.CodeDocument;
import java.awt.event.ActionEvent;

public class NewCodeDocumentAction extends ParafAbstractAction {

    public NewCodeDocumentAction() {
        super("New Code Document", "Create a code document");
    }

    @Override
    protected void perform(ActionEvent event) {
        Fascikla roditelj = IzborFascikle.pronadjiRoditelja();
        Document dokument = new CodeDocument(
                Document.novoImeKodDokumenta()
        );

        roditelj.dodajDete(dokument);
        MainFrame.getInstance().getEditorPanel().otvoriDokument(dokument);
        ApplicationFramework.getInstance().getIstorija()
                .dodajStavku("Created \"" + dokument.getIme() + "\"");
    }
}