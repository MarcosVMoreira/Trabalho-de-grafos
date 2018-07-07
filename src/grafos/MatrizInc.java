package grafos;

import java.util.Collection;
import java.util.LinkedList;

public class MatrizInc implements Grafo {

    LinkedList<LinkedList<Aresta>> matrizInc = new LinkedList<LinkedList<Aresta>>();
    private MeusAlgoritmos objeto = new MeusAlgoritmos();

    public MatrizInc(int numVertice, int numAresta) {
        for (int i = 0; i < numVertice; i++) {
            this.matrizInc.add(new LinkedList<Aresta>());
        }
    }

    @Override
    public void adicionarAresta(Vertice origem, Vertice destino) throws Exception {

        if (origem != null && destino != null) {
            for (int i = 0; i < objeto.getListaVertice().size(); i++) {
                if (i == destino.id() && i == origem.id()) {
                    matrizInc.get(i).add(new Aresta(origem, destino, 1));
                } else if (i == origem.id()) {
                    matrizInc.get(i).add(new Aresta(origem, destino, -1));
                } else if (i == destino.id()) {
                    matrizInc.get(i).add(new Aresta(origem, destino, 1));
                } else {
                    matrizInc.get(i).add(new Aresta(origem, destino, Double.MAX_VALUE));
                }
            }
        } else {
            throw new Exception("Origem ou destino vazios.");
        }
    }

    @Override
    public void adicionarAresta(Vertice origem, Vertice destino, double peso) throws Exception {
        if (origem != null && destino != null) {
            for (int i = 0; i < objeto.getListaVertice().size(); i++) {
                if (i == destino.id() && i == origem.id()) {
                    matrizInc.get(i).add(new Aresta(origem, destino, peso));
                } else if (i == origem.id()) {
                    matrizInc.get(i).add(new Aresta(origem, destino, peso * (-1)));
                } else if (i == destino.id()) {
                    matrizInc.get(i).add(new Aresta(origem, destino, peso));
                } else {
                    matrizInc.get(i).add(new Aresta(origem, destino, Double.MAX_VALUE));
                }
            }
        } else {
            throw new Exception("Origem ou destino vazios.");
        }
    }

    @Override
    public boolean existeAresta(Vertice origem, Vertice destino) {
        for (int i = 0; i < matrizInc.get(origem.id()).size(); i++) {
            if (matrizInc.get(origem.id()).get(i).destino().id() == destino.id()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int grauDoVertice(Vertice vertice) throws Exception {
        int cont = 0;
        for (int i = 0; i < matrizInc.get(vertice.id()).size(); i++) {
            if (matrizInc.get(vertice.id()).get(i).peso() != Double.MAX_VALUE) {
                cont++;
            }
        }
        return cont;
    }

    @Override
    public int numeroDeVertices() {
        return matrizInc.size();
    }

    @Override
    public int numeroDeArestas() {
        int cont = 0;

        for (int i = 0; i < matrizInc.size(); i++) {
            for (int j = 0; j < matrizInc.get(i).size(); j++) {
                if (matrizInc.get(i).get(j).peso() != Double.MAX_VALUE && matrizInc.get(i).get(j).peso() > 0) {
                    cont++;
                }

            }
        }

        return cont;
    }

    @Override
    public Collection<Vertice> adjacentesDe(Vertice vertice) throws Exception {
        Collection<Vertice> colecao = new LinkedList<>();

        for (int j = 0; j < matrizInc.get(vertice.id()).size(); j++) {
            if ((matrizInc.get(vertice.id()).get(j).peso() != Double.MAX_VALUE && matrizInc.get(vertice.id()).get(j).peso() <= 0)) {
                colecao.add(new Vertice(matrizInc.get(vertice.id()).get(j).destino().id()));
            } else if ((matrizInc.get(vertice.id()).get(j).origem().id()
                    == matrizInc.get(vertice.id()).get(j).destino().id()) && (matrizInc.get(vertice.id()).get(j).peso() != Double.MAX_VALUE)) {
                colecao.add(new Vertice(matrizInc.get(vertice.id()).get(j).destino().id()));
            }
        }

        return colecao;
    }

    @Override
    public void setarPeso(Vertice origem, Vertice destino, double peso) throws Exception {
        int cont = 0;
        for (int i = 0; i < matrizInc.get(origem.id()).size(); i++) {
            if ((matrizInc.get(origem.id()).get(i).peso() * (-1)) == matrizInc.get(destino.id()).get(i).peso()) {

            } else if (origem.id() == destino.id()) {
                cont = 0;
                for (int j = 0; j < matrizInc.size(); j++) {
                    if (matrizInc.get(j).get(i).peso() != Double.MAX_VALUE) {
                        cont++;
                    }
                }
            }
        }

    }

    @Override
    public Collection<Vertice> vertices() {
        Collection<Vertice> colecao = new LinkedList<>();
        for (int i = 0; i < matrizInc.size(); i++) {
            colecao.add(new Vertice(i));
        }

        return colecao;

    }

}
