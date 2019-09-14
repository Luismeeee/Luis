package Screen;

public class SceneManager {
    public static Screen currentScreen;

    public static void signNewScreen(Screen newScreen){
        if (currentScreen != null){
            currentScreen.clear();
        }
        newScreen.init();
        currentScreen = newScreen;
    }
}
