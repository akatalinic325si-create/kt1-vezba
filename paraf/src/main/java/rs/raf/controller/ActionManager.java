package rs.raf.controller;

public class ActionManager {

    private static final ActionManager instance = new ActionManager();

    private final NewDocumentAction newDocumentAction = new NewDocumentAction();
    private final NewCodeDocumentAction newCodeDocumentAction = new NewCodeDocumentAction();
    private final NewFolderAction newFolderAction = new NewFolderAction();
    private final ExitAction exitAction = new ExitAction();

    private ActionManager() {
    }

    public static ActionManager getInstance() {
        return instance;
    }

    public NewDocumentAction getNewDocumentAction() {
        return newDocumentAction;
    }

    public NewCodeDocumentAction getNewCodeDocumentAction() {
        return newCodeDocumentAction;
    }

    public NewFolderAction getNewFolderAction() {
        return newFolderAction;
    }

    public ExitAction getExitAction() {
        return exitAction;
    }
}