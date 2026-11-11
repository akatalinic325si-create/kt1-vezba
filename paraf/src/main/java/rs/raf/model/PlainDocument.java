package rs.raf.model;

public class PlainDocument extends Document {

    public PlainDocument(String ime) {
        super(ime);
    }
    public PlainDocument() {
        super(Document.novoImeObicnogDokumenta());
    }

    @Override
    public boolean koristiMonospaceFont() {
        return false;
    }

    @Override
    public boolean prelamajRedove() {
        return true;
    }
}