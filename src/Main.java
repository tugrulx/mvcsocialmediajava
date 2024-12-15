import Controller.Sign.HomeSignController;
import Controller.UserInterface.HomeUserController;

public class Main {
    public static void main(String[] args) {
        HomeSignController controller = new HomeSignController();
        controller.run();
        while (!controller.IsLogged()) {
            try {
                Thread.sleep(1000); // 1 saniye bekle
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        controller.SetVisible(false);
        HomeUserController userController = new HomeUserController();
        userController.run();

    }
}
