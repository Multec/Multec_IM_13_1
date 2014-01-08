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
import org.mt4j.components.visibleComponents.font.FontManager;
import org.mt4j.components.visibleComponents.widgets.MTBackgroundImage;
import org.mt4j.components.visibleComponents.widgets.MTImage;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.components.visibleComponents.widgets.buttons.MTImageButton;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.input.inputProcessors.globalProcessors.CursorTracer;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.sceneManagement.Iscene;
import org.mt4j.sceneManagement.transition.BlendTransition;
import org.mt4j.sceneManagement.transition.FadeTransition;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;
import org.mt4j.util.opengl.GLFBO;

import basic.scenes.Scene2;
import advanced.physics.physicsShapes.PhysicsRectangle;
import advanced.physics.util.PhysicsHelper;
import advanced.physics.util.UpdatePhysicsAction;
import processing.core.PImage;
import supportClasses.MoviePhysics;

public class NinjaVideoScene extends AbstractScene {

	protected static final String NinjaSelectTheme = null;
	//Applicatie variabelen
	private MTApplication mtApp;
	private Iscene menuOpen;
	private String category;
	private World world;
	private float timeStep = 1.0f/30.0f; 
    private int constraintIterations = 10; 
    private float scale = 20;
	
	public NinjaVideoScene(MTApplication mtApplication, String name) {
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
		ArrayList<String> movieNames = new ArrayList<String>(); 
		
		movieNames.add("../data/graspop.jpg"); 
		movieNames.add("../data/drones.jpg"); 
		movieNames.add("../data/rockwerchter.jpg"); 
		movieNames.add("../data/soa.jpg"); 
		movieNames.add("../data/stormweer.jpg"); 

		
		//variabelen om de physics world te cre‘ren
				Vec2 gravity = new Vec2( 0, 5);     
				AABB worldAABB = new AABB(new Vec2(-10,-10), new Vec2((mtApp.width), (mtApp.height))); 
		        boolean doSleep = true;       
		        
		        world = new World(worldAABB, gravity, doSleep); 
		        this.registerPreDrawAction(new UpdatePhysicsAction(world, timeStep, constraintIterations, scale)); 
		        MTComponent physicsContainer = new MTComponent(mtApp); 
		        
		        physicsContainer.scale(scale, scale, 1, Vector3D.ZERO_VECTOR); 
		        this.getCanvas().addChild(physicsContainer);
		        this.createScreenBorders(physicsContainer);
		        
				for(String s: movieNames) { 
					PImage photo = mtApp.loadImage(s); 
					// grote van de kaders
		            MoviePhysics physRect = new MoviePhysics(new Vector3D((float)Math.random()*mtApp.width,(float)Math.random()*mtApp.height), (int)(photo.width*0.1), (int)(photo.height*0.1), mtApp, world, 0.2f, 0.9f, 0.4f, scale); 
		            physRect.setTexture(photo); 
		            PhysicsHelper.addDragJoint(world, physRect, physRect.getBody().isDynamic(), scale); 
		            physicsContainer.addChild(physRect); 
		            }
				this.getCanvas().addChild(logoMenu);
	
	}
	
	
	private void createScreenBorders(MTComponent parent){
		//Left border 
		float borderWidth = 50f;
		float borderHeight = mtApp.height;
		Vector3D pos = new Vector3D(-(borderWidth/2f) , mtApp.height/2f);
		PhysicsRectangle borderLeft = new PhysicsRectangle(pos, borderWidth, borderHeight, mtApp, world, 0,0,0, scale);
		borderLeft.setName("borderLeft");
		parent.addChild(borderLeft);
		//Right border
		pos = new Vector3D(mtApp.width + (borderWidth/2), mtApp.height/2);
		PhysicsRectangle borderRight = new PhysicsRectangle(pos, borderWidth, borderHeight, mtApp, world, 0,0,0, scale);
		borderRight.setName("borderRight");
		parent.addChild(borderRight);
		//Top border
		borderWidth = mtApp.width;
		borderHeight = 50f;
		pos = new Vector3D(mtApp.width/2, -(borderHeight/2));
		PhysicsRectangle borderTop = new PhysicsRectangle(pos, borderWidth, borderHeight, mtApp, world, 0,0,0, scale);
		borderTop.setName("borderTop");
		parent.addChild(borderTop);
		//Bottom border
		pos = new Vector3D(mtApp.width/2 , mtApp.height + (borderHeight/2));
		PhysicsRectangle borderBottom = new PhysicsRectangle(pos, borderWidth, borderHeight, mtApp, world, 0,0,0, scale);
		borderBottom.setName("borderBottom");
		parent.addChild(borderBottom);
	}
	public NinjaVideoScene(MTApplication mtApplication, String name, String _category) {
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