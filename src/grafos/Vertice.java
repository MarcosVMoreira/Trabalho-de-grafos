package grafos;

public class Vertice {
    private int vertice;
    
    public Vertice( int v ){
        this.vertice = v;
    }

    public int id() {
        return getVertice();
    }

    public void setarVertice(int vertice) {
        this.setVertice(vertice);
    }

    /**
     * @return the vertice
     */
    public int getVertice() {
        return vertice;
    }

    /**
     * @param vertice the vertice to set
     */
    public void setVertice(int vertice) {
        this.vertice = vertice;
    }
    
}
