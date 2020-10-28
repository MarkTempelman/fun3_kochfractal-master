package calculate;

import java.util.ArrayList;

public abstract class AbstractEdge implements Runnable{

    @Override
    public abstract void run();

    public abstract void addEdge(Edge edge);

    public abstract ArrayList<Edge> getEdges();
}
