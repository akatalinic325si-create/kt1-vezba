
package rs.raf.controller;

import rs.raf.core.ApplicationFramework;
import rs.raf.gui.swing.MainFrame;
import rs.raf.model.Cvor;
import rs.raf.model.Fascikla;

final class IzborFascikle {

    private IzborFascikle() {
    }

    static Fascikla pronadjiRoditelja() {
        Cvor izabrani = MainFrame.getInstance()
                .getProjectTree().getIzabraniCvor();

        if (izabrani instanceof Fascikla fascikla) {
            return fascikla;
        }

        if (izabrani != null
                && izabrani.getRoditelj() instanceof Fascikla fascikla) {
            return fascikla;
        }

        return ApplicationFramework.getInstance().getProjekat();
    }
}
