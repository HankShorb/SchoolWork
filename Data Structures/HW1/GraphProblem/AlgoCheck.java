import java.awt.*;
import java.applet.*;
import graph.*;
import java.util.Random;


public class AlgoCheck extends Applet {

      Graph2D graph;
      DataSet data1;
      DataSet data2;
      DataSet data3;
      Axis    xaxis;
      Axis    yaxis_left;
      Axis    yaxis_right;
      double d1[],d2[],d3[];
      int np = 100000;
      int n;
 

      public void init() {
        final int MAXPOINTS=25;
        double d1[] = new double[MAXPOINTS*2];
        double d2[] = new double[MAXPOINTS*2];
        double d3[] = new double[MAXPOINTS*2];
        double x,y;

        //initialize stuff
        graph = new Graph2D();
        graph.drawzero = false;
        graph.drawgrid = false;
        graph.borderTop = 50;
        graph.borderRight=100;
        Random generator= new Random();
        setLayout( new BorderLayout() );
        add("Center", graph);
        int[] a;
        boolean[] used;
        int tempInt;
        int rand;
        double time=0;
        int n = 50;
        TimeInterval t;
        
        //set counter for algorightm 1 to 0 start for loop to run algorithm
        int algo1Count=0;
        for(int j=0; n<7000000 && time<30 ; j+=2){
        	n = 2*n;
            //start timer
        	t= new TimeInterval();
        	t.startTiming();
        	a = new int[n];
            //generating algorithm
        	for(int i=0; i<n; i++){
        		boolean needValue = true;
        		int entryNum = 0;
        		while(needValue){
        			entryNum = generator.nextInt(n);
        			needValue=false;
        			for(int k=0;k<i;k++){
        				if(a[k]==entryNum){
        					needValue = true;
        				}
        			}
        		}
        	a[i]=entryNum;
        	}
            //end timer
        	t.endTiming();
        	time = t.getElapsedTime();
            //store data
        	d1[j]=n;
        	d1[j+1]=time;
        	algo1Count++;
        	System.out.println("time to generate " + n + " values of Algorithm 1 is " + t.getElapsedTime());
        }
        

        //Same as last chunk basically
        time=0;
        int algo2Count=0;
        n=50;
        for(int j=0; n<7000000 && time<30 ; j+=2){
        	n=n*2;
        	t= new TimeInterval();
        	t.startTiming();
        	a = new int[n];
        	used = new boolean[n];
        	
        	for(int i=0; i<n; i++){
        		boolean needValue = true;
        		int entryNum = 0;
        		while(needValue){
        			entryNum = generator.nextInt(n);

        			if(!used[entryNum]){
        				needValue = false;
        				used[entryNum] = true;
        			}
        		}
        		a[i]=entryNum;
        	}
        	t.endTiming();
        	time = t.getElapsedTime();
        	d2[j]=n;
        	d2[j+1]=time;
        	algo2Count++;
        	System.out.println("time to generate " + n + " values of Algorithm 2 is " + t.getElapsedTime()/1000.0);
        }
        

        //Same again
        time=0;
        int algo3Count=0;
        n=50;
        for(int j=0; n<7000000 && time<30 ; j+=2){
        	n=n*2;
        	t= new TimeInterval();
        	t.startTiming();
        	a = new int[n];
        	for(int i=0;i<n;i++){
        		a[i]=i+1;
        	}
        	for(int i=0;i<n;i++){
        		rand = generator.nextInt(n);
        		tempInt = a[i];
        		a[i]=a[rand];
        		a[rand]=tempInt;
        	}
        	t.endTiming();
        	time = t.getElapsedTime();
        	d3[j]=n;
        	d3[j+1]=time;
        	algo3Count++;
        	System.out.println("time to generate " + n + " values of Algorithm 3 is " + t.getElapsedTime()/1000.0);
        }
        
        
        data1 = graph.loadDataSet(d1,algo1Count);
        data1.linestyle = 1;
        data1.linecolor   =  new Color(0,255,0);
        data1.marker    = 1;
        data1.markerscale = 1;
        data1.markercolor = new Color(0,0,255);
        data1.legend(200,100,"Algorithm 1");
        data1.legendColor(Color.black);
        
        data2 = graph.loadDataSet(d2,algo2Count);
        data2.linestyle = 1;
        data2.linecolor   =  new Color(255,0,0);
        data2.marker    = 1;
        data2.markerscale = 1;
        data2.markercolor = new Color(0,0,255);
        data2.legend(200,120,"Algorithm 2");
        data2.legendColor(Color.black);
        
        data3 = graph.loadDataSet(d3,algo3Count);
        data3.linestyle = 1;
        data3.linecolor   =  new Color(0,0,255);
        data3.marker    = 1;
        data3.markerscale = 1;
        data3.markercolor = new Color(0,0,255);
        data3.legend(200,140,"Algorithm 3");
        data3.legendColor(Color.black);

        xaxis = graph.createAxis(Axis.BOTTOM);
        xaxis.attachDataSet(data1);
        xaxis.attachDataSet(data2);
        xaxis.attachDataSet(data3);
        xaxis.setTitleText("X values");
        xaxis.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        xaxis.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
/*
**      Attach the first data set to the Left Axis
*/
        yaxis_left = graph.createAxis(Axis.LEFT);
        yaxis_left.attachDataSet(data1);
        yaxis_left.attachDataSet(data2);
        yaxis_left.attachDataSet(data3);
        yaxis_left.setTitleText("Y VALUES");
        yaxis_left.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        yaxis_left.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
        yaxis_left.setTitleColor( new Color(0,0,255) );
      
      
   }
 }



/**
  * A timing utility class useful for timing code segments
*/
 class TimeInterval {

    private long startTime, endTime;
    private long elapsedTime; // Time interval in milliseconds

    // Commands
    public void startTiming() {
        elapsedTime = 0;
        startTime = System.currentTimeMillis();
    }

    public void endTiming() {
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
    }

    // Queries
    public double getElapsedTime() {
        return (double) elapsedTime / 1000.0;
    }

}
