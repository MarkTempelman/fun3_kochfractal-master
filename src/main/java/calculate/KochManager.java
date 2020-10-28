/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculate;

import java.util.ArrayList;
import fun3kochfractalfx.FUN3KochFractalFX;
import timeutil.TimeStamp;

/**
 *
 * @author Nico Kuijpers
 * Modified for FUN3 by Gertjan Schouten
 */
public class KochManager {
    
    //private KochFractal koch;
    private ArrayList<Edge> edges;
    private FUN3KochFractalFX application;
    private TimeStamp tsCalc;
    private TimeStamp tsDraw;
    public Integer count;
    private Runnable leftEdge;
    private Runnable rightEdge;
    private Runnable bottomEdge;
    Thread leftEdgeThread;
    Thread rightEdgeThread;
    Thread bottomEdgeThread;
    
    public KochManager(FUN3KochFractalFX application) {
        this.edges = new ArrayList<Edge>();
        //this.koch = new KochFractal(this);
        this.application = application;
        this.tsCalc = new TimeStamp();
        this.tsDraw = new TimeStamp();
    }
    
    public void changeLevel(int nxt) {
        count = 0;
        edges.clear();
        //koch.setLevel(nxt);
        tsCalc.init();
        tsCalc.setBegin("Begin calculating");
//        Thread leftEdgeThread = new Thread(leftEdgeRunnable);
//        Thread rightEdgeThread = new Thread(rightEdgeRunnable);
//        Thread bottomEdgeThread = new Thread(bottomEdgeRunnable);
//        leftEdgeThread.start();
//        rightEdgeThread.start();
//        bottomEdgeThread.start();
        leftEdge = new LeftEdge(this, nxt);
        rightEdge = new RightEdge(this, nxt);
        bottomEdge = new BottomEdge(this, nxt);
        leftEdgeThread = new Thread(leftEdge);
        rightEdgeThread = new Thread(rightEdge);
        bottomEdgeThread = new Thread(bottomEdge);
        leftEdgeThread.run();
        rightEdgeThread.run();
        bottomEdgeThread.run();

        while(count < 3){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
        tsCalc.setEnd("End calculating");
        application.setTextNrEdges("" + edges.size());
        application.setTextCalc(tsCalc.toString());
        drawEdges();
    }
    
    public void drawEdges() {
        tsDraw.init();
        tsDraw.setBegin("Begin drawing");
        application.clearKochPanel();
        for (Edge e : edges) {
            application.drawEdge(e);
        }
        tsDraw.setEnd("End drawing");
        application.setTextDraw(tsDraw.toString());
    }

    public synchronized void increaseCount(){
        count++;
    }
    
    public void addEdge(Edge e) {
        edges.add(e);
    }

    public synchronized void addEdges(ArrayList<Edge> edges){
        this.edges.addAll(edges);
    }
}
