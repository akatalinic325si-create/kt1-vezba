package rs.raf.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public abstract class ParafAbstractAction extends AbstractAction {

    protected ParafAbstractAction(String ime, String opis) {
        putValue(Action.NAME, ime);
        putValue(Action.SHORT_DESCRIPTION, opis);
    }

    @Override
    public final void actionPerformed(ActionEvent event) {
        try {
            perform(event);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage() != null
                            ? e.getMessage()
                            : "Doslo je do greske.",
                    "Greska",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    protected abstract void perform(ActionEvent event);
}

