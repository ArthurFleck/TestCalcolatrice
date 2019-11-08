package it.uniroma2.ispw;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calcolatrice extends Application {
	
	private Button btt0;
	private Button btt1;
	private Button btt2;
	private Button btt3;
	private Button btt4;
	private Button btt5;
	private Button btt6;
	private Button btt7;
	private Button btt8;
	private Button btt9;
	private Button sum;
	private Button sub;
	private Button div;
	private Button mul;
	private Button equ;
	private Button com;
	private Button canc;
	private Button del;
	private Button sign;
	private Button percent;
	private Button memp;
	private Button memm;
	private Button memrel;
	private Button memcanc;
	private Label ris1, ris2;
	
	
	private Double operand1, operand2, result, mem;
	
	private boolean digits, memory;
	
	private String operand, operation;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		operand = "";
		operation = "";
		digits = false;
		memory = false;
		
		VBox root = new VBox();
		
		ris1 = new Label("");
		ris2 = new Label("");
		ris1.setAlignment(Pos.BASELINE_RIGHT);
		//ris1.setStyle("-fx-border-style: solid; -fx-max-width:300; -fx-border-insets: 0 0 10 0;");
		ris1.setStyle("-fx-max-width:300; -fx-border-insets: 0 0 10 0;");
		ris2.setAlignment(Pos.BASELINE_RIGHT);
		ris2.setStyle("-fx-border-style: solid; -fx-max-width:290;");
		root.setAlignment(Pos.CENTER);
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		
		creaButton();
		
		grid.add(memcanc, 0, 0);
		grid.add(memp, 1, 0);
		grid.add(memm, 2, 0);
		grid.add(memrel, 3, 0);
		
		grid.add(canc, 0, 1);
		grid.add(sign, 1, 1);
		grid.add(percent, 2, 1);
		grid.add(del, 3, 1);
		
		grid.add(btt7, 0, 2);
		grid.add(btt8, 1, 2);
		grid.add(btt9, 2, 2);
		grid.add(div, 3, 2);
		
		grid.add(btt4, 0, 3);
		grid.add(btt5, 1, 3);
		grid.add(btt6, 2, 3);
		grid.add(mul, 3, 3);
		
		grid.add(btt1, 0, 4);
		grid.add(btt2, 1, 4);
		grid.add(btt3, 2, 4);
		grid.add(sub, 3, 4);
		
		grid.add(btt0, 0, 5);
		grid.add(com, 1, 5);
		grid.add(equ, 2, 5);
		grid.add(sum, 3, 5);
		
		grid.setPrefSize(250, 250);
		grid.setHgap(10);
		grid.setVgap(10);
		
		root.getChildren().addAll(ris1, ris2, grid);
		
		Scene scene = new Scene(root, 400, 600);
		scene.setCursor(Cursor.HAND);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private void creaButton() {
		
		btt0 = creaDigit("0");
		btt1 = creaDigit("1");
		btt2 = creaDigit("2");
		btt3 = creaDigit("3");
		btt4 = creaDigit("4");
		btt5 = creaDigit("5");
		btt6 = creaDigit("6");
		btt7 = creaDigit("7");
		btt8 = creaDigit("8");
		btt9 = creaDigit("9");
		com = creaDigit(",");
		
		sum = creaOperation("+");
		sub = creaOperation("-");
		div = creaOperation("/");
		mul = creaOperation("*");
		
		equ = new Button("=");
		equ.setStyle("-fx-pref-width: 5em;");
		equ.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(digits) {
					ris1.setText(ris1.getText()+operand+"=");
					operand2 = new Double(operand.replace(',', '.'));
					switch (operation) {
					case "+":
						result = operand1 + operand2;
						ris2.setText(result.toString());
						break;
					case "-":
						result = operand1 - operand2;
						ris2.setText(result.toString());
						break;
					case "/":
						result = operand1 / operand2;
						ris2.setText(result.toString());
						break;
					case "*":
						result = operand1 * operand2;
						ris2.setText(result.toString());
						break;
					default:
						break;
					}
					operation = "";
					operand = "";
					digits = false;
				}
			}
		});
		
		
		canc = new Button("C");
		canc.setStyle("-fx-pref-width: 5em;");
		canc.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				operand = "";
				operation = "";
				digits = false;
				ris1.setText("");
				ris2.setText("");
			}
		});
		
		del = new Button("DEL");
		del.setStyle("-fx-pref-width: 5em;");
		del.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (!operand.isEmpty()) {
					operand = operand.substring(0, operand.length() - 1);
					ris2.setText(operand);
				}
			}
		});
		
		sign = new Button("+/-");
		sign.setStyle("-fx-pref-width: 5em;");
		sign.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (operand.startsWith("-"))
					operand = operand.substring(1);
				else
					operand = "-"+operand;
				ris2.setText(operand);
			}
		});
		
		percent = new Button("%");
		percent.setStyle("-fx-pref-width: 5em;");
		percent.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(digits && operation == "*") {
					ris1.setText(ris1.getText()+operand+"%=");
					operand2 = new Double(operand.replace(',', '.'));
					result = (operand1 * operand2)/100;
					ris2.setText(result.toString());
					operation = "";
					operand = "";
					digits = false;
				}
			}
		});
		
		memp = new Button("M+");
		memp.setStyle("-fx-pref-width: 5em;");
		memp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (memory) {
					mem += new Double(operand.replace(',', '.'));
				} else {
					mem = new Double(operand.replace(',', '.'));
					memory = true;
				}
			}
		});
		
		memm = new Button("M-");
		memm.setStyle("-fx-pref-width: 5em;");
		memm.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (memory) {
					mem -= new Double(operand.replace(',', '.'));
				} else {
					mem = new Double(operand.replace(',', '.'));
					memory = true;
				}
			}
		});
		
		memrel = new Button("MR");
		memrel.setStyle("-fx-pref-width: 5em;");
		memrel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (memory)
					ris2.setText(mem.toString());
			}
		});
		
		memcanc = new Button("MC");
		memcanc.setStyle("-fx-pref-width: 5em;");
		memcanc.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				memory = false;
			}
		});
		
	}
	
	private Button creaDigit(String value) {
		Button b;
		b = new Button(value);
		b.setStyle("-fx-pref-width: 5em; -fx-pref-height: 2em;");
		b.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				operand += value;
				ris2.setText(operand);
				if (!digits)
					digits = true;
			}
		});
		return b;
	}
	
	private Button creaOperation(String value) {
		Button b;
		b = new Button(value);
		b.setStyle("-fx-pref-width: 5em;");
		b.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if(digits) {
					ris1.setText(operand+value);
					ris2.setText("");
					operand1 = new Double(operand.replace(',', '.'));
					operation = value;
					operand = "";
					digits = false;
				}
			}
		});
		return b;
	}
	
}
