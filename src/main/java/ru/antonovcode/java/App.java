package ru.antonovcode.java;


import ru.antonovcode.java.controller.Controller;
import ru.antonovcode.java.view.Window;

/**
 * Hello world.
 *
 * @author username
 */
public final class App {
    //private static final Logger LOG = LoggerFactory.getLogger(App.class);
    private static final int SIZE = 20;

    private App() {
    }

    /**
     * Enter point in application.
     *
     * @param args application parameters
     */
    public static void main(final String[] args) {
        //final String greeting = "Hello World!";

        //System.out.println(greeting);
        //LOG.info(greeting);

        Window window = new Window();
        Controller controller = new Controller(window);
        window.setVisible(true);
    }
}
