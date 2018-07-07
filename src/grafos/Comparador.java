package grafos;

import java.util.Comparator;

public class Comparador implements Comparator<Aresta> {

    @Override
    public int compare(Aresta origem, Aresta destino) {
        if (origem.peso() > destino.peso()) {
            return 1;
        } else if (origem.peso() < destino.peso()) {
            return -1;
        } else {
            return 0;
        }
    }
}
