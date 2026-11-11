package rs.raf.model;

public class PlainDocument extends Document {

    public PlainDocument(String ime) {
        super(ime);
    }
    public PlainDocument() {
        super(Document.novoImeObicnogDokumenta());
    }

    public PlainDocument getDuplikat() {
        PlainDocument novi = new PlainDocument(this.getIme());
        novi.setTekst(this.getTekst());
        return novi;
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