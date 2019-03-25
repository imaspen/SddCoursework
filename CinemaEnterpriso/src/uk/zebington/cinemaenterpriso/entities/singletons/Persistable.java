package uk.zebington.cinemaenterpriso.entities.singletons;

import java.io.Serializable;

public interface Persistable extends Serializable {
    String getSavePath();
}
