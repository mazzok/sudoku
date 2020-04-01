package at.homeproductions.sudoku.entity;

public class PossibleValue {

    Integer value;
    boolean isHidden;

    public PossibleValue(Integer value){
        this.value = value;
    }

    public PossibleValue() {
        super();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    @Override
    public String toString() {
        return "value: "+this.value + " ishidden:"+isHidden;
    }
}
