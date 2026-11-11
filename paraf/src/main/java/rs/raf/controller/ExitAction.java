package rs.raf.controller;

import java.awt.event.ActionEvent;

public class ExitAction extends ParafAbstractAction {

    public ExitAction() {
        super("Exit", "Izlaz iz aplikacije");
    }

    @Override
    protected void perform(ActionEvent event) {
        System.exit(0);
    }
}
