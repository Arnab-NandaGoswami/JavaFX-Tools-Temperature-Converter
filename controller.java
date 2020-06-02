package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class controller implements Initializable {

	@FXML
	public Label lblWelcome;

	@FXML
	public ChoiceBox choiceBox;
	private static final String C_to_F_Text="Celcius To Farenhite";
	private static final String F_to_C_Text="Farenhite To Celcius";

	@FXML
	public TextField txtField;

	@FXML
	public Button btnConvert;

	boolean isC_to_f=true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		choiceBox.getItems().add(C_to_F_Text);
		choiceBox.getItems().add(F_to_C_Text);

		choiceBox.setValue(C_to_F_Text);

		choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

			if (newValue.equals(C_to_F_Text)){
				isC_to_f=true;
			}else {
				isC_to_f=false;
			}

		});

		btnConvert.setOnAction(event -> {

			convert();


		});

	}

	private void convert() {
		String input = txtField.getText();
		float temperature=0.0f;
		try {
			temperature = Float.parseFloat(input);
		}catch (Exception exception){
			warnUser();
			txtField.setText("");
			return;
		}

		float newTemperature = 0.0f;

		if (isC_to_f==true){
			newTemperature=(temperature*9/5)+32;
		}else {
			newTemperature=(temperature-32)*5/9;
		}
		display(newTemperature);

		txtField.setText("");
	}

	private void warnUser() {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setTitle("!!!!!!ERROR!!!!!!");
		alert.setHeaderText("-: Error :-");
		alert.setContentText("Enter Correct Number");
		alert.show();
	}

	private void display(float newTemperature) {
		String unit = isC_to_f?"F":"C";
		String parameter = isC_to_f?"Farenhite":"Celcius";
		System.out.print("The Temperature in "+parameter+" is= "+newTemperature+unit);
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result of the conversion");
		alert.setHeaderText("-: Conversion Result :-");
		alert.setContentText("The Temperature in "+parameter+" is= "+newTemperature+unit);
		alert.show();
	}
}
