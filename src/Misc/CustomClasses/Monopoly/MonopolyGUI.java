package Misc.CustomClasses.Monopoly;

import Misc.CustomClasses.Monopoly.Enum.PropertyType;
import Misc.CustomClasses.Monopoly.Spaces.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

import static Misc.CustomClasses.Monopoly.Enum.MiscSpaceType.*;
import static Misc.CustomClasses.Monopoly.Enum.PropertyType.*;

/**
 * GunGame that plays a game of Monopoly with four players.
 *
 * @author Jacob Swineford
 */
public class MonopolyGUI extends Application {

    // local data
    private static final Space[] board = new Space[40];
    private static final Pane root = new Pane();
    private static final Jail jail = new Jail();
    private static final Player[] playerList = new Player[] { // temp 4 players
            new Player(1000, "Jacob", "boardImages/moccasin.png"),
            new Player(1000, "Rachel", "boardImages/guy.png"),
            new Player(1000, "Aidan", "boardImages/hat.png"),
            new Player(1000, "Bridget", "boardImages/purple_heel.png")
    };

    // WARNING: CHANGING THIS WILL EFFECT THE POSITION OF THE MIDDLE ROWS.
    // IF THIS IS TO BE CHANGED, CHANGE rTranslation IN THE addMiddleRows()
    // AS WELL.
    private static final double MULTIPLIER = 1.5;

    @Override
    public void start(Stage primaryStage) {
        iterateBoard();
        final double pbWidth = 300;
        final double cardWidth = 75;

        playerList[0].addProperty((Property) board[3]); // test

        class MonopolyBoard {

            private double cardWidth;
            private double cardHeight;
            private double PANE_LENGTH;
            private Point2D[] moveLocations;
            private Pane root = new Pane();

            private MonopolyBoard(double cw) {
                cardWidth = cw;
                cardHeight = cardWidth * MULTIPLIER;
                PANE_LENGTH = cardWidth * 9 + cardHeight * 2; // 9w + 2h
                moveLocations = new Point2D[40];

                // iterates moveLocations
                addTopRow();
                addMiddleRows();
                addBottomRow();
                addMiddleGraphic();

                for (Point2D p : moveLocations) {
                    Rectangle dot = new Rectangle(5, 5);
                    dot.setFill(Color.DARKRED);
                    dot.setX(p.getX());
                    dot.setY(p.getY());
                    root.getChildren().add(dot);
                }
            }

            private void addTopRow() {
                int xTranslation = 0;
                for (int i = 20; i <= 30; i++) {
                    Pane p = getBoardGraphicFor(i);
                    moveLocations[i] = new Point2D(
                            p.getBoundsInLocal().getWidth() / 2,
                            p.getBoundsInLocal().getHeight() / 2);

                    p.setPrefHeight(cardHeight);
                    if (i == 20 || i == 30) {
                        p.setPrefWidth(cardHeight);
                    } else {
                        p.setPrefWidth(cardWidth);
                    }
                    p.setTranslateX(xTranslation);
                    p.setRotate(180);

                    root.getChildren().add(p);

                    if (i == 20) {
                        moveLocations[i] = new Point2D(
                                xTranslation + cardHeight / 2,
                                cardHeight / 2);
                        xTranslation += cardWidth * MULTIPLIER; // cardHeight
                    } else if (i < 30){
                        moveLocations[i] = new Point2D(
                                xTranslation + cardWidth / 2,
                                cardHeight / 2);
                        xTranslation += cardWidth;
                    } else {
                        moveLocations[i] = new Point2D(
                                xTranslation + cardHeight / 2,
                                cardHeight / 2);
                    }
                }
            }

            private void addMiddleRows() {
                // clockwise indexes
                int right = 31; // goes up
                int left = 19; // goes down

                double rTranslation = (cardHeight / MULTIPLIER) / 4;
                double xRight = PANE_LENGTH - cardHeight; // xLeft == 0
                double yTranslation = cardHeight - rTranslation; // starting translation

                for (int i = 0; i < 9; i ++) {
                    Pane pLeft = getBoardGraphicFor(left);
                    Pane pRight = getBoardGraphicFor(right);

                    moveLocations[left] = new Point2D(
                            cardHeight / 2 - rTranslation / 2,
                            yTranslation + cardWidth / 2 + rTranslation);

                    moveLocations[right] = new Point2D(
                            xRight + cardHeight / 2  + rTranslation / 2,
                            yTranslation + cardWidth / 2 + rTranslation);

                    pLeft.setPrefHeight(cardHeight);
                    pLeft.setPrefWidth(cardWidth);
                    pLeft.setRotate(90);
                    pLeft.setTranslateX(rTranslation);
                    pLeft.setTranslateY(yTranslation);

                    pRight.setPrefHeight(cardHeight);
                    pRight.setPrefWidth(cardWidth);
                    pRight.setTranslateY(yTranslation);
                    pRight.setTranslateX(xRight + rTranslation);
                    pRight.setRotate(-90);

                    root.getChildren().addAll(pLeft, pRight);

                    right++;
                    left--;
                    yTranslation += cardHeight / MULTIPLIER; // cardWidth
                }
            }

            private void addBottomRow() {
                int xTranslation = 0;
                double yTranslation = PANE_LENGTH - cardHeight;
                for (int i = 10; i >= 0; i--) {
                    Pane p = getBoardGraphicFor(i);

                    p.setPrefHeight(cardHeight);
                    p.setPrefWidth(cardWidth);

                    p.setTranslateX(xTranslation);
                    p.setTranslateY(yTranslation);

                    root.getChildren().add(p);

                    if (i == 10) {
                        moveLocations[i] = new Point2D(
                                xTranslation + cardHeight / 2,
                                yTranslation + cardHeight / 2);
                        xTranslation += cardWidth * MULTIPLIER; // cardHeight
                    } else if (i > 0 && i < 10) {
                        moveLocations[i] = new Point2D(
                                xTranslation + cardWidth / 2,
                                yTranslation + cardHeight / 2);
                        xTranslation += cardWidth;

                    } else {
                        moveLocations[i] = new Point2D(
                                xTranslation + cardHeight / 2,
                                yTranslation + cardHeight / 2);
                    }
                }
            }

            private void addMiddleGraphic() {
                double middleLength = 9 * cardWidth;
                ImageView middle = new ImageView("boardImages/middle.jpg");
                middle.setFitWidth(middleLength);
                middle.setFitHeight(middleLength);
                middle.setTranslateX(cardHeight);
                middle.setTranslateY(cardHeight);

                Rectangle stroke = new Rectangle(middleLength, middleLength);
                stroke.setStroke(Color.BLACK);
                stroke.setStrokeWidth(1);
                stroke.setFill(Color.TRANSPARENT);
                stroke.setTranslateX(cardHeight);
                stroke.setTranslateY(cardHeight);

                root.getChildren().addAll(middle, stroke);
            }

            private Pane getBoardGraphicFor(int boardIndex) {
                if (board[boardIndex] instanceof Property) {
                    Pane p = ((Property) board[boardIndex])
                            .getPropertySpaceGraphic(cardWidth, cardHeight, false);
                    p.setOnMouseClicked(event -> displayPropertyInformationBoxFor(boardIndex));
                    return p;
                }
                if (board[boardIndex] instanceof MiscSpace) {
                    return ((MiscSpace) board[boardIndex])
                            .getMiscSpaceGraphic(cardWidth, cardHeight, false);
                }
                if (board[boardIndex] instanceof Jail) {
                    return Jail.getJailGraphic(cardHeight, false);
                }
                return new Pane(); // sentinel value
            }

            private Pane getInformationGraphicFor(int boardIndex) {
                if (board[boardIndex] instanceof Property) {
                    return ((Property) board[boardIndex])
                            .getPropertySpaceGraphic(cardWidth, cardHeight, false);
                }
                if (board[boardIndex] instanceof MiscSpace) {
                    return ((MiscSpace) board[boardIndex])
                            .getMiscSpaceGraphic(cardWidth, cardHeight, true);
                }
                if (board[boardIndex] instanceof Jail) {
                    return Jail.getJailGraphic(cardHeight, true);
                }
                return new Pane(); // sentinel value
            }

            private Pane getPropertyInformationSectionFor(int boardIndex) throws ClassCastException {
                VBox v1 = new VBox();
                VBox v2 = new VBox();
                HBox h = new HBox();
                h.setSpacing(10);
                h.setTranslateY(5);

                Property p = (Property) board[boardIndex]; // CCE

                // currentBuildings
                String bu = String.valueOf(p.getNumBuildings());
                if (p.isARailroad() || p.isAUtility()) {
                    bu = "N/A";
                }
                Text currentBuildings = new Text("Current Buildings: " + bu);
                v1.getChildren().add(currentBuildings);

                // mortgage value
                Text mv = new Text("Mortgage value: $" +
                        p.getPropertyType().getMortgageValue());
                v1.getChildren().add(mv);

                // building cost
                String c = "$" + String.valueOf(p.getPropertyType().getBuildingCost());
                if (p.isARailroad() || p.isAUtility()) {
                    c = "N/A";
                }
                Text bc = new Text("Building cost: " + c);
                v1.getChildren().add(bc);

                // owner
                String o;
                if (p.getOwner() == null) {
                    o = "N/A";
                } else {
                    o = p.getOwner().getName();
                }
                Text owner = new Text("Current owner: " + o);
                v2.getChildren().add(owner);

                // current rent
                Text currentRent = new Text("Current rent: $" + p.getCurrentRent());
                v2.getChildren().add(currentRent);


                // building mortgage
                String m = "$" + String.valueOf(p.getPropertyType().getBuildingMortgageValue());
                if (p.isARailroad() || p.isAUtility()) {
                    m = "N/A";
                }
                Text bm = new Text("Building mortgage value: " + m);
                v2.getChildren().add(bm);

                // rent
                int buildNum = 0;
                int toggle = 1;
                String propType = "Building(s)";
                if (p.isARailroad()) {
                    propType = "Railroad(s)";
                    buildNum++;
                }
                for (int i : p.getPropertyType().getRentPrices()) {
                    Text rent = new Text(buildNum + " " + propType + ": $" + i);
                    if (toggle == 1) {
                        v1.getChildren().add(rent);
                    } else {
                        v2.getChildren().add(rent);
                    }

                    toggle *= -1;
                    buildNum++;
                }

                // special rent - 4x dice, 10x dice
                if (p.isAUtility()) {
                    Text sp = new Text("1 Utility owned:");
                    Text sp2 = new Text("2 Utilities owned:");
                    Text sp3 = new Text("4 x dice roll");
                    Text sp4 = new Text("10 x dice roll");
                    v1.getChildren().addAll(sp, sp2);
                    v2.getChildren().addAll(sp3, sp4);
                }



                // update all the values based on the current status of the game.
                KeyFrame kF = new KeyFrame(Duration.millis(10), event -> {

                    // generic properties - rent, houses
                    if (!p.isARailroad() && !p.isAUtility()) {
                        currentRent.setText("Current rent: $" + p.getCurrentRent());
                        currentBuildings.setText("Current Buildings: " + p.getNumBuildings());
                    }

                    // Railroads - rent
                    if (p.isARailroad()) {
                        currentRent.setText("Current rent: $" + p.getCurrentRent());
                    }

                    // Utilities, Railroads, Properties - owner
                    try {
                        owner.setText("Current owner: " + p.getOwner().getName());
                    } catch (NullPointerException e) {
                        owner.setText("Current owner: N/A");
                    }
                });
                Timeline timeline = new Timeline(kF);
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();


                h.getChildren().addAll(v1, v2);
                return h;
            }

            private static final double BRIGHTENER = 240;
            private Color trueBrightPropertyColor(PropertyType propertyType) {
                double red = propertyType.getColor().getRed() * 255;
                double green = propertyType.getColor().getGreen() * 255;
                double blue = propertyType.getColor().getBlue() * 255;
                red += BRIGHTENER;
                green += BRIGHTENER;
                blue += BRIGHTENER;

                if (red > 255) {
                    red = 255;
                }
                if (green > 255) {
                    green = 255;
                }
                if (blue > 255) {
                    blue = 255;
                }

                return Color.rgb((int) red, (int) green, (int) blue, 1);
            }

            private Pane getBoardGraphic() {
                return root;
            }

            private double getPaneLength() {
                return PANE_LENGTH;
            }

            /**
             * Displays an information box for the property at the specified
             * index. This information box functions as a high level window
             * and automatically updates based on the current games values.
             */
            private void displayPropertyInformationBoxFor(int boardIndex) throws ClassCastException {
                Stage stage = new Stage();
                HBox pane = new HBox();
                pane.setSpacing(50);
                Property property = (Property) board[boardIndex]; // CCE

                final double INFO_HEIGHT = cardHeight + 20;
                final double INFO_WIDTH = 500;
                Scene scene = new Scene(pane, INFO_WIDTH, INFO_HEIGHT,
                        trueBrightPropertyColor(property.getPropertyType()));

                // the HBox contains a graphic on the left and information on the right
                Pane graphic = getInformationGraphicFor(boardIndex);
                graphic.setTranslateX(25);
                graphic.setTranslateY(INFO_HEIGHT / 2 - (cardHeight / 2));
                Pane info = getPropertyInformationSectionFor(boardIndex);

                pane.getChildren().addAll(graphic, info);

                stage.setTitle(property.getPropertyType().toString().replaceAll("_", " "));
                stage.setAlwaysOnTop(true);
                stage.setScene(scene);
                stage.show();
            }

            private void movePlayerIconTo(int playerIndex, int spaceIndex) {
                Player p = playerList[playerIndex];
                p.setBoardIndex(spaceIndex);

                double iconW = cardWidth / 3;
                double iconH = cardWidth / 3;
                Pane i = p.getIcon(iconW, iconH, Color.TRANSPARENT);
                i.setTranslateX(moveLocations[spaceIndex].getX() - (iconW / 2));
                i.setTranslateY(moveLocations[spaceIndex].getY() - (iconH / 2));
                root.getChildren().add(i);
            }
        }
        MonopolyBoard mb = new MonopolyBoard(cardWidth);
        Pane mbPane = mb.getBoardGraphic();
        mbPane.setTranslateX(pbWidth);
        root.getChildren().add(mbPane);
        mb.movePlayerIconTo(0, 14); // test
        mb.movePlayerIconTo(1, 14);


        final double SCENE_WIDTH = mb.getPaneLength() + pbWidth;
        final double SCENE_HEIGHT = mb.getPaneLength();
        final double pbHeight = SCENE_HEIGHT / playerList.length;

        class PlayerBox {

            private int playerIndex;
            private double boxHeight;

            private Pane root = new Pane();
            private HBox infoCont = new HBox();
            private Rectangle disableOverlay;

            private Color outlineColor = Color.LIGHTGREEN;

            private PlayerBox(int playerIndex, double boxWidth, double boxHeight) {
                this.playerIndex = playerIndex;
                this.boxHeight = boxHeight;

                // disable overlay
                disableOverlay = new Rectangle(boxWidth, boxHeight);
                disableOverlay.setFill(Color.rgb(0, 0, 0, .5));
                disableOverlay.setVisible(false);

                // outline
                Rectangle outline = new Rectangle(boxWidth, boxHeight);
                outline.setFill(outlineColor);
                outline.setStroke(Color.BLACK);

                addNameAndPiece();
                addPropertyAndMoney();
                infoCont.setSpacing(20);
                infoCont.setTranslateX(-10);


                root.getChildren().addAll(outline, infoCont, disableOverlay);
            }

            private Pane getPlayerBoxGraphic() {
                return root;
            }

            private void addNameAndPiece() {
                VBox v = new VBox();
                v.setSpacing(30);

                String nameStyle = "-fx-font-size: 20;";
                Text name = new Text(playerList[playerIndex].getName());
                name.setStyle(nameStyle);

                Pane piece = playerList[playerIndex].getIcon(100, 100, outlineColor);

                v.getChildren().addAll(name, piece);
                v.setTranslateY((boxHeight - v.getBoundsInParent().getHeight()) / 2 - 20);
                v.setTranslateX(20);
                infoCont.getChildren().add(v);
            }

            private void addPropertyAndMoney() {
                VBox v = new VBox();
                v.setSpacing(30);

                String moneyStyle = "-fx-font-size: 20";
                Text money = new Text("$ " + playerList[playerIndex].getMoney());
                money.setStyle(moneyStyle);

                // constantly update money value
                KeyFrame kf = new KeyFrame(Duration.millis(10), event ->
                        money.setText("$ " + playerList[playerIndex].getMoney()));
                Timeline t = new Timeline(kf);
                t.setCycleCount(Timeline.INDEFINITE);
                t.play();

                v.getChildren().addAll(money, getPropertyToggleBox());
                v.setTranslateY(20);
                v.setTranslateX(20);
                infoCont.getChildren().add(v);
            }

            private void toggleDisableOverlay() {
                if (disableOverlay.isVisible()) {
                    disableOverlay.setVisible(false);
                } else {
                    disableOverlay.setVisible(true);
                }
            }

            private ArrayList<Integer> getPropertyIndexes() {
                ArrayList<Integer> a = new ArrayList<>();
                for (int i = 0; i < board.length; i++) {
                    if (board[i] instanceof Property) {
                        a.add(i);
                    }
                }
                return a;
            }

            private FlowPane getPropertyToggleBox() {
                double fpWidth = 180;
                double cardWidth = fpWidth / 9;
                FlowPane fp = new FlowPane();
                fp.setPrefWidth(fpWidth);
                for (int i : getPropertyIndexes()) {
                    Property p = (Property) board[i];
                    Pane pane = p.getPropertySpaceGraphic(
                            cardWidth, cardWidth * MULTIPLIER, true);

                    // constantly reflect properties owned by the player.
                    // this event affects each pane included in the fp
                    KeyFrame kf = new KeyFrame(Duration.millis(10), event -> {
                        if (playerList[playerIndex].getListOfProperties().contains(p)) {
                            pane.setOpacity(1);
                        } else {
                            pane.setOpacity(.5);
                        }
                    });
                    Timeline t = new Timeline(kf);
                    t.setCycleCount(Timeline.INDEFINITE);
                    t.play();

                    fp.getChildren().add(pane);
                }
                return fp;
            }

        }
        double y = 0;
        for (int i = 0; i < playerList.length; i++) {
            PlayerBox pb = new PlayerBox(i, pbWidth, pbHeight);
            Pane pane = pb.getPlayerBoxGraphic();
            pane.setTranslateY(y);
            root.getChildren().add(pane);
            y += pbHeight;
        }

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.BLUE);

        primaryStage.setTitle("Monopoly");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void iterateBoard() {
        board[0] = new MiscSpace(Go);
        board[1] = new Property(Mediterranean_Avenue);
        board[2] = new MiscSpace(Community_chest);
        board[3] = new Property(Baltic_Avenue);
        board[4] = new MiscSpace(Income_Tax);
        board[5] = new Property(Reading_Railroad);
        board[6] = new Property(Oriental_Avenue);
        board[7] = new MiscSpace(Chance);
        board[8] = new Property(Vermont_Avenue);
        board[9] = new Property(Connecticut_Avenue);
        board[10] = new Jail();
        board[11] = new Property(StCharles_Place);
        board[12] = new Property(Electric_Company);
        board[13] = new Property(States_Avenue);
        board[14] = new Property(Virginia_Avenue);
        board[15] = new Property(Pennsylvania_Railroad);
        board[16] = new Property(StJames_Place);
        board[17] = new MiscSpace(Community_chest);
        board[18] = new Property(Tennessee_Avenue);
        board[19] = new Property(New_York_Avenue);
        board[20] = new MiscSpace(Free_Parking);
        board[21] = new Property(Kentucky_Avenue);
        board[22] = new MiscSpace(Chance);
        board[23] = new Property(Indiana_Avenue);
        board[24] = new Property(Illinois_Avenue);
        board[25] = new Property(B_and_O_Railroad);
        board[26] = new Property(Atlantic_Avenue);
        board[27] = new Property(Ventnor_Avenue);
        board[28] = new Property(Water_Works);
        board[29] = new Property(Marvin_Gardens);
        board[30] = new MiscSpace(Go_to_jail_space);
        board[31] = new Property(Pacific_Avenue);
        board[32] = new Property(North_Carolina_Avenue);
        board[33] = new MiscSpace(Community_chest);
        board[34] = new Property(Pennsylvania_Avenue);
        board[35] = new Property(Short_Line);
        board[36] = new MiscSpace(Chance);
        board[37] = new Property(Park_Place);
        board[38] = new MiscSpace(Luxury_tax);
        board[39] = new Property(Boardwalk);
    }

    /**
     * Returns true if all but one of the current playerList are bankrupt.
     * Returns false otherwise.
     */
    private boolean gameIsOver() {
        int bankruptPlayers = 0;
        for (Player p : playerList) {
            if (p.isBankrupt()) {
                bankruptPlayers++;
            }
        }
        return playerList.length - bankruptPlayers == 1;
    }

    private static boolean playerInJail(Player player) {
        return jail.getListOfPrisoners().contains(player);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
