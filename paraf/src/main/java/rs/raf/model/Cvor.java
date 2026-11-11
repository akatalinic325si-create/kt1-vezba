
package rs.raf.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collections;
import java.util.List;

public abstract class Cvor implements Comparable<Cvor>{

    private final String ime;
    private Cvor roditelj;

    private final PropertyChangeSupport promene =
            new PropertyChangeSupport(this);

    protected Cvor(String ime) {
        if (ime == null || ime.isBlank()) {
            throw new IllegalArgumentException(
                    "Ime cvora ne moze biti prazno."
            );
        }

        this.ime = ime;
    }

    public String getIme() {
        return ime;
    }

    public Cvor getRoditelj() {
        return roditelj;
    }

    void setRoditelj(Cvor roditelj) {
        this.roditelj = roditelj;
    }

    public List<Cvor> getDeca() {
        return Collections.emptyList();
    }

    public void dodajPosmatraca(PropertyChangeListener posmatrac) {
        promene.addPropertyChangeListener(posmatrac);
    }

    public void ukloniPosmatraca(PropertyChangeListener posmatrac) {
        promene.removePropertyChangeListener(posmatrac);
    }

    protected final void obavestiPromenu(
            String naziv,
            Object staraVrednost,
            Object novaVrednost
    ) {
        promene.firePropertyChange(
                naziv,
                staraVrednost,
                novaVrednost
        );

        if (roditelj != null) {
            roditelj.obavestiPromenu(
                    naziv,
                    staraVrednost,
                    novaVrednost
            );
        }
    }

    @Override
    public String toString() {
        return ime;
    }

    @Override
    public int compareTo(Cvor o) {
        return this.getIme().compareTo(o.getIme());
    }
}
