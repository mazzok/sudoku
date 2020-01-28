package at.homeproductions.sudoku.entity;

public class PossibleValue {

    Integer value;
    boolean hide;

    public PossibleValue(Integer value){
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
