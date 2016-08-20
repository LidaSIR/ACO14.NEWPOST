package newpost.gui;

import newpost.controller.ClientController;
import newpost.db.AppDataContainer;
import newpost.validator.ValidationClientControllerProxy;
import newpost.validator.Validator;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sasha on 18.08.2016.
 */
public class MakeOrderFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    private static final String TITLE = "make order";

    private AppDataContainer appDataContainer;
    private ValidationClientControllerProxy validationClientControllerProxy;
    private ClientController clientController;

    public MakeOrderFrame(AppDataContainer appDataContainer){
        this.appDataContainer = appDataContainer;
        this.clientController = new ClientController(appDataContainer);
        this.validationClientControllerProxy = new ValidationClientControllerProxy(clientController, new Validator());
    }

    public static JFrame createFirstFrame() {
        JFrame orderFrame = new JFrame();
        orderFrame.setTitle(TITLE);
        orderFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        //CENTER
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize(), fSize = orderFrame.getSize();

        if (fSize.height > sSize.height) {fSize.height = sSize.height;}
        if (fSize.width  > sSize.width)  {fSize.width = sSize.width;}
        orderFrame.setLocation ((sSize.width - fSize.width)/2,
                (sSize.height - fSize.height)/2);

        

        orderFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        orderFrame.setVisible(true);
        return orderFrame;
    }

    public static void main(String[] args) {
        createFirstFrame();
        //AppDataContainer app = new AppDataContainer();
        //ManagerView mv = new ManagerView(app);
        //mv.showManagerView();
    }
}
