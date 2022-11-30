package Misc.CustomClasses.TowerDefense;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;

public class E extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private final double strokeWidth = 5;

    private Pane selected;

    @Override
    public void start(Stage primaryStage) {
        int SCENE_HEIGHT = 800;
        int SCENE_WIDTH = 1300;
        Pane root = new Pane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        scene.setOnMouseMoved(mouseEvent -> {
            if (selected != null) {

            }
        });

        int gridW = 8;
        int gridH = 5;
        double tileD = 100;
        TileGrid tg = new TileGrid(tileD, gridW, gridH);
        tg.setTranslateX((SCENE_WIDTH - tg.width) / 2.0);
        tg.setTranslateY((SCENE_HEIGHT - tg.height) / 2.0);
        root.getChildren().add(tg);

        String[] towers = {"shooter", "doubleShooter", "defender"};
        TowerTileGrid ttg = new TowerTileGrid(tileD, towers);
        ttg.setTranslateX((SCENE_WIDTH - ttg.width) / 2.0);
        root.getChildren().add(ttg);


        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("E");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class Tile extends Pane {

        private Tower tower;

        public Tile(double width, double height) {
            super();
            this.setWidth(width);
            this.setHeight(height);
            this.setOnMouseEntered(event -> {
                Rectangle bg = (Rectangle) this.getChildren().get(0);
                bg.setFill(((Color) bg.getFill()).darker());
            });
            this.setOnMouseExited(event -> {
                Rectangle bg = (Rectangle) this.getChildren().get(0);
                bg.setFill(((Color) bg.getFill()).brighter());
            });
            Rectangle bg = new Rectangle();
            bg.setFill(Color.LIGHTGREEN);
            bg.setWidth(width);
            bg.setHeight(height);
            bg.setStroke(Color.BLACK);
            bg.setStrokeWidth(5);
            this.getChildren().add(bg);
        }
    }

    private class TileGrid extends Pane {

        public double width;
        public double height;

         public TileGrid(double tileD, int gridW, int gridH) {
             super();
             for (int x = 0; x < gridW; x++) {
                 for (int y = 0; y < gridH; y++) {
                     double xp = x * tileD;
                     double yp = y * tileD;
                     Tile tile = new Tile(tileD, tileD);
                     tile.setTranslateX(xp);
                     tile.setTranslateY(yp);
                     this.getChildren().add(tile);
                 }
             }
             width = gridW * tileD + strokeWidth;
             height = gridH * tileD + strokeWidth;
         }
    }

    private class TowerTile extends Pane {

        private final Tower tower;

        public TowerTile(String type, double width, double height) {
            super();
            this.setWidth(width);
            this.setHeight(height);
            this.setOnMouseEntered(event -> {
                Rectangle bg = (Rectangle) this.getChildren().get(0);
                bg.setFill(((Color) bg.getFill()).darker());
            });
            this.setOnMouseExited(event -> {
                Rectangle bg = (Rectangle) this.getChildren().get(0);
                bg.setFill(((Color) bg.getFill()).brighter());
            });
            Rectangle bg = new Rectangle();
            bg.setFill(Color.MEDIUMPURPLE);
            bg.setWidth(width);
            bg.setHeight(height);
            bg.setStroke(Color.BLACK);
            bg.setStrokeWidth(strokeWidth);

            double towWidth = width * .7;
            double towHeight = height * .7;
            Tower tow = new Tower(type, towWidth, towHeight);
            Pane vis = tow.getVisual();
            tower = tow;
            vis.setTranslateX((width - towWidth) / 2.0);
            vis.setTranslateY((height - towHeight) / 2.0);

            this.getChildren().addAll(bg, vis);

            // click, shadowy thing on mouse
        }
    }

    private class TowerTileGrid extends Pane {

        public double width;
        public double height;

        public TowerTileGrid(double tileD, String[] towers) {
            super();
            int gridW = towers.length;
            for (int x = 0; x < gridW; x++) {
                TowerTile tt = new TowerTile(towers[x], tileD, tileD);
                tt.setTranslateX(x * tileD);
                this.getChildren().add(tt);
            }
            width = tileD * gridW + strokeWidth;
            height = tileD + strokeWidth;
        }
    }

    private class Tower {

        private Pane vis;

        public Tower(String name, double visWidth, double visHeight) {
            switch (name) {
                case "shooter": {vis = generateShooter(visWidth, visHeight); break;}
                case "doubleShooter": {vis = generateDoubleShooter(visWidth, visHeight); break;}
                case "defender": {vis = generateDefender(visWidth, visHeight); break;}
                default: {
                    System.out.println("Class not supported.");
                }
            }
        }

        private Pane generateShooter(double visWidth, double visHeight) {
            Pane vis = new Pane();
            Rectangle bg = new Rectangle(visWidth, visHeight);
            bg.setFill(Color.TRANSPARENT);

            double vBarWidth = visWidth / 3;
            Rectangle vBar = new Rectangle(vBarWidth, visHeight);
            vBar.setFill(Color.ALICEBLUE);
            vBar.setStroke(Color.BLACK);

            double hBarWidth = visWidth * .9;
            double hBarHeight = visHeight * .3;
            Rectangle hBar = new Rectangle(hBarWidth, hBarHeight);
            hBar.setFill(Color.LIGHTGREEN);
            hBar.setTranslateY((visHeight - hBarHeight) / 2.0);
            hBar.setTranslateX(visWidth - hBarWidth);
            hBar.setStroke(Color.BLACK);

            vis.getChildren().addAll(bg, vBar, hBar);

            return vis;
        }

        private Pane generateDoubleShooter(double visWidth, double visHeight) {
            Pane vis = new Pane();
            Rectangle bg = new Rectangle(visWidth, visHeight);
            bg.setFill(Color.TRANSPARENT);

            double vBarWidth = visWidth / 3;
            Rectangle vBar = new Rectangle(vBarWidth, visHeight);
            vBar.setFill(Color.ALICEBLUE);
            vBar.setStroke(Color.BLACK);

            double hBarWidth = visWidth * .9;
            double hBarHeight = visHeight * .2;
            double margin = ((visHeight - (2 * hBarHeight)) / 3);
            Rectangle hBar1 = new Rectangle(hBarWidth, hBarHeight);
            hBar1.setFill(Color.LIGHTGREEN);
            hBar1.setTranslateY(margin);
            hBar1.setTranslateX(visWidth - hBarWidth);
            hBar1.setStroke(Color.BLACK);

            Rectangle hBar2 = new Rectangle(hBarWidth, hBarHeight);
            hBar2.setFill(Color.LIGHTGREEN);
            hBar2.setTranslateY(margin + margin + hBarHeight);
            hBar2.setTranslateX(visWidth - hBarWidth);
            hBar2.setStroke(Color.BLACK);

            vis.getChildren().addAll(bg, vBar, hBar1, hBar2);
            return vis;
        }

        private Pane generateDefender(double visWidth, double visHeight) {
            Pane vis = new Pane();
            Rectangle bg = new Rectangle(visWidth, visHeight);
            bg.setFill(Color.TRANSPARENT);

            double vBarWidth = visWidth / 3.2;
            Rectangle vBar = new Rectangle(vBarWidth, visHeight);
            vBar.setFill(Color.ALICEBLUE);
            vBar.setStroke(Color.BLACK);
            vBar.setTranslateX(visWidth - vBarWidth);

            double hBarWidth = visWidth * .7;
            double hBarHeight = visHeight * .2;
            Rectangle hBar1 = new Rectangle(hBarWidth, hBarHeight);
            hBar1.setFill(Color.ALICEBLUE);
            hBar1.setStroke(Color.BLACK);
            hBar1.setTranslateX(visWidth - hBarWidth);

            Rectangle hBar2 = new Rectangle(hBarWidth, hBarHeight);
            hBar2.setFill(Color.ALICEBLUE);
            hBar2.setStroke(Color.BLACK);
            hBar2.setTranslateX(visWidth - hBarWidth);
            hBar2.setTranslateY(visHeight - hBarHeight);

            vis.getChildren().addAll(bg, vBar, hBar1, hBar2);
            return vis;
        }



        private Pane getVisual() {return vis;}

    }
}
