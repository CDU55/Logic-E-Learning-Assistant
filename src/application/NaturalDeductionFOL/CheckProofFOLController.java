package application.NaturalDeductionFOL;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Exceptions.InvalidRuleName;
import NaturalDeduction.NaturalDeductionFOL.ProofReaderFOL;
import application.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class CheckProofFOLController {
	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TextArea console;
	    

	    public void check()
	    {
					String checkResult;
					try {
						checkResult = ProofReaderFOL.checkProofString(console.getText().trim());
						AlertBox.display(checkResult);
					} catch (InvalidRuleName e) {
						AlertBox.display(e.getMessage());
					}
		}
	    	
			
	    
	    public void checkFromFile()
	    {
	    	try {
				FileChooser chooser=new FileChooser();
				chooser.getExtensionFilters().add(new ExtensionFilter("Text Files","*.txt"));
				File f=chooser.showOpenDialog(null);
				if(f!=null)
				{
						String checkResult=ProofReaderFOL.checkProofFromFile(f.getAbsolutePath());
						AlertBox.display(checkResult);
					
				}
	    	}
	    	catch (InvalidRuleName | IOException e) {
				AlertBox.display(e.getMessage());
			}
	    }
	    
	    public void back(ActionEvent event) throws IOException
	    {
	    	Parent deductionParent=FXMLLoader.load(getClass().getResource("NaturalDeductionFOL.fxml"));
	    	Scene deductionScene=new Scene(deductionParent);
	    	Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
	    	window.setScene(deductionScene);
	    	window.show();
	    }
}
