/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meteorblastergui;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import meteorblasterbackend.GamerProfile;
import meteorblasterbackend.GamerProfileCollection;
import meteorblasterbackend.MeteorIO;

/**
 *
 * @author Doetheman
 */
public class ProfilePane extends VBox{
    GamerProfileCollection profile; // Profiles of all registered players
    String profileFilename; // File containing profiles data
    String configFilename; // File containing Level data. Passed to GameUniverse
    Node controls; //placeholder for the controls used in your GUI
    private boolean verify; //Checks profile name
    private Button btnOK;
    private ComboBox comboB;
    private TextField textFN;
    private TextField textID;
    boolean IdFound = false;
    Button begin;
    Craft craft;
    public ProfilePane(String profileFileName, String configFileName){   
        //profile = new GamerProfile();
        


       
        comboB = new ComboBox();
        
        
        Label label_SelectGame = new Label("Select GamerID");
        
        Label label_info = new Label(" ");
        
        comboB.getItems().add("Register New Gamer");
        
        verify = false;
        
        Label lblID = new Label("Gamer ID:");
        textID = new TextField();
        
        Label lblFN = new Label("First Name:");
        textFN = new TextField();
        
        Label lblLN = new Label("Last Name:");
        TextField textLN = new TextField();
        
        btnOK = new Button("OK");
        
        HBox hbox = new HBox(5);
        hbox.getChildren().addAll(label_SelectGame, comboB, btnOK);
        
        HBox hboxID = new HBox(10);
        hboxID.getChildren().addAll(lblID, textID);
        
        HBox hboxFN = new HBox(5);
        hboxFN.getChildren().addAll(lblFN, textFN);
        
        HBox hboxLN = new HBox(5);
        hboxLN.getChildren().addAll(lblLN , textLN);
        
        VBox vboxHB = new VBox(5);
        vboxHB.getChildren().addAll(hbox);
        vboxHB.setSpacing(5);
        
        
        VBox vboxIDs = new VBox(5);
        vboxIDs.getChildren().addAll(hboxID, hboxFN, hboxLN);
        vboxIDs.setVisible(false);
        
                
        Button btnC = new Button("Cancel");
        Button btnR = new Button("Register");
        begin = new Button ("BEGIN GAME");
        
        HBox hboxButtons = new HBox(5);
        hboxButtons.getChildren().addAll(btnC, btnR,label_info);
        this.getChildren().addAll(vboxHB, vboxIDs,hboxButtons);
        hboxButtons.setVisible(false);
        this.getChildren().add(begin);
        profile = new GamerProfileCollection();
        profile = MeteorIO.readGamerProfileData(profileFileName);
        
        for(int i = 0; i < profile.getNumGamerProfiles(); i++){
           comboB.getItems().add(profile.getGamerProfile(i).getGamerId().trim());
        }
        VBox vbProperties = new VBox();
        this.getChildren().add(vbProperties);
        vbProperties.setVisible(false);
        
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
                
                if (comboB.getValue().equals("Register Gamer")){
                    hbox.setVisible(true);
                    hboxButtons.setVisible(true);
                    vboxIDs.setVisible(true);
            
                    VBox vbProperties2 = new VBox();   
                    
                    //this.getChildren().add(vbProperties2);
                    Stage secStage = new Stage();
                    TextArea PropArea = new TextArea();
                    ListView Lv = new ListView();
                    ObservableList<String> pList = FXCollections.<String>observableArrayList();
                    for(int i = 0; i < profile.getNumGamerProfiles(); i++){
                         pList.add(i, profile.getGamerProfile(i).toString());
                         if(comboB.getSelectionModel().getSelectedItem().equals(profile.getGamerProfile(i).getGamerId()));
                         PropArea.setText(profile.getGamerProfile(i).toString());
                    }
                    Lv.setItems(pList);
                    Lv.setMinWidth(100);
                     
            
            }
            }
         
        });
        
        btnC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle( ActionEvent event){
                textID.setText("");
                textFN.setText("");
                textLN.setText("");
            }
        });
        
        btnR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int i;
                for (i = 0; i < comboB.getItems().size()-1; i++){
                    if(textID.getText().trim().equals(comboB.getItems().get(i))){
                        IdFound = true;                 
                    }
                }if(IdFound){
                    label_info.setText("Try again mister");

                }else{
                    label_info.setText("success");
                    lblID.setText(textID.getText()+" Registered");
                     GamerProfile gp = new GamerProfile();
                     String FN =(textFN.getText());
                     String LN = (textLN.getText());
                     String ID = (textID.getText());
                     gp.setGamerId(ID);
                     gp.setFirstName(FN);
                     gp.setLastName(LN);
                     profile.addGamerProfile(gp);
                    MeteorIO.writeGamerProfileData(profile);
        
        Stage primaryStage2 = new Stage ();
                            
       ProfilePane PP = new ProfilePane("gameData.txt","meteorBlasterConfig.txt");
 
        Scene scene = new Scene(PP, 300, 250);
        
        primaryStage2.setTitle("Meteor Blaster!");
        primaryStage2.setScene(scene);
        primaryStage2.show();
    }
   }
        });   
        begin.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                GameUniverse gU = new GameUniverse(configFileName, profile);
                Stage gStage = new Stage();
                Scene game = new Scene(gU,800, 500);
               game.setOnKeyPressed(gU.getHearMe());
                
                gStage.setTitle("GameUniverse");
                gStage.setScene(game);
                gStage.show();
            }
            
        });
         };
       
        
    }
    
   
        
    

        