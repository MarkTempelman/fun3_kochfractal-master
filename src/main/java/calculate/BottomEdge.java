package calculate;

import java.util.ArrayList;

public class BottomEdge extends AbstractEdge{
    private KochManager manager;
    private KochFractal fractal;
    private int level;
    private ArrayList<Edge> edges = new ArrayList<>();

    public BottomEdge(KochManager manager, int level){
        this.manager = manager;
        this.fractal = new KochFractal();
        this.level = level;
        fractal.attachObserver(manager);
    }

    @Override
    public void run() {
        fractal.setLevel(level);
        fractal.generateBottomEdge();
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
