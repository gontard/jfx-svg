package org.gontard.jfx.svg;


import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.xml.stream.XMLStreamException;


public class JfxSvgApp extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 800, 600, Color.rgb(30, 30, 30));
        scene.setOnDragOver(event -> {
		    Dragboard db = event.getDragboard();
		    if (db.hasUrl()) {
		        event.acceptTransferModes(TransferMode.COPY);
		    } 
		    else {
		        event.consume();
		    }
		});
        
        // Dropping over surface
        scene.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasUrl()) {
                	String url = db.getUrl();
                	JfxSvgRenderer renderer = new JfxSvgRenderer();
                	try {
						Node svgNode = renderer.load(new URL(url).openStream());
						root.setCenter(svgNode);
	                	success = true;
					} catch (XMLStreamException | IOException e) {
						e.printStackTrace();
					}
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
	}

}
