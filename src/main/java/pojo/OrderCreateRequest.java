package pojo;

public class OrderCreateRequest {
    private String[] ingredients;

//    public OrderCreateRequest(String[] ingredients) {
//        this.ingredients = ingredients;
//    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }
}
