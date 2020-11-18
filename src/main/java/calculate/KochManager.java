/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculate;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fun3kochfractalfx.FUN3KochFractalFX;
import interfaces.Observer;
import javafx.scene.control.ProgressBar;
import timeutil.TimeStamp;

/**
 *
 * @author Nico Kuijpers
 * Modified for FUN3 by Gertjan Schouten
 */
public class KochManager implements Observer {
    
    //private KochFractal koch;
    private ArrayList<Edge> edges;
    private FUN3KochFractalFX application;
    private TimeStamp tsCalc;
    private TimeStamp tsDraw;
    private Integer count;
    private LeftEdge leftEdge;
    private RightEdge rightEdge;
    private BottomEdge bottomEdge;
//    Thread leftEdgeThread;
//    Thread rightEdgeThread;
//    Thread bottomEdgeThread;
    ExecutorService pool = Executors.newFixedThreadPool(3);
    
    public KochManager(FUN3KochFractalFX application) {
        this.edges = new ArrayList<Edge>();
        //this.koch = new KochFractal(this);
        this.application = application;
        this.tsCalc = new TimeStamp();
        this.tsDraw = new TimeStamp();
    }

    public void endThreads(){
        pool.shutdown();
    }

    private void createEdges(int nxt){
        leftEdge = new LeftEdge(this, nxt);
        rightEdge = new RightEdge(this, nxt);
        bottomEdge = new BottomEdge(this, nxt);
    }

    private void calculateEdges(){
        count = 0;
        pool.submit(leftEdge);
        pool.submit(rightEdge);
        pool.submit(bottomEdge);
    }

    private void bindTasksToProgress(){
        ProgressBar firstProgress = application.getFirstProgress();
        ProgressBar secondProgress = application.getSecondProgress();
        ProgressBar thirdProgress = application.getThirdProgress();
        firstProgress.progressProperty().bind(leftEdge.progressProperty());
        secondProgress.progressProperty().bind(rightEdge.progressProperty());
        thirdProgress.progressProperty().bind(bottomEdge.progressProperty());
    }
    
    public void changeLevel(int nxt) {
        count = 0;
        edges.clear();
        //koch.setLevel(nxt);
        tsCalc.init();
        tsCalc.setBegin("Begin calculating");
        createEdges(nxt);
        pool = Executors.newFixedThreadPool(3);
        bindTasksToProgress();
        calculateEdges();
        while (count < 3) {
            try {
                Thread.sleep(0);
            } catch(InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        tsCalc.setEnd("End calculating");
        application.setTextNrEdges("" + edges.size());
        application.setTextCalc(tsCalc.toString());
        application.requestDrawEdges();
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

    @Override
    public synchronized void update(ArrayList<Edge> objects) {
        edges.addAll(objects);
        count++;
    }
}
