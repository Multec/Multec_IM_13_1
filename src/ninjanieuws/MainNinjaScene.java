package ninjanieuws;

import org.mt4j.MTApplication;
import org.mt4j.components.visibleComponents.widgets.MTBackgroundImage;
import org.mt4j.components.visibleComponents.widgets.video.MTMovieClip;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.math.Vertex;

import processing.core.PImage;

public class MainNinjaScene extends AbstractScene {

	private MTApplication mtApp;
	
	public MainNinjaScene(MTApplication mtApplication, String name) {
		// TODO Auto-generated constructor stub
		super(mtApplication, name);
		this.mtApp = mtApplication;
		//this.setClearColor(new MTColor(0, 0, 0, 255));
		PImage bg_img = mtApplication.loadImage("../data/ninja_bg.jpg");
		MTBackgroundImage bg = new MTBackgroundImage(mtApp, bg_img, false);
		MTMovieClip movie = new MTMovieClip("../data/test_mov.mp4", new Vertex(), mtApp);
		//Altijd toevoegen op het canvas! Applicatie is 1 grote arraylist.
		this.getCanvas().addChild(bg);
		this.getCanvas().addChild(movie);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
		
	}

}
