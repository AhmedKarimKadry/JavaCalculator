module JavaFXCalculator {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	
	opens mycalculator to javafx.graphics, javafx.fxml;
}
