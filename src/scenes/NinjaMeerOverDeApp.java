package scenes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.mt4j.MTApplication;
import org.mt4j.components.MTComponent;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.widgets.MTTextField;
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.font.IFont;
import org.mt4j.components.visibleComponents.shapes.MTEllipse;
import org.mt4j.components.visibleComponents.shapes.MTLine;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.shapes.MTRoundRectangle;
import org.mt4j.components.visibleComponents.widgets.MTBackgroundImage;
import org.mt4j.components.visibleComponents.widgets.MTImage;
import org.mt4j.components.visibleComponents.widgets.MTSlider;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.components.visibleComponents.widgets.MTTextField;
import org.mt4j.components.visibleComponents.widgets.buttons.MTImageButton;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.sceneManagement.Iscene;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.math.Vertex;

import advanced.physics.util.PhysicsHelper;
import advanced.physics.util.UpdatePhysicsAction;
import processing.core.PImage;
import supportClasses.MoviePhysics;

public class NinjaMeerOverDeApp extends AbstractScene {

	
	//Applicatie variabelen
	private MTApplication mtApp;
	private Iscene menuOpen;
	private String category;
	private World world;
	private float timeStep = 1.0f/30.0f; 
    private int constraintIterations = 10; 
    private float scale = 20;
    private MTTextArea volume;
    private MTTextArea brightness;
    private MTTextArea title;
    private MTSlider brightnessslid;
    private MTSlider volumeslid;
	
    
	public NinjaMeerOverDeApp(MTApplication mtApplication, String name) {
		//Setup van het scherm.
		super(mtApplication, name);
		this.mtApp = mtApplication;
		this.category = "Hot";
		this.setClearColor(new MTColor(0, 0, 0, 255));
		PImage bg_img = mtApplication.loadImage("../data/ninja_bg.jpg");
		MTBackgroundImage bg = new MTBackgroundImage(mtApp, bg_img, false);
		//Aanmaken van het logo dat dienst doet als menu opener
		//Tevens functionaliteit meegeven om te reageren op click-events
		PImage logo = mtApplication.loadImage("../data/menu/ninjaLogo.png");
		MTImageButton logoMenu = new MTImageButton(logo, mtApp);
		logoMenu.setNoStroke(true);
		if (MT4jSettings.getInstance().isOpenGlMode()) {
			bg.setUseDirectGL(true);
			logoMenu.setUseDirectGL(true);
		}
		logoMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					//Save scene
					mtApp.pushScene();
					if(menuOpen == null) {
						menuOpen = new NinjaMainMenu(mtApp, "Ninjanieuws", category);
						mtApp.addScene(menuOpen);
					}
					mtApp.changeScene(menuOpen);
					break;
				default:
					break;
				}		
			}
		});
		
		//Alle elementen toevoegen op het canvas.
		this.getCanvas().addChild(bg);
		this.getCanvas().addChild(logoMenu);
		logoMenu.setPositionGlobal(new Vector3D(mtApp.width - logoMenu.getWidthXY(TransformSpace.GLOBAL) / 2, logoMenu.getHeightXY(TransformSpace.GLOBAL) / 2));
		
		// Overlay
		
		MTRectangle overlaykader = new MTRectangle (0, 0, 1920, 1080, mtApplication);
		overlaykader.setFillColor(MTColor.GRAY);
		getCanvas().addChild(overlaykader);
		
		MTColor transparentColour = new MTColor(0, 0, 0, 130);
		
		overlaykader.setFillColor(transparentColour);
		
		overlaykader.setNoStroke(true);
		
		overlaykader.setPickable(false);
		
		// Settingskader
		MTRoundRectangle settingskader = new MTRoundRectangle (mtApplication.width/2 - 306, mtApplication.height/2-380, 64, 612, 760, 30, 30, mtApplication);
		settingskader.setFillColor(MTColor.RED);

		getCanvas().addChild(settingskader);
		
		MTColor settingskleur = new MTColor(0, 109, 104);
		
		settingskader.setFillColor(settingskleur);
	
		settingskader.setNoStroke(true);
		
		settingskader.setPickable(false);
		
		// Settings woord 
		
		MTTextArea instellingen = new MTTextArea(mtApplication, FontManager.getInstance().createFont(mtApplication, "FuturaStd-Light.otf", 
				30, 				  //Font size
				new MTColor(255, 255, 255, 255),   //Font fill color
				new MTColor(255, 255, 255, 255))); // Font 
		instellingen.setPositionGlobal(new Vector3D(mtApplication.width/2 - 58, mtApplication.height -780));
		instellingen.setText("NinjApp");
		instellingen.setPickable(false);
		instellingen.setNoFill(true);
		instellingen.setNoStroke(true);
	    this.getCanvas().addChild(instellingen);
	    
	    MTLine line = new MTLine(mtApplication, new Vertex(650, 145), new Vertex(780, 145));
	    line.setPickable(false);
	    getCanvas().addChild(line);
	    
	    // woordje uitleg
		
		MTTextArea deeleen = new MTTextArea(mtApplication, FontManager.getInstance().createFont(mtApplication, "FuturaStd-Light.otf", 
				24, 				  //Font size
				new MTColor(255, 255, 255, 255),   //Font fill color
				new MTColor(255, 255, 255, 255))); // Font 
		deeleen.setPositionGlobal(new Vector3D(mtApplication.width/2 - 252, mtApplication.height -680));
		deeleen.setText("Vers nieuws. Fijngesneden. Ninjanieuws is een");
		deeleen.setPickable(false);
		deeleen.setNoFill(true);
		deeleen.setNoStroke(true);
	    this.getCanvas().addChild(deeleen);
	    
	    MTTextArea deeltwee = new MTTextArea(mtApplication, FontManager.getInstance().createFont(mtApplication, "FuturaStd-Light.otf", 
				24, 				  //Font size
				new MTColor(255, 255, 255, 255),   //Font fill color
				new MTColor(255, 255, 255, 255))); // Font 
	    deeltwee.setPositionGlobal(new Vector3D(mtApplication.width/2 - 252, mtApplication.height -640));
	    deeltwee.setText("experiment van VRT Start-up.");
	    deeltwee.setPickable(false);
	    deeltwee.setNoFill(true);
	    deeltwee.setNoStroke(true);
	    this.getCanvas().addChild(deeltwee);
	    
	    // terug naar instellingen
	    
	    MTTextArea instellingenback = new MTTextArea(mtApplication, FontManager.getInstance().createFont(mtApplication, "FuturaStd-Light.otf", 
				24, 				  //Font size
				new MTColor(255, 255, 255, 255),   //Font fill color
				new MTColor(255, 255, 255, 255))); // Font 
	    instellingenback.setPositionGlobal(new Vector3D(mtApplication.width/2 + 150, mtApplication.height -120));
	    instellingenback.setText("Instellingen");
	    instellingenback.setPickable(false);
	    instellingenback.setNoFill(true);
	    instellingenback.setNoStroke(true);
	    this.getCanvas().addChild(instellingenback);
	}
	
	
	
	public NinjaMeerOverDeApp(MTApplication mtApplication, String name, String _category) {
		//Setup van het scherm.
		super(mtApplication, name);
		this.mtApp = mtApplication;
		if(_category == null) {
			this.category = "Hot";
		}
		else {
			this.category = _category;
		}
		this.setClearColor(new MTColor(0, 0, 0, 255));
		PImage bg_img = mtApplication.loadImage("../data/ninja_bg.jpg");
		MTBackgroundImage bg = new MTBackgroundImage(mtApp, bg_img, false);
		//Aanmaken van het logo dat dienst doet als menu opener
		//Tevens functionaliteit meegeven om te reageren op click-events
		PImage logo = mtApplication.loadImage("../data/menu/ninjaLogo.png");
		MTImageButton logoMenu = new MTImageButton(logo, mtApp);
		logoMenu.setNoStroke(true);
		if (MT4jSettings.getInstance().isOpenGlMode()) {
			logoMenu.setUseDirectGL(true);
		}
		logoMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					//Save scene
					mtApp.pushScene();
					if(menuOpen == null) {
						menuOpen = new NinjaMainMenu(mtApp, "Ninjanieuws", category);
						mtApp.addScene(menuOpen);
					}
					mtApp.changeScene(menuOpen);
					break;
				default:
					break;
				}		
			}
		});
		
		//Alle elementen toevoegen op het canvas.
		this.getCanvas().addChild(bg);
		this.getCanvas().addChild(logoMenu);
		logoMenu.setPositionGlobal(new Vector3D(mtApp.width - logoMenu.getWidthXY(TransformSpace.GLOBAL) / 2, logoMenu.getHeightXY(TransformSpace.GLOBAL) / 2));
	
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