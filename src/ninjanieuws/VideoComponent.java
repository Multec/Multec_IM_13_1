package ninjanieuws;

import org.mt4j.components.visibleComponents.widgets.video.MTMovieClip;
import org.mt4j.util.math.Vertex;

import processing.core.PApplet;

public class VideoComponent extends MTMovieClip {

	public VideoComponent(String movieFile, Vertex upperLeft, PApplet pApplet) {
		super(movieFile, upperLeft, pApplet);
	}
}
