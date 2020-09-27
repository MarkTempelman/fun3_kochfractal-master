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
    
    private KochFractal koch;
    private ArrayList<Edge> edges;
    private FUN3KochFractalFX application;
    private TimeStamp tsCalc;
    private TimeStamp tsDraw;
    private Integer count;
    
    public KochManager(FUN3KochFractalFX application) {
        this.edges = new ArrayList<Edge>();
        this.koch = new KochFractal(this);
        this.application = application;
        this.tsCalc = new TimeStamp();
        this.tsDraw = new TimeStamp();
    }
    
    public void changeLevel(int nxt) {
        count = 0;
        edges.clear();
        koch.setLevel(nxt);
        tsCalc.init();
        tsCalc.setBegin("Begin calculating");
        Thread leftEdgeThread = new Thread(leftEdgeRunnable);
        Thread rightEdgeThread = new Thread(rightEdgeRunnable);
        Thread bottomEdgeThread = new Thread(bottomEdgeRunnable);
        leftEdgeThread.start();
        rightEdgeThread.start();
        bottomEdgeThread.start();
        tsCalc.setEnd("End calculating");
        application.setTextNrEdges("" + koch.getNrOfEdges());
        application.setTextCalc(tsCalc.toString());
        while(count < 3){

        }
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
    
    public void addEdge(Edge e) {
        edges.add(e);
    }

    Runnable leftEdgeRunnable = new Runnable() {
        @Override
        public void run() {
            koch.generateLeftEdge();
            count++;
        }
    };

    Runnable rightEdgeRunnable = new Runnable() {
        @Override
        public void run() {
            koch.generateRightEdge();
            count++;
        }
    };

    Runnable bottomEdgeRunnable = new Runnable() {
        @Override
        public void run() {
            koch.generateBottomEdge();
            count++;
        }
    };
}
