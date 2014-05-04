package edu.jmm;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class Ball extends View {
	
	   private static final double STPR = 7; //SENSOR TO PIXEL RATIO
	   private int xMin = 0;          // This view's bounds
	   private int xMax;
	   private int yMin = 0;
	   private int yMax;
	   private float midleX;
	   private float midleY;
	   private float ballRadius = 8; // Ball's radius
	   private float ballX = ballRadius + 20;  // Ball's center (x,y)
	   private float ballY = ballRadius + 40;
	   private float ballSpeedX = 5;  // Ball's speed (x,y)
	   private float ballSpeedY = 3;
	   private RectF ballBounds;      // Needed for Canvas.drawOval
	   private Paint paint;           // The paint (e.g. style, color) used for drawing
	   ICoordProvider updater;
	   
	   // Constructor
	   public Ball(Context context, ICoordProvider Updater) {
	      super(context);
	      updater=Updater;
	      ballBounds = new RectF();
	      paint = new Paint();
	   }
	  
	   // Called back to draw the view. Also called by invalidate().
	   @Override
	   protected void onDraw(Canvas canvas) {
	      // Draw the ball
	      ballBounds.set(ballX-ballRadius, ballY-ballRadius,midleX+ ballX+ballRadius,midleY+ ballY+ballRadius);
	      paint.setColor(Color.GREEN);
	      canvas.drawOval(ballBounds, paint);
	        
	      // Update the position of the ball, including collision detection and reaction.
	      update();
	  
	      // Delay
	      try {  
	         Thread.sleep(30);  
	      } catch (InterruptedException e) { }
	      
	      invalidate();  // Force a re-draw
	   }
	   
	   // Detect collision and update the position of the ball.
	   private void update() {
	      // Get new (x,y) position
	      ballX=(int)(updater.getX()*STPR);
	      ballY=(int)(updater.getY()*STPR);
	      float z=updater.getZ();
	      
	     
	      
	      
	      if(z>0){
	    	  ballRadius=8*z*(float)0.5;
	      }else if(z<0){
	    	  ballRadius=(-1)*(8/z);
	      }else if(z==0){
	    	  ballRadius=8;
	      }
	    	
	      
	   }
	   
	   // Called back when the view is first created or its size changes.
	   @Override
	   public void onSizeChanged(int w, int h, int oldW, int oldH) {
	      // Set the movement bounds for the ball
		  midleX=(float) (w/2);
		  midleY=(float) (h/2);
	      xMax = w-1;
	      yMax = h-1;
	   }
	}