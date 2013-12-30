
import org.mt4j.MTApplication;

import scenes.MainNinjaScene;

public class StartNinjaNieuws extends MTApplication {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		initialize();
	}
	
	@Override
	public void startUp() {
		addScene(new MainNinjaScene(this, "Ninjanieuws"));
	}
}
