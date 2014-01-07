package supportClasses;

public class CategoryContainer {

	private static String category = "Hot";
	
	public CategoryContainer() {
		//Create container
	}

	public static String getCategory() {
		return category;
	}

	public static void setCategory(String _category) {
		CategoryContainer.category = _category;
	}
	
}
