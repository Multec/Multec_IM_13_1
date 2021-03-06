package scenes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
import supportClasses.PathContainer;

public class NinjaSelectCategory extends AbstractScene {

	//Applicatie variabelen
	private MTApplication mtApp;
	private String category;
	//Arraylist met opgeslagen categorynamen en filepaths
	private List<PathContainer> filePaths = new ArrayList<PathContainer>();
	//Hoeveelheid buttons in categoriemenu
	private final int AMOUNT_BUTTONS = 5;
	//Hoeveelheid categorieen
	private final int AMOUNT_CAT = 6;
	//variabelen voor opmaak imagebuttons
	private PImage[] btnImages = new PImage[5];
	private MTImageButton[] catBtns = new MTImageButton[5];
	//variabele voor onthouden van categorie
	private String[] catOrder = new String[5];
	//Verschillende scenes waar deze scene naartoe kan gaan(nog niet geinitialiseerd)
	//Exit knop is geen scene maar rechtstreekse sluiting van het window
	private Iscene newScene;
	
	public NinjaSelectCategory(MTApplication mtApplication, String name, String _category) {
		//Setup van het scherm.
		super(mtApplication, name);
		this.category = _category;
		this.mtApp = mtApplication;
		//Alle categorie�n met hun filepath
		filePaths.add(new PathContainer("Hot", "../data/menu/hot.png"));
		filePaths.add(new PathContainer("Binnenland", "../data/menu/binnenland.png"));
		filePaths.add(new PathContainer("Buitenland", "../data/menu/buitenland.png"));
		filePaths.add(new PathContainer("Sport", "../data/menu/sport.png"));
		filePaths.add(new PathContainer("Cultuur", "../data/menu/cultuur.png"));
		filePaths.add(new PathContainer("Economie", "../data/menu/economie.png"));
		this.setClearColor(new MTColor(0, 0, 0, 255));
		PImage bg_img = mtApplication.loadImage("../data/ninja_bg.jpg");
		MTBackgroundImage bg = new MTBackgroundImage(mtApp, bg_img, false);
		//Aanmaken van het logo dat dienst doet als menu opener
		PImage logo = mtApplication.loadImage("../data/menu/ninjaLogo.png");
		MTImageButton logoMenu = new MTImageButton(logo, mtApp);
		//Laadt de images van het category menu
		PImage catMenu = mtApplication.loadImage("../data/menu/categoryMenu.png");
		int count = 0;
		for(int i = 0; i < AMOUNT_CAT; i++) {
			if(filePaths.get(i).getCategoryName() != this.category) {
				btnImages[count] = mtApplication.loadImage(filePaths.get(i).getFilePath());
				catOrder[count] = filePaths.get(i).getCategoryName();
				count++;
			}
		}
		//Inladen images van hoofdmenu
		PImage ribbon = mtApplication.loadImage("../data/menu/menuRibbon.png");
		PImage theme = mtApplication.loadImage("../data/menu/theme.png");
		PImage settings = mtApplication.loadImage("../data/menu/settings.png");
		PImage exit = mtApplication.loadImage("../data/menu/exit.png");
		PImage catImg = mtApplication.loadImage("../data/menu/hot.png");
		if(this.category == "Binnenland") {
			catImg = mtApplication.loadImage("../data/menu/binnenland.png");
		}
		else if(this.category == "Buitenland") {
			catImg = mtApplication.loadImage("../data/menu/buitenland.png");
		}
		else if(this.category == "Sport") {
			catImg = mtApplication.loadImage("../data/menu/sport.png");
		}
		else if(this.category == "Cultuur") {
			catImg = mtApplication.loadImage("../data/menu/cultuur.png");
		}
		else if(this.category == "Economie") {
			catImg = mtApplication.loadImage("../data/menu/economie.png");
		}
		//Aanmaken van de verschillende menu-elementen
		MTRectangle menuRibbon = new MTRectangle(ribbon, mtApp);
		MTImageButton categoryButton = new MTImageButton(catImg, mtApp);
		MTImageButton themeButton = new MTImageButton(theme, mtApp);
		MTImageButton settingsButton = new MTImageButton(settings, mtApp);
		MTImageButton exitButton = new MTImageButton(exit, mtApp);
		MTRectangle categoryMenu = new MTRectangle(catMenu, mtApp);
		for(int i = 0; i < AMOUNT_BUTTONS; i++) {
			catBtns[i] = new MTImageButton(btnImages[i], mtApp);
		}
		//Verwijder de strokes rondom de verschillende elementen
		logoMenu.setNoStroke(true);
		menuRibbon.setNoStroke(true);
		categoryButton.setNoStroke(true);
		themeButton.setNoStroke(true);
		settingsButton.setNoStroke(true);
		exitButton.setNoStroke(true);
		categoryMenu.setNoStroke(true);
		for(int i = 0; i < AMOUNT_BUTTONS; i++) {
			catBtns[i].setNoStroke(true);
		}
		//Als OpenGL aanwezig is, gebruik maken van OpenGL om de graphics te tonen
		if (MT4jSettings.getInstance().isOpenGlMode()) {
			bg.setUseDirectGL(true);
			logoMenu.setUseDirectGL(true);
			menuRibbon.setUseDirectGL(true);
			categoryButton.setUseDirectGL(true);
			themeButton.setUseDirectGL(true);
			settingsButton.setUseDirectGL(true);
			exitButton.setUseDirectGL(true);
			categoryMenu.setUseDirectGL(true);
			for(int i = 0; i < AMOUNT_BUTTONS; i++) {
				catBtns[i].setUseDirectGL(true);
			}
		}
		//Alle events van de verschillende menu-onderdelen worden hieronder gedefinieerd
		logoMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eLogo) {
				switch(eLogo.getID()) {
				case TapEvent.BUTTON_CLICKED:
					//Ga terug naar mainNinjaScene
					//Deze keer wordt de category ook meegestuurd zodoende dat deze niet verloren gaat
					//bij het afbreken van de scene
					mtApp.popScene();
					newScene = new MainNinjaScene(mtApp, "NinjaNieuws");
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
					mtApp.popScene();
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
		catBtns[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eBtn) {
				switch(eBtn.getID()) {
				case TapEvent.BUTTON_CLICKED:
					mtApp.popScene();
					newScene = new NinjaMainMenu(mtApp, "Ninjanieuws", catOrder[0]);
					mtApp.addScene(newScene);
					mtApp.changeScene(newScene);
					break;
				default:
					break;
				}
			}
		});
		catBtns[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eBtn) {
				switch(eBtn.getID()) {
				case TapEvent.BUTTON_CLICKED:
					mtApp.popScene();
					newScene = new NinjaMainMenu(mtApp, "Ninjanieuws", catOrder[1]);
					mtApp.addScene(newScene);
					mtApp.changeScene(newScene);
					break;
				default:
					break;
				}
			}
		});
		catBtns[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eBtn) {
				switch(eBtn.getID()) {
				case TapEvent.BUTTON_CLICKED:
					mtApp.popScene();
					newScene = new NinjaMainMenu(mtApp, "Ninjanieuws", catOrder[2]);
					mtApp.addScene(newScene);
					mtApp.changeScene(newScene);
					break;
				default:
					break;
				}
			}
		});
		catBtns[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eBtn) {
				switch(eBtn.getID()) {
				case TapEvent.BUTTON_CLICKED:
					mtApp.popScene();
					newScene = new NinjaMainMenu(mtApp, "Ninjanieuws", catOrder[3]);
					mtApp.addScene(newScene);
					mtApp.changeScene(newScene);
					break;
				default:
					break;
				}
			}
		});
		catBtns[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eBtn) {
				switch(eBtn.getID()) {
				case TapEvent.BUTTON_CLICKED:
					mtApp.popScene();
					newScene = new NinjaMainMenu(mtApp, "Ninjanieuws", catOrder[4]);
					mtApp.addScene(newScene);
					mtApp.changeScene(newScene);
					break;
				default:
					break;
				}
			}
		});
		//Alle elementen toevoegen op het canvas.
		this.getCanvas().addChild(bg);
		this.getCanvas().addChild(categoryMenu);
		this.getCanvas().addChild(menuRibbon);
		this.getCanvas().addChild(logoMenu);
		this.getCanvas().addChild(categoryButton);
		this.getCanvas().addChild(themeButton);
		this.getCanvas().addChild(settingsButton);
		this.getCanvas().addChild(exitButton);
		for(int i = 0; i < AMOUNT_BUTTONS; i++) {
			this.getCanvas().addChild(catBtns[i]);
		}
		//Plaats van de menu-elementen op het canvas instellen
		logoMenu.setPositionGlobal(new Vector3D(mtApp.width - logoMenu.getWidthXY(TransformSpace.GLOBAL) / 2, logoMenu.getHeightXY(TransformSpace.GLOBAL) / 2));
		menuRibbon.setPositionGlobal(new Vector3D(mtApp.width - menuRibbon.getWidthXY(TransformSpace.GLOBAL) / 2 + 22, menuRibbon.getHeightXY(TransformSpace.GLOBAL) / 2 - 23));
		categoryMenu.setPositionGlobal(new Vector3D(mtApp.width - categoryMenu.getWidthXY(TransformSpace.GLOBAL) / 2, categoryMenu.getHeightXY(TransformSpace.GLOBAL) / 2));
		//Buttons eerste menu
		categoryButton.setPositionGlobal(new Vector3D(mtApp.width - categoryButton.getWidthXY(TransformSpace.GLOBAL) / 2 - 205, categoryButton.getHeightXY(TransformSpace.GLOBAL) / 2));
		themeButton.setPositionGlobal(new Vector3D(mtApp.width - themeButton.getWidthXY(TransformSpace.GLOBAL) / 2 - 180, themeButton.getHeightXY(TransformSpace.GLOBAL) / 2 + 90));
		settingsButton.setPositionGlobal(new Vector3D(mtApp.width - settingsButton.getWidthXY(TransformSpace.GLOBAL) / 2 - 115, settingsButton.getHeightXY(TransformSpace.GLOBAL) / 2 + 160));
		exitButton.setPositionGlobal(new Vector3D(mtApp.width - exitButton.getWidthXY(TransformSpace.GLOBAL) / 2 - 20, exitButton.getHeightXY(TransformSpace.GLOBAL) / 2 + 210));
		//Buttons category menu
		catBtns[0].setPositionGlobal(new Vector3D(mtApp.width - catBtns[0].getWidthXY(TransformSpace.GLOBAL) / 2 - 315, catBtns[0].getHeightXY(TransformSpace.GLOBAL) / 2 + 10));
		catBtns[1].setPositionGlobal(new Vector3D(mtApp.width - catBtns[1].getWidthXY(TransformSpace.GLOBAL) / 2 - 280, catBtns[1].getHeightXY(TransformSpace.GLOBAL) / 2 + 120));
		catBtns[2].setPositionGlobal(new Vector3D(mtApp.width - catBtns[2].getWidthXY(TransformSpace.GLOBAL) / 2 - 225, catBtns[2].getHeightXY(TransformSpace.GLOBAL) / 2 + 217));
		catBtns[3].setPositionGlobal(new Vector3D(mtApp.width - catBtns[3].getWidthXY(TransformSpace.GLOBAL) / 2 - 130, catBtns[3].getHeightXY(TransformSpace.GLOBAL) / 2 + 277));
		catBtns[4].setPositionGlobal(new Vector3D(mtApp.width - catBtns[4].getWidthXY(TransformSpace.GLOBAL) / 2 - 30, catBtns[4].getHeightXY(TransformSpace.GLOBAL) / 2 + 317));
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
