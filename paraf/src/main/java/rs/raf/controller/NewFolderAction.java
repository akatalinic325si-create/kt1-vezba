package rs.raf.controller;

import rs.raf.core.ApplicationFramework;
import rs.raf.model.Fascikla;
import java.awt.event.ActionEvent;

public class NewFolderAction extends ParafAbstractAction {

    public NewFolderAction() {
        super("New Folder", "Create a folder");
    }

    @Override
    protected void perform(ActionEvent event) {
        Fascikla roditelj = IzborFascikle.pronadjiRoditelja();
        Fascikla fascikla = new Fascikla();

        roditelj.dodajDete(fascikla);
        ApplicationFramework.getInstance().getIstorija()
                .dodajStavku("Created folder \"" + fascikla.getIme() + "\"");
    }
}