import controller.MainController;
import view.MainView;

public class App {
    public static void main(String[] args) {
        MainView view = new MainView();
        new MainController(view);
    }
}