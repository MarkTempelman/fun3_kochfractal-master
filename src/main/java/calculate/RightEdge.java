package calculate;

import java.util.ArrayList;

public class RightEdge extends AbstractEdge{
    private KochManager manager;
    private KochFractal fractal;
    private int level;
    private ArrayList<Edge> edges = new ArrayList<>();

    public RightEdge(KochManager manager, int level){
        this.manager = manager;
        this.fractal = new KochFractal(this);
        this.level = level;
    }

    @Override
    public void run() {
        fractal.setLevel(level);
        fractal.generateRightEdge();
        manager.addEdges(edges);
        manager.increaseCount();
    }

    @Override
    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    @Override
    public ArrayList<Edge> getEdges() {
        return edges;
    }
}