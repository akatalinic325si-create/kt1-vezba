
package rs.raf.model;

public class CodeDocument extends Document {

    public CodeDocument(String ime) {
        super(ime);
    }

    public CodeDocument getDuplikat() {
        CodeDocument novi = new CodeDocument(this.getIme());
        novi.setTekst(this.getTekst());
        return novi;
    }

    public CodeDocument() {
        super(Document.novoImeKodDokumenta());
    }

    @Override
    public boolean koristiMonospaceFont() {
        return true;
    }

    @Override
    public boolean prelamajRedove() {
        return false;
    }
}
