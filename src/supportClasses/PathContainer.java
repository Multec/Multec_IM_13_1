/*
 * Deze klasse dient om de verschillende filepath op te slaan
 * Het simuleert tevens een associatieve array
 * Dit om de verschillende buttons in het categorymenu op te stellen
 */
package supportClasses;

public class PathContainer {

	private String category;
	private String filePath;
	
	public PathContainer(String category, String filePath) {
		this.category = category;
		this.filePath = filePath;
	}

	public String getCategoryName() {
		return this.category;
	}

	public String getFilePath() {
		return this.filePath;
	}
}
