/**
 * obullxl@gmail.com
 */
package mplat.uijfx.images;

import javafx.scene.image.Image;

/**
 * @author obullxl@gmail.com
 */
public class IMGS {
    private static Image LOGIN_LOGO;
    private static Image WELCOME_LOGO;
    private static Image WELCOME_COPY_RGT;

    private static Image EXIT;

    public static Image findLoginLogo() {
        if(LOGIN_LOGO == null) {
            LOGIN_LOGO = new Image(IMGS.class.getResourceAsStream("login-logo.jpg"));
        }

        return LOGIN_LOGO;
    }

    public static Image findWelcomeLogo() {
        if(WELCOME_LOGO == null) {
            WELCOME_LOGO = new Image(IMGS.class.getResourceAsStream("welcome-logo.jpg"));
        }

        return WELCOME_LOGO;
    }

    public static Image findWelcomeCopyRgt() {
        if(WELCOME_COPY_RGT == null) {
            WELCOME_COPY_RGT = new Image(IMGS.class.getResourceAsStream("welcome-copyrgt.jpg"));
        }

        return WELCOME_COPY_RGT;
    }

    public static Image findExit() {
        if(EXIT == null) {
            EXIT = new Image(IMGS.class.getResourceAsStream("exit.jpg"));
        }

        return EXIT;
    }

}
