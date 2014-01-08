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
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.MTColor;
import org.mt4j.util.math.Vector3D;

import processing.core.PImage;
import supportClasses.CategoryContainer;

public class MainNinjaScene extends AbstractScene {
	
	//Applicatie variabelen
	private MTApplication mtApp;
	private boolean visibleMenu = false;
	private boolean visibleCategory = false;

	public MainNinjaScene(MTApplication application, String name) {
		super(application, name);
		this.mtApp = application;
		this.setClearColor(new MTColor(0, 0, 0, 255));
		//Aanmaken achtergrond
		final PImage bg_img = application.loadImage("../data/ninja_bg.jpg");
		final MTBackgroundImage bg = new MTBackgroundImage(mtApp, bg_img, false);
		//Laadt de images van het category menu
		//Inladen images
		final PImage logo = application.loadImage("../data/menu/ninjaLogo.png");
		final PImage ribbon = application.loadImage("../data/menu/menuRibbon.png");
		final PImage categoryMenu = application.loadImage("../data/menu/categoryMenu.png");
		final PImage theme = application.loadImage("../data/menu/theme.png");
		final PImage settings = application.loadImage("../data/menu/settings.png");
		final PImage exit = application.loadImage("../data/menu/exit.png");
		final PImage hotMenu = application.loadImage("../data/menu/hot_menu.png");
		final PImage binnenlandMenu = application.loadImage("../data/menu/binnenland_menu.png");
		final PImage buitenlandMenu = application.loadImage("../data/menu/buitenland_menu.png");
		final PImage sportMenu = application.loadImage("../data/menu/sport_menu.png");
		final PImage cultuurMenu = application.loadImage("../data/menu/cultuur_menu.png");
		final PImage economieMenu = application.loadImage("../data/menu/economie_menu.png");
		//Aanmaken van hoofdmenu-elementen
		final MTImageButton logoBtn = new MTImageButton(logo, mtApp);
		final MTRectangle menuBG = new MTRectangle(ribbon, mtApp);
		final MTRectangle categoryMenuBG = new MTRectangle(categoryMenu, mtApp);
		final MTImageButton hotMenuBtn = new MTImageButton(hotMenu, mtApp);
		final MTImageButton binnenlandMenuBtn = new MTImageButton(binnenlandMenu, mtApp);
		final MTImageButton buitenlandMenuBtn = new MTImageButton(buitenlandMenu, mtApp);
		final MTImageButton sportMenuBtn = new MTImageButton(sportMenu, mtApp);
		final MTImageButton cultuurMenuBtn = new MTImageButton(cultuurMenu, mtApp);
		final MTImageButton economieMenuBtn = new MTImageButton(economieMenu, mtApp);
		final MTImageButton themeBtn = new MTImageButton(theme, mtApp);
		final MTImageButton settingsBtn = new MTImageButton(settings, mtApp);
		final MTImageButton exitBtn = new MTImageButton(exit, mtApp);
		//Aanmaken van categorie elementen
		final MTImageButton hotCategoryBtn = new MTImageButton(hotMenu, mtApp);
		final MTImageButton[] binnenlandCategoryBtn = {new MTImageButton(binnenlandMenu, mtApp), new MTImageButton(binnenlandMenu, mtApp)};
		final MTImageButton[] buitenlandCategoryBtn = {new MTImageButton(buitenlandMenu, mtApp), new MTImageButton(buitenlandMenu, mtApp)};
		final MTImageButton[] sportCategoryBtn = {new MTImageButton(sportMenu, mtApp), new MTImageButton(sportMenu, mtApp)};
		final MTImageButton[] cultuurCategoryBtn = {new MTImageButton(cultuurMenu, mtApp), new MTImageButton(cultuurMenu, mtApp)};
		final MTImageButton economieCategoryBtn = new MTImageButton(economieMenu, mtApp);
		//Set pickable false voor menuBG en categoryMenuBG
		menuBG.setPickable(false);
		categoryMenuBG.setPickable(false);
		//Verwijder de strokes rondom de verschillende elementen
		logoBtn.setNoStroke(true);
		menuBG.setNoStroke(true);
		hotMenuBtn.setNoStroke(true);
		binnenlandMenuBtn.setNoStroke(true);
		buitenlandMenuBtn.setNoStroke(true);
		sportMenuBtn.setNoStroke(true);
		cultuurMenuBtn.setNoStroke(true);
		economieMenuBtn.setNoStroke(true);
		themeBtn.setNoStroke(true);
		settingsBtn.setNoStroke(true);
		exitBtn.setNoStroke(true);
		categoryMenuBG.setNoStroke(true);
		hotCategoryBtn.setNoStroke(true);
		binnenlandCategoryBtn[0].setNoStroke(true);
		binnenlandCategoryBtn[1].setNoStroke(true);
		buitenlandCategoryBtn[0].setNoStroke(true);
		buitenlandCategoryBtn[1].setNoStroke(true);
		sportCategoryBtn[0].setNoStroke(true);
		sportCategoryBtn[1].setNoStroke(true);
		cultuurCategoryBtn[0].setNoStroke(true);
		cultuurCategoryBtn[1].setNoStroke(true);
		economieCategoryBtn.setNoStroke(true);
		//Als OpenGL aanwezig is, gebruik maken van OpenGL om de graphics te tonen
		if (MT4jSettings.getInstance().isOpenGlMode()) {
			bg.setUseDirectGL(true);
			logoBtn.setUseDirectGL(true);
			menuBG.setUseDirectGL(true);
			hotMenuBtn.setUseDirectGL(true);
			binnenlandMenuBtn.setUseDirectGL(true);
			buitenlandMenuBtn.setUseDirectGL(true);
			sportMenuBtn.setUseDirectGL(true);
			cultuurMenuBtn.setUseDirectGL(true);
			economieMenuBtn.setUseDirectGL(true);
			themeBtn.setUseDirectGL(true);
			settingsBtn.setUseDirectGL(true);
			exitBtn.setUseDirectGL(true);
			categoryMenuBG.setUseDirectGL(true);
			hotCategoryBtn.setUseDirectGL(true);
			binnenlandCategoryBtn[0].setUseDirectGL(true);
			binnenlandCategoryBtn[1].setUseDirectGL(true);
			buitenlandCategoryBtn[0].setUseDirectGL(true);
			buitenlandCategoryBtn[1].setUseDirectGL(true);
			sportCategoryBtn[0].setUseDirectGL(true);
			sportCategoryBtn[1].setUseDirectGL(true);
			cultuurCategoryBtn[0].setUseDirectGL(true);
			cultuurCategoryBtn[1].setUseDirectGL(true);
			economieCategoryBtn.setUseDirectGL(true);
		}
		//Visibility van de elementen op het canvas.
		bg.setVisible(true);
		logoBtn.setVisible(true);
		menuBG.setVisible(false);
		categoryMenuBG.setVisible(false);
		hotMenuBtn.setVisible(false);
		binnenlandMenuBtn.setVisible(false);
		buitenlandMenuBtn.setVisible(false);
		sportMenuBtn.setVisible(false);
		cultuurMenuBtn.setVisible(false);
		economieMenuBtn.setVisible(false);
		themeBtn.setVisible(false);
		settingsBtn.setVisible(false);
		exitBtn.setVisible(false);
		hotCategoryBtn.setVisible(false);
		binnenlandCategoryBtn[0].setVisible(false);
		binnenlandCategoryBtn[1].setVisible(false);
		buitenlandCategoryBtn[0].setVisible(false);
		buitenlandCategoryBtn[1].setVisible(false);
		sportCategoryBtn[0].setVisible(false);
		sportCategoryBtn[1].setVisible(false);
		cultuurCategoryBtn[0].setVisible(false);
		cultuurCategoryBtn[1].setVisible(false);
		economieCategoryBtn.setVisible(false);
		//Actionlisteners (regel 153 tot )
		logoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					if(visibleCategory && visibleMenu) {
						visibleCategory = !visibleCategory;
						visibleMenu = !visibleMenu;
						menuBG.setVisible(false);
						hotMenuBtn.setVisible(false);
						binnenlandMenuBtn.setVisible(false);
						buitenlandMenuBtn.setVisible(false);
						sportMenuBtn.setVisible(false);
						cultuurMenuBtn.setVisible(false);
						economieMenuBtn.setVisible(false);
						themeBtn.setVisible(false);
						settingsBtn.setVisible(false);
						exitBtn.setVisible(false);
						categoryMenuBG.setVisible(false);
						hotCategoryBtn.setVisible(false);
						binnenlandCategoryBtn[0].setVisible(false);
						binnenlandCategoryBtn[1].setVisible(false);
						buitenlandCategoryBtn[0].setVisible(false);
						buitenlandCategoryBtn[1].setVisible(false);
						sportCategoryBtn[0].setVisible(false);
						sportCategoryBtn[1].setVisible(false);
						cultuurCategoryBtn[0].setVisible(false);
						cultuurCategoryBtn[1].setVisible(false);
						economieCategoryBtn.setVisible(false);
					}
					else if(visibleMenu) {
						visibleMenu = !visibleMenu;
						menuBG.setVisible(visibleMenu);
						themeBtn.setVisible(visibleMenu);
						settingsBtn.setVisible(visibleMenu);
						exitBtn.setVisible(visibleMenu);
						if(CategoryContainer.getCategory() == "Binnenland") {
							binnenlandMenuBtn.setVisible(visibleMenu);
						}
						else if(CategoryContainer.getCategory() == "Buitenland") {
							buitenlandMenuBtn.setVisible(visibleMenu);
						}
						else if(CategoryContainer.getCategory() == "Sport") {
							sportMenuBtn.setVisible(visibleMenu);
						}
						else if(CategoryContainer.getCategory() == "Cultuur") {
							cultuurMenuBtn.setVisible(visibleMenu);
						}
						else if(CategoryContainer.getCategory() == "Economie") {
							economieMenuBtn.setVisible(visibleMenu);
						}
						else {
							hotMenuBtn.setVisible(visibleMenu);
						}
					}
					else {
						visibleMenu = !visibleMenu;
						menuBG.setVisible(visibleMenu);
						themeBtn.setVisible(visibleMenu);
						settingsBtn.setVisible(visibleMenu);
						exitBtn.setVisible(visibleMenu);
						if(CategoryContainer.getCategory() == "Binnenland") {
							binnenlandMenuBtn.setVisible(visibleMenu);
						}
						else if(CategoryContainer.getCategory() == "Buitenland") {
							buitenlandMenuBtn.setVisible(visibleMenu);
						}
						else if(CategoryContainer.getCategory() == "Sport") {
							sportMenuBtn.setVisible(visibleMenu);
						}
						else if(CategoryContainer.getCategory() == "Cultuur") {
							cultuurMenuBtn.setVisible(visibleMenu);
						}
						else if(CategoryContainer.getCategory() == "Economie") {
							economieMenuBtn.setVisible(visibleMenu);
						}
						else {
							hotMenuBtn.setVisible(visibleMenu);
						}
					}
					break;
				default:
					break;
				}
			}
		});
		hotMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					visibleCategory = !visibleCategory;
					categoryMenuBG.setVisible(visibleCategory);
					if(CategoryContainer.getCategory() == "Binnenland") {
						hotCategoryBtn.setVisible(visibleCategory);
						buitenlandCategoryBtn[0].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Buitenland") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Sport") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Cultuur") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[1].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Economie") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[1].setVisible(visibleCategory);
						cultuurCategoryBtn[1].setVisible(visibleCategory);
					}
					else {
						binnenlandCategoryBtn[0].setVisible(visibleCategory);
						buitenlandCategoryBtn[0].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					break;
				default:
					break;
				}
			}
		});
		binnenlandMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					visibleCategory = !visibleCategory;
					categoryMenuBG.setVisible(visibleCategory);
					if(CategoryContainer.getCategory() == "Binnenland") {
						hotCategoryBtn.setVisible(visibleCategory);
						buitenlandCategoryBtn[0].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Buitenland") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Sport") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Cultuur") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[1].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Economie") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[1].setVisible(visibleCategory);
						cultuurCategoryBtn[1].setVisible(visibleCategory);
					}
					else {
						binnenlandCategoryBtn[0].setVisible(visibleCategory);
						buitenlandCategoryBtn[0].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					break;
				default:
					break;
				}
			}
		});
		buitenlandMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					visibleCategory = !visibleCategory;
					categoryMenuBG.setVisible(visibleCategory);
					if(CategoryContainer.getCategory() == "Binnenland") {
						hotCategoryBtn.setVisible(visibleCategory);
						buitenlandCategoryBtn[0].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Buitenland") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Sport") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Cultuur") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[1].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Economie") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[1].setVisible(visibleCategory);
						cultuurCategoryBtn[1].setVisible(visibleCategory);
					}
					else {
						binnenlandCategoryBtn[0].setVisible(visibleCategory);
						buitenlandCategoryBtn[0].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					break;
				default:
					break;
				}
			}
		});
		sportMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					visibleCategory = !visibleCategory;
					categoryMenuBG.setVisible(visibleCategory);
					if(CategoryContainer.getCategory() == "Binnenland") {
						hotCategoryBtn.setVisible(visibleCategory);
						buitenlandCategoryBtn[0].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Buitenland") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Sport") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Cultuur") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[1].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Economie") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[1].setVisible(visibleCategory);
						cultuurCategoryBtn[1].setVisible(visibleCategory);
					}
					else {
						binnenlandCategoryBtn[0].setVisible(visibleCategory);
						buitenlandCategoryBtn[0].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					break;
				default:
					break;
				}
			}
		});
		cultuurMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					visibleCategory = !visibleCategory;
					categoryMenuBG.setVisible(visibleCategory);
					if(CategoryContainer.getCategory() == "Binnenland") {
						hotCategoryBtn.setVisible(visibleCategory);
						buitenlandCategoryBtn[0].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Buitenland") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Sport") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Cultuur") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[1].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Economie") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[1].setVisible(visibleCategory);
						cultuurCategoryBtn[1].setVisible(visibleCategory);
					}
					else {
						binnenlandCategoryBtn[0].setVisible(visibleCategory);
						buitenlandCategoryBtn[0].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					break;
				default:
					break;
				}
			}
		});
		economieMenuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					visibleCategory = !visibleCategory;
					categoryMenuBG.setVisible(visibleCategory);
					if(CategoryContainer.getCategory() == "Binnenland") {
						hotCategoryBtn.setVisible(visibleCategory);
						buitenlandCategoryBtn[0].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Buitenland") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Sport") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Cultuur") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[1].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					else if(CategoryContainer.getCategory() == "Economie") {
						hotCategoryBtn.setVisible(visibleCategory);
						binnenlandCategoryBtn[1].setVisible(visibleCategory);
						buitenlandCategoryBtn[1].setVisible(visibleCategory);
						sportCategoryBtn[1].setVisible(visibleCategory);
						cultuurCategoryBtn[1].setVisible(visibleCategory);
					}
					else {
						binnenlandCategoryBtn[0].setVisible(visibleCategory);
						buitenlandCategoryBtn[0].setVisible(visibleCategory);
						sportCategoryBtn[0].setVisible(visibleCategory);
						cultuurCategoryBtn[0].setVisible(visibleCategory);
						economieCategoryBtn.setVisible(visibleCategory);
					}
					break;
				default:
					break;
				}
			}
		});
		hotCategoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					visibleCategory = !visibleCategory;
					categoryMenuBG.setVisible(visibleCategory);
					hotCategoryBtn.setVisible(visibleCategory);
					binnenlandCategoryBtn[0].setVisible(visibleCategory);
					binnenlandCategoryBtn[1].setVisible(visibleCategory);
					buitenlandCategoryBtn[0].setVisible(visibleCategory);
					buitenlandCategoryBtn[1].setVisible(visibleCategory);
					sportCategoryBtn[0].setVisible(visibleCategory);
					sportCategoryBtn[1].setVisible(visibleCategory);
					cultuurCategoryBtn[0].setVisible(visibleCategory);
					cultuurCategoryBtn[1].setVisible(visibleCategory);
					economieCategoryBtn.setVisible(visibleCategory);
					hotMenuBtn.setVisible(visibleMenu);
					binnenlandMenuBtn.setVisible(visibleCategory);
					buitenlandMenuBtn.setVisible(visibleCategory);
					sportMenuBtn.setVisible(visibleCategory);
					cultuurMenuBtn.setVisible(visibleCategory);
					economieMenuBtn.setVisible(visibleCategory);
					break;
				default:
					break;
				}
			}
		});
		binnenlandCategoryBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					visibleCategory = !visibleCategory;
					categoryMenuBG.setVisible(visibleCategory);
					hotCategoryBtn.setVisible(visibleCategory);
					binnenlandCategoryBtn[0].setVisible(visibleCategory);
					binnenlandCategoryBtn[1].setVisible(visibleCategory);
					buitenlandCategoryBtn[0].setVisible(visibleCategory);
					buitenlandCategoryBtn[1].setVisible(visibleCategory);
					sportCategoryBtn[0].setVisible(visibleCategory);
					sportCategoryBtn[1].setVisible(visibleCategory);
					cultuurCategoryBtn[0].setVisible(visibleCategory);
					cultuurCategoryBtn[1].setVisible(visibleCategory);
					economieCategoryBtn.setVisible(visibleCategory);
					hotMenuBtn.setVisible(visibleCategory);
					binnenlandMenuBtn.setVisible(visibleMenu);
					buitenlandMenuBtn.setVisible(visibleCategory);
					sportMenuBtn.setVisible(visibleCategory);
					cultuurMenuBtn.setVisible(visibleCategory);
					economieMenuBtn.setVisible(visibleCategory);
					break;
				default:
					break;
				}
			}
		});
		binnenlandCategoryBtn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					visibleCategory = !visibleCategory;
					categoryMenuBG.setVisible(visibleCategory);
					hotCategoryBtn.setVisible(visibleCategory);
					binnenlandCategoryBtn[0].setVisible(visibleCategory);
					binnenlandCategoryBtn[1].setVisible(visibleCategory);
					buitenlandCategoryBtn[0].setVisible(visibleCategory);
					buitenlandCategoryBtn[1].setVisible(visibleCategory);
					sportCategoryBtn[0].setVisible(visibleCategory);
					sportCategoryBtn[1].setVisible(visibleCategory);
					cultuurCategoryBtn[0].setVisible(visibleCategory);
					cultuurCategoryBtn[1].setVisible(visibleCategory);
					economieCategoryBtn.setVisible(visibleCategory);
					hotMenuBtn.setVisible(visibleCategory);
					binnenlandMenuBtn.setVisible(visibleMenu);
					buitenlandMenuBtn.setVisible(visibleCategory);
					sportMenuBtn.setVisible(visibleCategory);
					cultuurMenuBtn.setVisible(visibleCategory);
					economieMenuBtn.setVisible(visibleCategory);
					break;
				default:
					break;
				}
			}
		});
		buitenlandCategoryBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					visibleCategory = !visibleCategory;
					categoryMenuBG.setVisible(visibleCategory);
					hotCategoryBtn.setVisible(visibleCategory);
					binnenlandCategoryBtn[0].setVisible(visibleCategory);
					binnenlandCategoryBtn[1].setVisible(visibleCategory);
					buitenlandCategoryBtn[0].setVisible(visibleCategory);
					buitenlandCategoryBtn[1].setVisible(visibleCategory);
					sportCategoryBtn[0].setVisible(visibleCategory);
					sportCategoryBtn[1].setVisible(visibleCategory);
					cultuurCategoryBtn[0].setVisible(visibleCategory);
					cultuurCategoryBtn[1].setVisible(visibleCategory);
					economieCategoryBtn.setVisible(visibleCategory);
					hotMenuBtn.setVisible(visibleCategory);
					binnenlandMenuBtn.setVisible(visibleCategory);
					buitenlandMenuBtn.setVisible(visibleMenu);
					sportMenuBtn.setVisible(visibleCategory);
					cultuurMenuBtn.setVisible(visibleCategory);
					economieMenuBtn.setVisible(visibleCategory);
					break;
				default:
					break;
				}
			}
		});
		buitenlandCategoryBtn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					visibleCategory = !visibleCategory;
					categoryMenuBG.setVisible(visibleCategory);
					hotCategoryBtn.setVisible(visibleCategory);
					binnenlandCategoryBtn[0].setVisible(visibleCategory);
					binnenlandCategoryBtn[1].setVisible(visibleCategory);
					buitenlandCategoryBtn[0].setVisible(visibleCategory);
					buitenlandCategoryBtn[1].setVisible(visibleCategory);
					sportCategoryBtn[0].setVisible(visibleCategory);
					sportCategoryBtn[1].setVisible(visibleCategory);
					cultuurCategoryBtn[0].setVisible(visibleCategory);
					cultuurCategoryBtn[1].setVisible(visibleCategory);
					economieCategoryBtn.setVisible(visibleCategory);
					hotMenuBtn.setVisible(visibleCategory);
					binnenlandMenuBtn.setVisible(visibleCategory);
					buitenlandMenuBtn.setVisible(visibleMenu);
					sportMenuBtn.setVisible(visibleCategory);
					cultuurMenuBtn.setVisible(visibleCategory);
					economieMenuBtn.setVisible(visibleCategory);
					break;
				default:
					break;
				}
			}
		});
		sportCategoryBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					visibleCategory = !visibleCategory;
					categoryMenuBG.setVisible(visibleCategory);
					hotCategoryBtn.setVisible(visibleCategory);
					binnenlandCategoryBtn[0].setVisible(visibleCategory);
					binnenlandCategoryBtn[1].setVisible(visibleCategory);
					buitenlandCategoryBtn[0].setVisible(visibleCategory);
					buitenlandCategoryBtn[1].setVisible(visibleCategory);
					sportCategoryBtn[0].setVisible(visibleCategory);
					sportCategoryBtn[1].setVisible(visibleCategory);
					cultuurCategoryBtn[0].setVisible(visibleCategory);
					cultuurCategoryBtn[1].setVisible(visibleCategory);
					economieCategoryBtn.setVisible(visibleCategory);
					hotMenuBtn.setVisible(visibleCategory);
					binnenlandMenuBtn.setVisible(visibleCategory);
					buitenlandMenuBtn.setVisible(visibleCategory);
					sportMenuBtn.setVisible(visibleMenu);
					cultuurMenuBtn.setVisible(visibleCategory);
					economieMenuBtn.setVisible(visibleCategory);
					break;
				default:
					break;
				}
			}
		});
		sportCategoryBtn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					visibleCategory = !visibleCategory;
					categoryMenuBG.setVisible(visibleCategory);
					hotCategoryBtn.setVisible(visibleCategory);
					binnenlandCategoryBtn[0].setVisible(visibleCategory);
					binnenlandCategoryBtn[1].setVisible(visibleCategory);
					buitenlandCategoryBtn[0].setVisible(visibleCategory);
					buitenlandCategoryBtn[1].setVisible(visibleCategory);
					sportCategoryBtn[0].setVisible(visibleCategory);
					sportCategoryBtn[1].setVisible(visibleCategory);
					cultuurCategoryBtn[0].setVisible(visibleCategory);
					cultuurCategoryBtn[1].setVisible(visibleCategory);
					economieCategoryBtn.setVisible(visibleCategory);
					hotMenuBtn.setVisible(visibleCategory);
					binnenlandMenuBtn.setVisible(visibleCategory);
					buitenlandMenuBtn.setVisible(visibleCategory);
					sportMenuBtn.setVisible(visibleMenu);
					cultuurMenuBtn.setVisible(visibleCategory);
					economieMenuBtn.setVisible(visibleCategory);
					break;
				default:
					break;
				}
			}
		});
		cultuurCategoryBtn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					visibleCategory = !visibleCategory;
					categoryMenuBG.setVisible(visibleCategory);
					hotCategoryBtn.setVisible(visibleCategory);
					binnenlandCategoryBtn[0].setVisible(visibleCategory);
					binnenlandCategoryBtn[1].setVisible(visibleCategory);
					buitenlandCategoryBtn[0].setVisible(visibleCategory);
					buitenlandCategoryBtn[1].setVisible(visibleCategory);
					sportCategoryBtn[0].setVisible(visibleCategory);
					sportCategoryBtn[1].setVisible(visibleCategory);
					cultuurCategoryBtn[0].setVisible(visibleCategory);
					cultuurCategoryBtn[1].setVisible(visibleCategory);
					economieCategoryBtn.setVisible(visibleCategory);
					hotMenuBtn.setVisible(visibleCategory);
					binnenlandMenuBtn.setVisible(visibleCategory);
					buitenlandMenuBtn.setVisible(visibleCategory);
					sportMenuBtn.setVisible(visibleCategory);
					cultuurMenuBtn.setVisible(visibleMenu);
					economieMenuBtn.setVisible(visibleCategory);
					break;
				default:
					break;
				}
			}
		});
		cultuurCategoryBtn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					visibleCategory = !visibleCategory;
					categoryMenuBG.setVisible(visibleCategory);
					hotCategoryBtn.setVisible(visibleCategory);
					binnenlandCategoryBtn[0].setVisible(visibleCategory);
					binnenlandCategoryBtn[1].setVisible(visibleCategory);
					buitenlandCategoryBtn[0].setVisible(visibleCategory);
					buitenlandCategoryBtn[1].setVisible(visibleCategory);
					sportCategoryBtn[0].setVisible(visibleCategory);
					sportCategoryBtn[1].setVisible(visibleCategory);
					cultuurCategoryBtn[0].setVisible(visibleCategory);
					cultuurCategoryBtn[1].setVisible(visibleCategory);
					economieCategoryBtn.setVisible(visibleCategory);
					hotMenuBtn.setVisible(visibleCategory);
					binnenlandMenuBtn.setVisible(visibleCategory);
					buitenlandMenuBtn.setVisible(visibleCategory);
					sportMenuBtn.setVisible(visibleCategory);
					cultuurMenuBtn.setVisible(visibleMenu);
					economieMenuBtn.setVisible(visibleCategory);
					break;
				default:
					break;
				}
			}
		});
		economieCategoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(e.getID()) {
				case TapEvent.BUTTON_CLICKED:
					visibleCategory = !visibleCategory;
					categoryMenuBG.setVisible(visibleCategory);
					hotCategoryBtn.setVisible(visibleCategory);
					binnenlandCategoryBtn[0].setVisible(visibleCategory);
					binnenlandCategoryBtn[1].setVisible(visibleCategory);
					buitenlandCategoryBtn[0].setVisible(visibleCategory);
					buitenlandCategoryBtn[1].setVisible(visibleCategory);
					sportCategoryBtn[0].setVisible(visibleCategory);
					sportCategoryBtn[1].setVisible(visibleCategory);
					cultuurCategoryBtn[0].setVisible(visibleCategory);
					cultuurCategoryBtn[1].setVisible(visibleCategory);
					economieCategoryBtn.setVisible(visibleCategory);
					hotMenuBtn.setVisible(visibleCategory);
					binnenlandMenuBtn.setVisible(visibleCategory);
					buitenlandMenuBtn.setVisible(visibleCategory);
					sportMenuBtn.setVisible(visibleCategory);
					cultuurMenuBtn.setVisible(visibleCategory);
					economieMenuBtn.setVisible(visibleMenu);
					break;
				default:
					break;
				}
			}
		});
		//Alle elementen toevoegen op het canvas.
		this.getCanvas().addChild(bg);
		this.getCanvas().addChild(categoryMenuBG);
		this.getCanvas().addChild(menuBG);
		this.getCanvas().addChild(logoBtn);
		this.getCanvas().addChild(hotMenuBtn);
		this.getCanvas().addChild(binnenlandMenuBtn);
		this.getCanvas().addChild(buitenlandMenuBtn);
		this.getCanvas().addChild(sportMenuBtn);
		this.getCanvas().addChild(cultuurMenuBtn);
		this.getCanvas().addChild(economieMenuBtn);
		this.getCanvas().addChild(themeBtn);
		this.getCanvas().addChild(settingsBtn);
		this.getCanvas().addChild(exitBtn);
		this.getCanvas().addChild(hotCategoryBtn);
		this.getCanvas().addChild(binnenlandCategoryBtn[0]);
		this.getCanvas().addChild(binnenlandCategoryBtn[1]);
		this.getCanvas().addChild(buitenlandCategoryBtn[0]);
		this.getCanvas().addChild(buitenlandCategoryBtn[1]);
		this.getCanvas().addChild(sportCategoryBtn[0]);
		this.getCanvas().addChild(sportCategoryBtn[1]);
		this.getCanvas().addChild(cultuurCategoryBtn[0]);
		this.getCanvas().addChild(cultuurCategoryBtn[1]);
		this.getCanvas().addChild(economieCategoryBtn);
		//Plaats van de menu-achtergronden op het canvas instellen
		logoBtn.setPositionGlobal(new Vector3D(mtApp.width - logoBtn.getWidthXY(TransformSpace.GLOBAL) / 2, logoBtn.getHeightXY(TransformSpace.GLOBAL) / 2));
		menuBG.setPositionGlobal(new Vector3D(mtApp.width - menuBG.getWidthXY(TransformSpace.GLOBAL) / 2 + 22, menuBG.getHeightXY(TransformSpace.GLOBAL) / 2 - 23));
		categoryMenuBG.setPositionGlobal(new Vector3D(mtApp.width - categoryMenuBG.getWidthXY(TransformSpace.GLOBAL) / 2, categoryMenuBG.getHeightXY(TransformSpace.GLOBAL) / 2));
		//Buttons eerste menu
		hotMenuBtn.setPositionGlobal(new Vector3D(mtApp.width - hotMenuBtn.getWidthXY(TransformSpace.GLOBAL) / 2 - 205, hotMenuBtn.getHeightXY(TransformSpace.GLOBAL) / 2));
		binnenlandMenuBtn.setPositionGlobal(new Vector3D(mtApp.width - binnenlandMenuBtn.getWidthXY(TransformSpace.GLOBAL) / 2 - 205, binnenlandMenuBtn.getHeightXY(TransformSpace.GLOBAL) / 2));
		buitenlandMenuBtn.setPositionGlobal(new Vector3D(mtApp.width - buitenlandMenuBtn.getWidthXY(TransformSpace.GLOBAL) / 2 - 205, buitenlandMenuBtn.getHeightXY(TransformSpace.GLOBAL) / 2 + 5));
		sportMenuBtn.setPositionGlobal(new Vector3D(mtApp.width - sportMenuBtn.getWidthXY(TransformSpace.GLOBAL) / 2 - 210, sportMenuBtn.getHeightXY(TransformSpace.GLOBAL) / 2 + 10));
		cultuurMenuBtn.setPositionGlobal(new Vector3D(mtApp.width - cultuurMenuBtn.getWidthXY(TransformSpace.GLOBAL) / 2 - 205, cultuurMenuBtn.getHeightXY(TransformSpace.GLOBAL) / 2 + 5));
		economieMenuBtn.setPositionGlobal(new Vector3D(mtApp.width - economieMenuBtn.getWidthXY(TransformSpace.GLOBAL) / 2 - 215, economieMenuBtn.getHeightXY(TransformSpace.GLOBAL) / 2 + 5));
		themeBtn.setPositionGlobal(new Vector3D(mtApp.width - themeBtn.getWidthXY(TransformSpace.GLOBAL) / 2 - 180, themeBtn.getHeightXY(TransformSpace.GLOBAL) / 2 + 90));
		settingsBtn.setPositionGlobal(new Vector3D(mtApp.width - settingsBtn.getWidthXY(TransformSpace.GLOBAL) / 2 - 115, settingsBtn.getHeightXY(TransformSpace.GLOBAL) / 2 + 160));
		exitBtn.setPositionGlobal(new Vector3D(mtApp.width - exitBtn.getWidthXY(TransformSpace.GLOBAL) / 2 - 20, exitBtn.getHeightXY(TransformSpace.GLOBAL) / 2 + 210));
		//Buttons category menu
		hotCategoryBtn.setPositionGlobal(new Vector3D(mtApp.width - hotCategoryBtn.getWidthXY(TransformSpace.GLOBAL) / 2 - 312, hotCategoryBtn.getHeightXY(TransformSpace.GLOBAL) / 2));
		binnenlandCategoryBtn[0].setPositionGlobal(new Vector3D(mtApp.width - binnenlandCategoryBtn[0].getWidthXY(TransformSpace.GLOBAL) / 2 - 312, binnenlandCategoryBtn[0].getHeightXY(TransformSpace.GLOBAL) / 2 + 10));
		binnenlandCategoryBtn[1].setPositionGlobal(new Vector3D(mtApp.width - binnenlandCategoryBtn[1].getWidthXY(TransformSpace.GLOBAL) / 2 - 280, binnenlandCategoryBtn[1].getHeightXY(TransformSpace.GLOBAL) / 2 + 120));
		buitenlandCategoryBtn[0].setPositionGlobal(new Vector3D(mtApp.width - buitenlandCategoryBtn[0].getWidthXY(TransformSpace.GLOBAL) / 2 - 280, buitenlandCategoryBtn[0].getHeightXY(TransformSpace.GLOBAL) / 2 + 120));
		buitenlandCategoryBtn[1].setPositionGlobal(new Vector3D(mtApp.width - buitenlandCategoryBtn[1].getWidthXY(TransformSpace.GLOBAL) / 2 - 220, buitenlandCategoryBtn[1].getHeightXY(TransformSpace.GLOBAL) / 2 + 210));
		sportCategoryBtn[0].setPositionGlobal(new Vector3D(mtApp.width - sportCategoryBtn[0].getWidthXY(TransformSpace.GLOBAL) / 2 - 222, sportCategoryBtn[0].getHeightXY(TransformSpace.GLOBAL) / 2 + 217));
		sportCategoryBtn[1].setPositionGlobal(new Vector3D(mtApp.width - sportCategoryBtn[1].getWidthXY(TransformSpace.GLOBAL) / 2 - 128, sportCategoryBtn[1].getHeightXY(TransformSpace.GLOBAL) / 2 + 280));
		cultuurCategoryBtn[0].setPositionGlobal(new Vector3D(mtApp.width - cultuurCategoryBtn[0].getWidthXY(TransformSpace.GLOBAL) / 2 - 130, cultuurCategoryBtn[0].getHeightXY(TransformSpace.GLOBAL) / 2 + 277));
		cultuurCategoryBtn[1].setPositionGlobal(new Vector3D(mtApp.width - cultuurCategoryBtn[1].getWidthXY(TransformSpace.GLOBAL) / 2 - 27, cultuurCategoryBtn[1].getHeightXY(TransformSpace.GLOBAL) / 2 + 317));
		economieCategoryBtn.setPositionGlobal(new Vector3D(mtApp.width - economieCategoryBtn.getWidthXY(TransformSpace.GLOBAL) / 2 - 30, economieCategoryBtn.getHeightXY(TransformSpace.GLOBAL) / 2 + 317));
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

