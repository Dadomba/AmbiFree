package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ScreenAndZonesPanel extends JPanel{
	
	int window_width = -1;
	Dimension screen_resolution = new Dimension();
	
	boolean top_zones = false;
	ArrayList<Rectangle> top_zones_rect = new ArrayList<Rectangle>();
	
	boolean bottom_zones = false;
	ArrayList<Rectangle> bottom_zones_rect = new ArrayList<Rectangle>();
	
	ArrayList<Rectangle> lateral_zones_rect = new ArrayList<Rectangle>();
	
	double scale = -1;

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(initialisation){
			baseRectDrawing(g);
		}
	}
	
	public void init(){
		
	}
	
	public void baseRectDrawing(Graphics g){
		setPreferredSize(new Dimension(window_width, (int)Math.round(screen_resolution.getHeight()*scale) + 1));
		g.setColor(Color.BLACK);
		scale = (window_width-10)/screen_resolution.getWidth();
		g.drawRect(0, 0, window_width-10, (int)Math.round(screen_resolution.getHeight()*scale));
		setPreferredSize(new Dimension(window_width, (int)Math.round(screen_resolution.getHeight()*scale) + 1));
		
		System.out.println(window_width);
		System.out.println((int)Math.round(screen_resolution.getHeight()*scale) + 1);
	}
	


}
