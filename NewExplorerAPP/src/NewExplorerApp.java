
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import dao.*;
import mvc.*;

public class NewExplorerApp {
    public static void main(String[] args){
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			Font font = new Font("Serif",0,12);
			Font textFont = new Font("Serif",0,16);
			UIManager.put("ComboBox.font",font);
			UIManager.put("List.font",font);
			UIManager.put("Button.font",font);
			UIManager.put("Button.foreground",Color.red);
			UIManager.put("RadioButton.font",font);
			UIManager.put("Label.font",font);
			UIManager.put("Menu.font",font);
			UIManager.put("MenuItem.font",font);
			UIManager.put("Panel.font",font);
			UIManager.put("TitledBorder.font",font);
			UIManager.put("JTextArea.font",Color.red);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		FileDAO fdao = new FileDAO();
		FileModelImpl model = new FileModelImpl(fdao);
		FileViewImpl view = new FileViewImpl(model);
		FileControllerImpl controller = new FileControllerImpl(view,model);
    }
}
