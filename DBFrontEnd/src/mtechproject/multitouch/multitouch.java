package mtechproject.multitouch;

/**

 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.

 * All rights reserved. Use is subject to license terms.

 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.RotateEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

 


public class multitouch extends Application {

    static final int IMAGEVIEW_WIDTH = 200;
    static final int IMAGEVIEW_HEIGHT = 200;
    private ImageView postView;
    private Rectangle clipRect;
    private MultiTouchPane multiTouchPane;

 

    private void init(Stage primaryStage) {

        Group root = new Group();
        primaryStage.setScene(new Scene(root));
        multiTouchPane = new MultiTouchPane();
        root.getChildren().add(multiTouchPane);
    }

 

    public class MultiTouchPane extends Region {

 

        public MultiTouchPane() {

            clipRect = new Rectangle();
            clipRect.setSmooth(false);
            setClip(clipRect);

            Image post = new Image(multitouch.class.getResource("warning.png").toExternalForm(), false);
            postView = new ImageView(post);
            for (int i = 1; i <= 3; i++) {
                Image img = new Image(multitouch.class.getResource("animal" + i + ".jpg").toExternalForm(), false);
                MultiTouchImageView iv = new MultiTouchImageView(img);
                getChildren().add(iv);
            }
            getChildren().add(postView);

        }

        

        @Override protected void layoutChildren() {

            final double w = getWidth();
            final double h = getHeight();
            clipRect.setWidth(w);
            clipRect.setHeight(h);

            for (Node child : getChildren()) {

                if (child == postView) {
                    postView.relocate(w - 15 - postView.getLayoutBounds().getWidth(), 0);

                } else if (child.getLayoutX() == 0 && child.getLayoutY() == 0) {

                    final double iw = child.getBoundsInParent().getWidth();
                    final double ih = child.getBoundsInParent().getHeight();
                    child.setLayoutX((w - iw) * Math.random() + 100);
                    child.setLayoutY((h - ih) * Math.random() + 100);

                }

            }

        }

    }

 

    public class MultiTouchImageView extends StackPane {

 

        private ImageView imageView;
        private double lastX, lastY, startScale, startRotate;

        public MultiTouchImageView(Image img) {
            setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 0, 0, 0.5), 8, 0, 0, 2));
            imageView = new ImageView(img);
            imageView.setSmooth(true);
            getChildren().add(imageView);

            setOnMousePressed(new EventHandler<MouseEvent>() {             

                @Override public void handle(MouseEvent event) {

 

                    lastX = event.getX();
                    lastY = event.getY();
                    toFront();
                    postView.toFront();

                }

            });

            setOnMouseDragged(new EventHandler<MouseEvent>() {                

                @Override public void handle(MouseEvent event) {

 

                    double layoutX = getLayoutX() + (event.getX() - lastX);
                    double layoutY = getLayoutY() + (event.getY() - lastY);
                    if ((layoutX >= 0) && (layoutX <= (getParent().getLayoutBounds().getWidth()))) {
                        setLayoutX(layoutX);

                    }

                    if ((layoutY >= 0) && (layoutY <= (getParent().getLayoutBounds().getHeight()))) {
                        setLayoutY(layoutY);
                    }

                    if ((getLayoutX() + (event.getX() - lastX) <= 0)) {
                        setLayoutX(0);
                    }

                }

            });

            addEventHandler(ZoomEvent.ZOOM_STARTED, new EventHandler<ZoomEvent>() {                

                @Override public void handle(ZoomEvent event) {

                    startScale = getScaleX();

                }

            });

            addEventHandler(ZoomEvent.ZOOM, new EventHandler<ZoomEvent>() {               

                @Override public void handle(ZoomEvent event) {

                    setScaleX(event.getTotalZoomFactor());

                    setScaleY(event.getTotalZoomFactor());

                }

            });

            addEventHandler(RotateEvent.ROTATION_STARTED, new EventHandler<RotateEvent>() {                

                @Override public void handle(RotateEvent event) {

                    startRotate = getRotate();

                }

            });

            addEventHandler(RotateEvent.ROTATE, new EventHandler<RotateEvent>() {               

                @Override public void handle(RotateEvent event) {

                    setRotate(event.getTotalAngle());

                }

            });

 

        }

    }

 

    @Override public void start(Stage primaryStage) throws Exception {

        init(primaryStage);

        primaryStage.show();

    }

    public static void main(String[] args) { launch(args); }

}