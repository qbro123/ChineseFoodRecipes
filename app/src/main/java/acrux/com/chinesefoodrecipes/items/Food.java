package acrux.com.chinesefoodrecipes.items;

/**
 * Created by Kelvin_Nguyen on 1/12/2016.
 */

public class Food {
    public static String KEY_NAME="name";
    public static String KEY_INGREDIENTS="ingredients";
    public static String KEY_SAUCE="sauce";
    public static String KEY_METHOD="method";
    public static String KEY_IMAGE= "image";

    private int id;
    private String name , ingredients, sauce , method , nutrition;
    private byte[] image;

    public Food(int id, String name, String ingredients, String sauce, String method, String nutrition, byte[] image) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.sauce = sauce;
        this.method = method;
        this.nutrition = nutrition;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getSauce() {
        return sauce;
    }

    public String getMethod() {
        return method;
    }

    public String getNutrition() {
        return nutrition;
    }

    public byte[] getImage() {
        return image;
    }
}
