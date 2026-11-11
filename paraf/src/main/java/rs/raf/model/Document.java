
package rs.raf.model;

public abstract class Document extends Cvor {

    private String tekst;
    public static final String PROMENA_TEKSTA = "tekst";
    private static int brojacObicnihDokumenata = 0;
    private static int brojacKodDokumenata = 0;

    public static String novoImeObicnogDokumenta() {
        return "Document" + (++brojacObicnihDokumenata);
    }

    public static String novoImeKodDokumenta() {
        return "Code" + (++brojacKodDokumenata);
    }

    protected Document(String ime) {
        super(ime);
        this.tekst = "";
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        if (tekst == null) {
            throw new IllegalArgumentException("Tekst dokumenta ne moze biti null.");
        }

        String stariTekst = this.tekst;
        this.tekst = tekst;

        obavestiPromenu(PROMENA_TEKSTA, stariTekst, tekst);
    }

    public abstract boolean koristiMonospaceFont();

    public abstract boolean prelamajRedove();
}
