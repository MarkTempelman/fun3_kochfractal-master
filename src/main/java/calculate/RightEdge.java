package calculate;

import java.util.ArrayList;

public class RightEdge implements Runnable{
    private KochManager manager;
    private KochFractal fractal;
    private int level;

    public RightEdge(KochManager manager, int level){
        this.manager = manager;
        this.fractal = new KochFractal();
        this.level = level;
    }

    @Override
    public void run() {
        fractal.setLevel(level);
        fractal.generateRightEdge();

        manager.addEdges(fractal.getEdges());
        manager.count++;
    }
}