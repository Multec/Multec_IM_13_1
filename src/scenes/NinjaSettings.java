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

public class NinjaSettings extends AbstractScene {

	
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
	
    
	public NinjaSettings(MTApplication mtApplication, String name) {
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
		instellingen.setPositionGlobal(new Vector3D(mtApplication.width/2 - 63, mtApplication.height -780));
		instellingen.setText("Instellingen");
		instellingen.setPickable(false);
		instellingen.setNoFill(true);
		instellingen.setNoStroke(true);
	    this.getCanvas().addChild(instellingen);
	    
	    MTLine line = new MTLine(mtApplication, new Vertex(650, 145), new Vertex(800, 145));
	    line.setPickable(false);
	    getCanvas().addChild(line);
	    
	    // helderheid woord
		
		MTTextArea helderheid = new MTTextArea(mtApplication, FontManager.getInstance().createFont(mtApplication, "FuturaStd-Light.otf", 
				24, 				  //Font size
				new MTColor(255, 255, 255, 255),   //Font fill color
				new MTColor(255, 255, 255, 255))); // Font 
		helderheid.setPositionGlobal(new Vector3D(mtApplication.width/2 - 252, mtApplication.height -680));
		helderheid.setText("Helderheid:");
		helderheid.setPickable(false);
		helderheid.setNoFill(true);
		helderheid.setNoStroke(true);
	    this.getCanvas().addChild(helderheid);
	    
	    // helderheid slider
	    
	    MTSlider helderheidwaarde = new MTSlider (mtApplication.width/2 -50, mtApplication.height/2-240, 300, 25, 0, 100, mtApplication);
	    this.getCanvas().addChild(helderheidwaarde);
	    
	    // volume woord
	    
		MTTextArea volume = new MTTextArea(mtApplication, FontManager.getInstance().createFont(mtApplication, "FuturaStd-Light.otf", 
				24, 				  //Font size
				new MTColor(255, 255, 255, 255),   //Font fill color
				new MTColor(255, 255, 255, 255))); // Font 
		volume.setPositionGlobal(new Vector3D(mtApplication.width/2 - 252, mtApplication.height -580));
		volume.setText("Volume:");
		volume.setPickable(false);
		volume.setNoFill(true);
		volume.setNoStroke(true);
	    this.getCanvas().addChild(volume);
	    
	    // volume slider
	    
	    MTSlider volumewaarde = new MTSlider (mtApplication.width/2 -50, mtApplication.height/2 - 140, 300, 25, 0, 100, mtApplication);
	    this.getCanvas().addChild(volumewaarde);
	    
	    // slaapstand woord
	    
	    MTTextArea slaapstand = new MTTextArea(mtApplication, FontManager.getInstance().createFont(mtApplication, "FuturaStd-Light.otf", 
				24, 				  //Font size
				new MTColor(255, 255, 255, 255),   //Font fill color
				new MTColor(255, 255, 255, 255))); // Font 
	    slaapstand.setPositionGlobal(new Vector3D(mtApplication.width/2 - 252, mtApplication.height -480));
	    slaapstand.setText("Slaapstand:");
	    slaapstand.setPickable(false);
	    slaapstand.setNoFill(true);
	    slaapstand.setNoStroke(true);
	    this.getCanvas().addChild(slaapstand);
	    
	    // slaapstand waarden
	    
	    MTTextArea waarden = new MTTextArea(mtApplication, FontManager.getInstance().createFont(mtApplication, "FuturaStd-Light.otf", 
				24, 				  //Font size
				new MTColor(255, 255, 255, 255),   //Font fill color
				new MTColor(255, 255, 255, 255))); // Font 
	    waarden.setPositionGlobal(new Vector3D(mtApplication.width/2 + 150, mtApplication.height -480));
	    waarden.setText("5 minuten");
	    waarden.setPickable(false);
	    waarden.setNoFill(true);
	    waarden.setNoStroke(true);
	    this.getCanvas().addChild(waarden);
	    
	    // opslaan
	    
	    MTTextArea textFieldVijf = new MTTextArea(mtApplication, FontManager.getInstance().createFont(mtApplication, "FuturaStd-Light.otf", 
				24, 				  //Font size
				new MTColor(255, 255, 255, 255),   //Font fill color
				new MTColor(255, 255, 255, 255))); // Font 
		textFieldVijf.setPositionGlobal(new Vector3D(mtApplication.width/2 - 122, mtApplication.height -380));
	    textFieldVijf.setText("Opslaan");
	    textFieldVijf.setPickable(false);
	    textFieldVijf.setNoFill(true);
		textFieldVijf.setNoStroke(true);
	    this.getCanvas().addChild(textFieldVijf);
	
	    // annuleren
	    
	    MTTextArea annuleren = new MTTextArea(mtApplication, FontManager.getInstance().createFont(mtApplication, "FuturaStd-Light.otf", 
				24, 				  //Font size
				new MTColor(255, 255, 255, 255),   //Font fill color
				new MTColor(255, 255, 255, 255))); // Font 
	    annuleren.setPositionGlobal(new Vector3D(mtApplication.width/2 + 30, mtApplication.height -380));
	    annuleren.setText("Annuleren");
	    annuleren.setPickable(false);
	    annuleren.setNoFill(true);
	    annuleren.setNoStroke(true);
	    this.getCanvas().addChild(annuleren);
	    
	    // meer over deze app
	    
	    MTTextArea meerinfo = new MTTextArea(mtApplication, FontManager.getInstance().createFont(mtApplication, "FuturaStd-Light.otf", 
				24, 				  //Font size
				new MTColor(255, 255, 255, 255),   //Font fill color
				new MTColor(255, 255, 255, 255))); // Font 
	    meerinfo.setPositionGlobal(new Vector3D(mtApplication.width/2 + 60, mtApplication.height -120));
	    meerinfo.setText("Meer over deze app");
	    meerinfo.setPickable(false);
	    meerinfo.setNoFill(true);
	    meerinfo.setNoStroke(true);
	    this.getCanvas().addChild(meerinfo);
	}
	
	
	
	public NinjaSettings(MTApplication mtApplication, String name, String _category) {
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
