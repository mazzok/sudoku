package at.homeproductions.sudoku.model;

public class PossibleValueModel {
    Integer value;
    boolean hide;

    public PossibleValueModel(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }
}
