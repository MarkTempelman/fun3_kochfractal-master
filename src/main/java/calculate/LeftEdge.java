package calculate;

import java.util.ArrayList;

public class LeftEdge extends AbstractEdge{
    private KochManager manager;
    private KochFractal fractal;
    private int level;
    private ArrayList<Edge> edges = new ArrayList<>();

    public LeftEdge(KochManager manager, int level){
        this.manager = manager;
        this.fractal = new KochFractal(this);
        this.level = level;
    }

    @Override
    public void run() {
        fractal.setLevel(level);
        fractal.generateLeftEdge();
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