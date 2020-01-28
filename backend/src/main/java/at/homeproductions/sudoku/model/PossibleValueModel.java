package at.homeproductions.sudoku.model;

public class PossibleValueModel {
    Integer value;
    boolean isHidden;

    public PossibleValueModel(){
        super();
    }

    public PossibleValueModel(Integer value){
        this.value = value;
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
}
