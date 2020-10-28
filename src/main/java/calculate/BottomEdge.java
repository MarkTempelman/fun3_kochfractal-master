package calculate;

import java.util.ArrayList;

public class BottomEdge implements Runnable{
    private KochManager manager;
    private KochFractal fractal;
    private int level;

    public BottomEdge(KochManager manager, int level){
        this.manager = manager;
        this.fractal = new KochFractal();
        this.level = level;
    }

    @Override
    public void run() {
        fractal.setLevel(level);
        fractal.generateBottomEdge();

        manager.addEdges(fractal.getEdges());
        manager.count++;
    }
}
