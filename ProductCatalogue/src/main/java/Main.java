import controller.ProductCatelogueController;
import service.ProductCatelogueService;

/**
 * Created by janani.sampathkumar on 08/02/2017.
 */
public class Main {

    public static void main(String[] args) {
        new ProductCatelogueController(new ProductCatelogueService());
    }

}
