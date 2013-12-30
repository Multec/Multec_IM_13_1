package scenes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.mt4j.MTApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTRectangle;
import org.mt4j.components.visibleComponents.widgets.MTBackgroundImage;
import org.mt4j.components.visibleComponents.widgets.buttons.MTImageButton;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapEvent;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.sceneManagement.Iscene;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;

import processing.core.PImage;
import scenes.MainNinjaScene;

public class NinjaMainMenu extends AbstractScene {

	//Applicatie variabelen
	private MTApplication mtApp;
	private String currentCategory;
	//Verschillende scenes waar deze scene naartoe kan gaan(nog niet geinitialiseerd)
	//Exit knop is geen scene maar rechtstreekse sluiting van het window
	private Iscene newScene;
	
	public NinjaMainMenu(MTApplication mtApplication, String name, String _category) {
		//Setup van het scherm.
		super(mtApplication, name);
		this.currentCategory = _category;
		this.mtApp = mtApplication;
		this.setClearColor(new MTColor(0, 0, 0, 255));
		PImage bg_img = mtApplication.loadImage("../data/ninja_bg.jpg");
		MTBackgroundImage bg = new MTBackgroundImage(mtApp, bg_img, false);
		//Aanmaken van het logo dat dienst doet als menu opener
		PImage logo = mtApplication.loadImage("../data/menu/ninjaLogo.png");
		MTImageButton logoMenu = new MTImageButton(logo, mtApp);
		//Laadt de images in
		PImage ribbon = mtApplication.loadImage("../data/menu/menuRibbon.png");
		PImage theme = mtApplication.loadImage("../data/menu/theme.png");
		PImage settings = mtApplication.loadImage("../data/menu/settings.png");
		PImage exit = mtApplication.loadImage("../data/menu/exit.png");
		PImage category = mtApplication.loadImage("../data/menu/hot.png");
		if(this.currentCategory == "Binnenland") {
			category = mtApplication.loadImage("../data/menu/binnenland.png");
		}
		else if(this.currentCategory == "Buitenland") {
			category = mtApplication.loadImage("../data/menu/buitenland.png");
		}
		else if(this.currentCategory == "Sport") {
			category = mtApplication.loadImage("../data/menu/sport.png");
		}
		else if(this.currentCategory == "Cultuur") {
			category = mtApplication.loadImage("../data/menu/cultuur.png");
		}
		else if(this.currentCategory == "Economie") {
			category = mtApplication.loadImage("../data/menu/economie.png");
		}
		//Aanmaken van het de imageButtons en de menuRibbon
		MTRectangle menuRibbon = new MTRectangle(ribbon, mtApp);
		MTImageButton categoryButton = new MTImageButton(category, mtApp);
		MTImageButton themeButton = new MTImageButton(theme, mtApp);
		MTImageButton settingsButton = new MTImageButton(settings, mtApp);
		MTImageButton exitButton = new MTImageButton(exit, mtApp);
		//Verwijder de strokes rondom de imageButton
		logoMenu.setNoStroke(true);
		menuRibbon.setNoStroke(true);
		categoryButton.setNoStroke(true);
		themeButton.setNoStroke(true);
		settingsButton.setNoStroke(true);
		exitButton.setNoStroke(true);
		//Als OpenGL aanwezig is, gebruik maken van OpenGL om de graphics te tonen
		if (MT4jSettings.getInstance().isOpenGlMode()) {
			bg.setUseDirectGL(true);
			logoMenu.setUseDirectGL(true);
			menuRibbon.setUseDirectGL(true);
			categoryButton.setUseDirectGL(true);
			themeButton.setUseDirectGL(true);
			settingsButton.setUseDirectGL(true);
			exitButton.setUseDirectGL(true);
		}
		//Alle events van de verschillende menu-onderdelen worden hieronder gedefinieerd
		logoMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eLogo) {
				switch(eLogo.getID()) {
				case TapEvent.BUTTON_CLICKED:
					//Ga terug naar mainNinjaScene
					//Deze keer wordt de category ook meegestuurd zodoende dat deze niet verloren gaat
					//bij het afbreken van de scen
					if(mtApp.popScene()) {
						mtApp.popScene();
					}
					newScene = new MainNinjaScene(mtApp, "NinjaNieuws", currentCategory);
					mtApp.addScene(newScene);
					mtApp.changeScene(newScene);
					break;
				default:
					break;
				}		
			}
		});
		categoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eCategory) {
				switch(eCategory.getID()) {
				case TapEvent.BUTTON_CLICKED:
					//Code voor categoryselectie
					if(mtApp.popScene()) {
						mtApp.popScene();
					}
					newScene = new NinjaSelectCategory(mtApp, "Ninjanieuws", currentCategory);
					mtApp.addScene(newScene);
					mtApp.changeScene(newScene);
					break;
				default:
					break;
				}
			}
		});
		themeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eTheme) {
				switch(eTheme.getID()) {
				case TapEvent.BUTTON_CLICKED:
					//Code voor naar thema selectie te gaan komt hier
					break;
				default:
					break;
				}
			}
		});
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eSettings) {
				switch(eSettings.getID()) {
				case TapEvent.BUTTON_CLICKED:
					//Code voor naar settingsscherm te gaan komt hier
					break;
				default:
					break;
				}
			}
		});
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exit) {
				switch(exit.getID()) {
				case TapEvent.BUTTON_CLICKED:
					//Code voor de app af te sluiten (WIP want nog veel problemen)
					break;
				default:
					break;
				}
			}
		});
		//Alle elementen toevoegen op het canvas.
		this.getCanvas().addChild(bg);
		this.getCanvas().addChild(menuRibbon);
		this.getCanvas().addChild(logoMenu);
		this.getCanvas().addChild(categoryButton);
		this.getCanvas().addChild(themeButton);
		this.getCanvas().addChild(settingsButton);
		this.getCanvas().addChild(exitButton);
		//Plaats van de elementen op het canvas instellen
		logoMenu.setPositionGlobal(new Vector3D(mtApp.width - logoMenu.getWidthXY(TransformSpace.GLOBAL) / 2, logoMenu.getHeightXY(TransformSpace.GLOBAL) / 2));
		menuRibbon.setPositionGlobal(new Vector3D(mtApp.width - menuRibbon.getWidthXY(TransformSpace.GLOBAL) / 2 + 22, menuRibbon.getHeightXY(TransformSpace.GLOBAL) / 2 - 23));
		categoryButton.setPositionGlobal(new Vector3D(mtApp.width - categoryButton.getWidthXY(TransformSpace.GLOBAL) / 2 - 205, categoryButton.getHeightXY(TransformSpace.GLOBAL) / 2));
		themeButton.setPositionGlobal(new Vector3D(mtApp.width - themeButton.getWidthXY(TransformSpace.GLOBAL) / 2 - 180, themeButton.getHeightXY(TransformSpace.GLOBAL) / 2 + 90));
		settingsButton.setPositionGlobal(new Vector3D(mtApp.width - settingsButton.getWidthXY(TransformSpace.GLOBAL) / 2 - 115, settingsButton.getHeightXY(TransformSpace.GLOBAL) / 2 + 160));
		exitButton.setPositionGlobal(new Vector3D(mtApp.width - exitButton.getWidthXY(TransformSpace.GLOBAL) / 2 - 20, exitButton.getHeightXY(TransformSpace.GLOBAL) / 2 + 210));
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
