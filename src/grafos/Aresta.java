package grafos;

public class Aresta {

    private Vertice origem;
    private Vertice destino;
    private double peso;
    private String classificacao;
    
    public Aresta( Vertice origem, Vertice destino ){
        this.origem = origem;
        this.destino = destino;
        this.peso = 1;
    }
    
    public Aresta( Vertice origem, Vertice destino, double peso ){
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public Vertice origem() {
        return origem;
    }

    public void setarOrigem(Vertice origem) {
        this.origem = origem;
    }

    public Vertice destino() {
        return destino;
    }

    public void setarDestino(Vertice destino) {
        this.destino = destino;
    }

    public double peso() {
        return peso;
    }

    public void setarPeso(double peso) {
        this.peso = peso;
    }

    /**
     * @return the classificacao
     */
    public String getClassificacao() {
        return classificacao;
    }

    /**
     * @param classificacao the classificacao to set
     */
    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }
    
}
