
import org.mt4j.MTApplication;

import scenes.MainNinjaScene;
import scenes.NinjaMeerOverDeApp;
import scenes.NinjaSettings;
import scenes.NinjaVideoScene;

public class StartNinjaNieuws extends MTApplication {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		initialize();
	}
	
	@Override
	public void startUp() {
		//addScene(new MainNinjaScene(this, "Ninjanieuws"));
		addScene(new NinjaSettings(this, "NinjaSettings"));
		//addScene(new NinjaVideoScene(this, "NinjaVideos"));
		//addScene(new NinjaMeerOverDeApp(this, "NinjaMeerOverDeApp"));
	}
}
