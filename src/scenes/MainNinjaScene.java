package scenes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.mt4j.MTApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.widgets.MTBackgroundImage;
import org.mt4j.components.visibleComponents.widgets.buttons.MTImageButton;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.sceneManagement.Iscene;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;

import processing.core.PImage;

public class MainNinjaScene extends AbstractScene {

	//Applicatie variabelen
	private MTApplication mtApp;
	private Iscene menuOpen;
	private String category;
	
	public MainNinjaScene(MTApplication mtApplication, String name) {
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
	}
	
	public MainNinjaScene(MTApplication mtApplication, String name, String _category) {
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
