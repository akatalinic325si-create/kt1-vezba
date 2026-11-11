
package rs.raf.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fascikla extends Cvor {

    private final List<Cvor> deca;
    private static int brojacFascikli = 0;
    public static final String PROMENA_DECE = "deca";

    public Fascikla(String ime) {
        super(ime);
        this.deca = new ArrayList<>();
    }
    public Fascikla() {
        this("Folder" + (++brojacFascikli));
    }

    @Override
    public List<Cvor> getDeca () {
        return Collections.unmodifiableList(deca);
    }

    public void dodajDete (Cvor dete) {
        if (dete == null) {
            throw new IllegalArgumentException("Dete ne moze biti null.");
        }

        if (dete instanceof Projekat) {
            throw new IllegalArgumentException(
                    "Projekat ne moze biti dodat u fasciklu."
            );
        }

        if (dete.getRoditelj() != null) {
            throw new IllegalArgumentException(
                    "Cvor vec pripada drugoj fascikli."
            );
        }

        Cvor current = this;

        while (current != null) {
            if (current == dete) {
                throw new IllegalArgumentException(
                        "Dete ne moze biti medju svojim roditeljima."
                );
            }

            current = current.getRoditelj();
        }

        deca.add(dete);
        dete.setRoditelj(this);
        obavestiPromenu(PROMENA_DECE, null, dete);
    }

    public void obrisiDete(Cvor dete) {
        if (deca.remove(dete)) {
            dete.setRoditelj(null);
            obavestiPromenu(PROMENA_DECE, dete, null);
        }
    }

    public int brojDokumenata(){
        int brojac = 0;
        for(Cvor dete: getDeca()){
            if(dete instanceof Fascikla fascikla){
                brojac+=fascikla.brojDokumenata();
            }
            else{
                brojac++;
            }
        }
        return brojac;
    }
}
