package Misc.CustomClasses.Menu;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import static Misc.CustomClasses.Menu.Direction.*;

/**
 * @author Jacob Swineford
 */
public class Menu <I extends Pane & Item> extends VBox {

    // information for moving and rendering menu
    private I selected;
    private LinkedList<I> items;
    private LinkedList<MenuRow<I>> rows;
    private int itemsPerRow;

    private boolean horizontalWrap;
    private boolean verticalWrap;

    public Menu(int itemsPerRow, boolean horizontalWrap, boolean verticalWrap) {
        super();
        this.itemsPerRow = itemsPerRow;
        this.horizontalWrap = horizontalWrap;
        this.verticalWrap = verticalWrap;
        items = new LinkedList<>();
        rows = new LinkedList<>();
        rows.add(new MenuRow<>());
        this.getChildren().add(rows.getLast());
    }

    public void add(I item) {
        MenuRow<I> lastRow = rows.getLast();
        int numItems = lastRow.getRow().size();
        items.add(item);
        if (numItems == itemsPerRow) {
            MenuRow<I> newRow = new MenuRow<>();
            this.getChildren().add(newRow);
            lastRow = newRow;
            rows.add(newRow);
        }
        lastRow.add(item);
    }

    public void remove(I item) {
        if (item == selected) {
            I old = selected;
            int nI = items.indexOf(selected) - 1;
            try {
                selected = items.get(nI);
                old.navigatedOff();
                selected.navigatedOn();
            } catch (Exception e) {
                //
            }
        }
        MenuRow<I> lastRow = rows.getLast();
        items.remove(item);
        lastRow.remove(item);
        if (lastRow.getChildren().size() == 0 && rows.size() > 1) {
            this.getChildren().remove(lastRow);
            rows.remove(lastRow);
        }
    }

    public void select(I item) {
        if (!items.contains(item)) return;
        if (selected != null)
            selected.navigatedOff();
        selected = item;
        selected.navigatedOn();
    }

    void deselect(I item) {
        if (!items.contains(item)) return;
        if (item == selected) selected = null;
        item.navigatedOff();
    }

    public void executeSelected() {if (selected != null) selected.execute();}

    public void navigate(Direction direction) {
        if (selected == null) return;
        int sI = items.indexOf(selected);
        I old = selected;
        LinkedList<I> selectedRow = getSelectedRow();
        LinkedList<I> selectedColumn = getSelectedColumn();

        try {
            if (direction == Right) {
                if (horizontalWrap && selectedRow.getLast() == selected) {
                    selected = selectedRow.getFirst();
                } else {
                    selected = items.get(sI + 1);
                }
            }

            if (direction == Left) {
                if (horizontalWrap && selectedRow.getFirst() == selected) {
                    selected = selectedRow.getLast();
                } else {
                    selected = items.get(sI - 1);
                }
            }

            if (direction == Up) {
                if (verticalWrap && selectedColumn.getFirst() == selected) {
                    selected = selectedColumn.getLast();
                } else {
                    selected = items.get(sI - itemsPerRow);
                }
            }

            if (direction == Down) {
                if (verticalWrap && selectedColumn.getLast() == selected) {
                    selected = selectedColumn.getFirst();
                } else {
                    selected = items.get(sI + itemsPerRow);
                }
            }

            old.navigatedOff();
            selected.navigatedOn();
        } catch (IndexOutOfBoundsException e) {
            //
        }
    }

    public I getSelected() {return selected;}
    public I getLast() {
        try {
            return rows.getLast().getLastItem();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
    public I getFirst() {
        try {
            return rows.getFirst().getFirstItem();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private LinkedList<I> getSelectedRow() {
        LinkedList<I> ret = new LinkedList<>();
        for (MenuRow<I> row : rows) {
            if (row.contains(selected)) {
                ret.addAll(row.getRow());
            }
        }
        return ret;
    }

    private LinkedList<I> getSelectedColumn() {
        int sICol = items.indexOf(selected) % itemsPerRow;
        LinkedList<I> ret = new LinkedList<>();
        for (MenuRow<I> row : rows) {
            try {
                I item = row.itemAt(sICol);
                ret.add(item);
            } catch (IndexOutOfBoundsException e) {
                //
            }
        }
        return ret;
    }
}

class MenuRow<I extends Pane & Item> extends HBox {

    private LinkedList<I> items = new LinkedList<>();

    void add(I item) {
        items.add(item);
        this.getChildren().add(item);
    }

    void remove(I item) {
        items.remove(item);
        this.getChildren().remove(item);
    }

    boolean contains(I item) {
        return items.contains(item);
    }

    LinkedList<I> getRow() {
        return items;
    }

    I itemAt(int index) {
        return items.get(index);
    }

    I getLastItem() {
        return items.getLast();
    }

    I getFirstItem() {
        return items.getFirst();
    }
}
