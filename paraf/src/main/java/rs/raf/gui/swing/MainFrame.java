package rs.raf.gui.swing;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JSplitPane;
import rs.raf.core.ApplicationFramework;
import javax.swing.Action;

public class MainFrame extends JFrame {

    private static MainFrame instance;
    public static final String TITLE = "Paraf";

    private static final String FILE_MENU_TITLE = "File";
    private static final String EXIT_ITEM_TITLE = "Exit";

    private final ProjectTree projectTree;
    private final EditorPanel editorPanel;
    private final HistoryPanel historyPanel;
    private MainToolbar mainToolbar;

    private MainFrame() {
        setTitle(TITLE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        editorPanel = new EditorPanel();

        historyPanel = new HistoryPanel(ApplicationFramework.getInstance().getIstorija());

        projectTree = new ProjectTree(ApplicationFramework.getInstance().getProjekat());

        JScrollPane treeScroll = new JScrollPane(projectTree);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                treeScroll,
                editorPanel
        );

        splitPane.setDividerLocation(230);

        add(splitPane, BorderLayout.CENTER);
        add(historyPanel, BorderLayout.EAST);

    }


    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }

        return instance;
    }


    public EditorPanel getEditorPanel() {
        return editorPanel;
    }
    public ProjectTree getProjectTree() {
        return projectTree;
    }


    public void postaviAkcije(
            Action newDocumentAction,
            Action newCodeDocumentAction,
            Action duplicateDocumentAction,
            Action newFolderAction,
            Action exitAction) {

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        fileMenu.add(newDocumentAction);
        fileMenu.add(newCodeDocumentAction);
        fileMenu.add(duplicateDocumentAction);
        fileMenu.add(newFolderAction);

        fileMenu.addSeparator();
        fileMenu.add(exitAction);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        if (mainToolbar != null) {
            remove(mainToolbar);
        }

        mainToolbar = new MainToolbar(
                newDocumentAction,
                newCodeDocumentAction,
                duplicateDocumentAction,
                newFolderAction,
                exitAction
        );

        add(mainToolbar, BorderLayout.NORTH);

        revalidate();
        repaint();
    }

}
