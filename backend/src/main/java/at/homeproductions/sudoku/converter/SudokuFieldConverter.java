package at.homeproductions.sudoku.converter;

import at.homeproductions.sudoku.entity.SudokuField;
import at.homeproductions.sudoku.model.SudokuFieldModel;

public class SudokuFieldConverter extends AbstractConverter<SudokuFieldModel, SudokuField> {

    @Override
    public SudokuField toEntity(SudokuFieldModel model) {
        SudokuField entity = new SudokuField();
        entity.setX(model.getX());
        entity.setY(model.getY());
        entity.setValue(model.getValue());
        entity.setIsInitialField(model.getIsInitialField());
        entity.setPossibleValues(new PossibleValueConverter().toEntityList(model.getPossibleValues()));
        entity.setIsValueReserved(model.getIsValueReserved());
        return entity;
    }

    @Override
    public SudokuFieldModel toModel(SudokuField entity) {
        SudokuFieldModel model = new SudokuFieldModel();
        model.setX(entity.getX());
        model.setY(entity.getY());
        model.setValue(entity.getValue());
        model.setIsInitialField(entity.getIsInitialField());
        model.setPossibleValues(new PossibleValueConverter().toModelList(entity.getPossibleValues()));
        model.setIsValueReserved(entity.getIsValueReserved());
        return model;
    }

    @Override
    protected Class<SudokuFieldModel> getModelClass() {
        return SudokuFieldModel.class;
    }

    @Override
    protected Class<SudokuField> getEntityClass() {
        return SudokuField.class;
    }

}
