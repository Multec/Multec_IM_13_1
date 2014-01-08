package supportClasses;

public class CategoryContainer {

	private static String category = "Hot";

	public static String getCategory() {
		return category;
	}

	public static void setCategory(String _category) {
		CategoryContainer.category = _category;
	}
	
}
