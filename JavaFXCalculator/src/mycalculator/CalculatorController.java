package mycalculator;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.lang.Math;

/*
 * JavaFX Calculator
 * Controller Class
 * Author: Ahmed Kadry
 * This class contains the actual code controlling the calculator
 * Uses multiple methods
 */

public class CalculatorController {
	
	
	//declare variables
	String operation2= "";
	double number1,number2,x;
	
	//declare TextField and annotation
	@FXML
	private TextField displayField;
	
	
	//method to recieve button clicks
	//and what they correspond to
	public void buttonClickHandler(ActionEvent evt) {
		
		Button clickedButton = (Button) evt.getTarget(); //get clicked button from target
		String buttonLabel = clickedButton.getText(); //find out text of clicked button
		
		
		
		//Tell apart digits from operations
		switch(buttonLabel) {
		case "0": 
		case "1": 
		case "2": 
		case "3": 
		case "4": 
		case "5":
		case "6": 
		case "7": 
		case "8": 
		case "9": 
		case "10": 
		case ".":
		case "π":
		case "e":
			processDigit(buttonLabel);
			break;
		case "+":
		case "-":
		case "X":
		case "/":
		case "=":
			processOperation(buttonLabel);
			break;
		case "C":
			displayField.clear();
			break;
		case "+-":
			//switch from negative to positive and vice versa
			//get current number and convert to double
			String temp = displayField.getText();
			double tempNum = Double.parseDouble(temp);
			//switch sign
			tempNum*=-1;
			
			//check if there are decimals, then print
			if (tempNum%1!=0) {
				displayField.setText(Double.toString(tempNum));
			} else {
				displayField.setText(Integer.toString((int)(tempNum)));
			}
			break;
			
        case "MC":
        case "M+":
        case "M-":
        case "MR":
            processMemory(buttonLabel);
            break;
            
            
        case "x²":
        //set x to display text	
        x = Double.parseDouble(displayField.getText());
        
        //square x
        x = x*x;
        
        //clear display
        displayField.setText("");
        
        //display squared X, check if there are decimals and remove appropriatley
		if (x%1!=0) {
			displayField.setText(Double.toString(x));
		} else {
			displayField.setText(Integer.toString((int)(x)));
		}
        break;
			
        
        case "√x":
        x = Double.parseDouble(displayField.getText());	
        
        //square root x
        x = Math.sqrt(x);
        //clear display
        displayField.setText("");
        
        //display squared X, check if there are decimals and remove appropriatley
		if (x%1!=0) {
			displayField.setText(Double.toString(x));
		} else {
			displayField.setText(Integer.toString((int)(x)));
		}
        break;
     
		}
	}
	
	
	
	
	
	//display selected button in console
	//takes in String parameter
	private void processDigit (String buttonLabel) {
		
		displayField.setText(displayField.getText()+ buttonLabel);
	}
	
	
	
	
	//display selected operation in console
	//takes in String parameter
	private void processOperation (String buttonLabel) {
//		displayField.setText(buttonLabel);
		System.out.printf("You selected operation %s",buttonLabel);
		
		//label operation pressed
		String operation = buttonLabel;
		
		//if operation is not =
		if (!operation.equals("=")) {
			
			operation2 = operation;
			
			//get first number entered after the operation is chosen
			// regardless of what the operation is
			//check if pi was clicked, to set number
			if (displayField.getText().equals("π")) {
				number1 = Math.PI;
				
			//if e was chosen, set number 1 to eulers number	
			}else if (displayField.getText().equals("e")) {
				number1 = Math.exp(1);
				
			} else {
				number1 = Double.parseDouble(displayField.getText());
			}
			
			//clear the screen once the operation is clicked
			displayField.setText("");
			
			
		// if the operation pressed is =
		} else {
			
			
			//store the second number from the calculation after = is pressed
			if (displayField.getText().equals("π")) {
				number2 = Math.PI;
				
			//if e was chosen, set number 2 to eulers number	
			}else if (displayField.getText().equals("e")) {
					number2 = Math.exp(1);	
					
			} else {
				number2 = Double.parseDouble(displayField.getText());
			}
			
			//call calculate method
			// pass both numbers and which operation
			calculate(number1, number2, operation2);
			
			//reset operation
			operation2 = "";	
		}
		}
	
	//Method for memory buttons
	//takes in buttonLabel string
	public void processMemory(String buttonLabel) {
		
		//Set memory value
		String memory = displayField.getText();
		
		
		//clear if memory clear
		if (buttonLabel.equals("MC")) {
			memory = "";
			
			//display memory if memory recall
		} else if ((buttonLabel).equals("MR")) {
			displayField.setText(memory);
			
			//add to memory
		} else if (buttonLabel.equals("M+")){
			calculate(Double.parseDouble(displayField.getText()),
					Double.parseDouble(memory), "+");
			
			//subtract from memory
		}else {
			calculate(Double.parseDouble(displayField.getText()),
					Double.parseDouble(memory), "-");
		}
	}
	
	
	
	//method to calculate result
	//takes 2 numbers as doubles
	//takes string representing the operation to be done
	public void calculate (double num1, double num2, String operation) {
		
		// variable to store result of calculation
		double result;
		
		//go through possible operations
		switch (operation) {
		
		case "+":
			// add numbers if plus is chosen
			result = (num1+num2);
			System.out.println((int)(result));
			
			//check if result is whole
			// if whole, then % will return 0, and will print as int without decimals
			// repeated for each case
			if (result%1!=0) {
				displayField.setText(Double.toString(result));
			} else {
				displayField.setText(Integer.toString((int)(result)));
			}
			
			
			break;
		case "-":
			
			//subtract numbers
			result = (num1-num2);

			if (result%1!=0) {
				displayField.setText(Double.toString(result));
			} else {
				displayField.setText(Integer.toString((int)(result)));
			}
			
			break;
			
		case "X":
			//multiply numbers
			result =(num1*num2);

			
			if (result%1!=0) {
				displayField.setText(Double.toString(result));
			} else {
				displayField.setText(Integer.toString((int)(result)));
			}
			
			break;
			
			
		case "/":
			
			//check if dividing by zero
			// if yes, answer is not possible
			if (num2 ==0) {
				displayField.setText("ERROR");
				break;
			} else {
				//divide numbers
				result = (num1/num2);

				if (result%1!=0) {
					displayField.setText(Double.toString(result));
				} else {
					displayField.setText(Integer.toString((int)(result)));
				}
				
			}
		}
		
	}	
	
	

	}


