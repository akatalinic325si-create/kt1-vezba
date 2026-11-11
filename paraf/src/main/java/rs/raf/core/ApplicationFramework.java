
package rs.raf.core;

import rs.raf.controller.ActionManager;
import rs.raf.controller.OtvoriDokumentListener;
import rs.raf.gui.swing.MainFrame;
import rs.raf.model.Projekat;
import rs.raf.model.Istorija;

public class ApplicationFramework {

    private boolean controllerPovezan = false;

    private static final ApplicationFramework instance = new ApplicationFramework();

    private final Projekat projekat = new Projekat();

    private final Istorija istorija = new Istorija();

    private ApplicationFramework() {
    }

    public static ApplicationFramework getInstance() {
        return instance;
    }

    public Projekat getProjekat() {
        return projekat;
    }

    public Istorija getIstorija() {
        return istorija;
    }

    public void initialize() {
        MainFrame prozor = MainFrame.getInstance();

        if (!controllerPovezan) {
            ActionManager akcije = ActionManager.getInstance();

            prozor.postaviAkcije(
                    akcije.getNewDocumentAction(),
                    akcije.getNewCodeDocumentAction(),
                    akcije.getNewFolderAction(),
                    akcije.getExitAction()
            );

            prozor.getProjectTree().addMouseListener(
                    new OtvoriDokumentListener()
            );

            controllerPovezan = true;
        }

        prozor.setVisible(true);
    }
}


