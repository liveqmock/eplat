/**
 * obullxl@gmail.com
 */
package mplat.uijfx;

import javafx.scene.image.Image;

/**
 * @author obullxl@gmail.com
 */
public class ImageHolder {

    private static final ImageHolder _holder = new ImageHolder();

    private ImageHolder() {
    }

    public static final ImageHolder get() {
        return _holder;
    }
    private Image loginLogo;

    public Image findLoginLogo() {
        if (this.loginLogo == null) {
            this.loginLogo = new Image(this.getClass().getResourceAsStream("images/logo.jpg"));
        }

        return this.loginLogo;
    }
}
