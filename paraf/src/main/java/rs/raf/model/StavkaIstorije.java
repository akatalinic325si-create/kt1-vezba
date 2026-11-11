
package rs.raf.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StavkaIstorije {

    private final LocalDateTime vreme;
    private final String tekst;

    public StavkaIstorije(String tekst) {
        if (tekst == null || tekst.isBlank()) {
            throw new IllegalArgumentException(
                    "Tekst istorije ne moze biti prazan."
            );
        }

        this.vreme = LocalDateTime.now();
        this.tekst = tekst;
    }

    public LocalDateTime getVreme() {
        return vreme;
    }

    public String getTekst() {
        return tekst;
    }

    @Override
    public String toString() {
        DateTimeFormatter format =
                DateTimeFormatter.ofPattern("HH:mm:ss");

        return "[" + vreme.format(format) + "] " + tekst;
    }
}
