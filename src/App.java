import java.awt.EventQueue;

import controller.GuiBuilder;
import controller.DatabaseHandler;
import view.GuiWindow;

public class App {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiBuilder builder = new GuiBuilder();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
