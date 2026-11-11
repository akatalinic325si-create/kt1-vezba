package rs.raf.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Istorija {

    public static final String NOVA_STAVKA = "novaStavka";

    private final List<StavkaIstorije> stavke = new ArrayList<>();
    private final PropertyChangeSupport promene = new PropertyChangeSupport(this);

    public void dodajStavku(String tekst) {
        StavkaIstorije stavka = new StavkaIstorije(tekst);
        stavke.add(stavka);
        promene.firePropertyChange(NOVA_STAVKA, null, stavka);
    }

    public List<StavkaIstorije> getStavke() {
        return Collections.unmodifiableList(new ArrayList<>(stavke));
    }

    public void dodajPosmatraca(PropertyChangeListener posmatrac) {
        promene.addPropertyChangeListener(posmatrac);
    }

    public void ukloniPosmatraca(PropertyChangeListener posmatrac) {
        promene.removePropertyChangeListener(posmatrac);
    }
}