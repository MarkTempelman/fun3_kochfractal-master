package calculate;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class LeftEdge extends Task{
    private KochManager manager;
    private KochFractal fractal;
    private int level;
    private ArrayList<Edge> edges = new ArrayList<>();

    public LeftEdge(KochManager manager, int level){
        this.manager = manager;
        this.fractal = new KochFractal();
        this.level = level;
        fractal.attachObserver(manager);
    }

    @Override
    protected ArrayList<Edge> call() throws Exception {
        fractal.setLevel(level);
        fractal.generateLeftEdge();
        return null;
    }
}