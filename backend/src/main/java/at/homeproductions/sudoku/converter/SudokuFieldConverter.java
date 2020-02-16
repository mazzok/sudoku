package at.homeproductions.sudoku.converter;

import at.homeproductions.sudoku.entity.SudokuField;
import at.homeproductions.sudoku.model.SudokuFieldModel;

import java.util.List;
import java.util.stream.Collectors;

public class SudokuFieldConverter extends AbstractConverter<SudokuFieldModel, SudokuField> {

    @Override
    public SudokuField toEntity(SudokuFieldModel model) {
        SudokuField sudokuField = new SudokuField();
        sudokuField.setX(model.getX());
        sudokuField.setY(model.getY());
        sudokuField.setValue(model.getValue());
        sudokuField.setIsInitialField(model.getIsInitialField());
        sudokuField.setPossibleValues(new PossibleValueConverter().toEntityList(model.getPossibleValues()));
        return sudokuField;
    }

    @Override
    public SudokuFieldModel toModel(SudokuField entity) {
        SudokuFieldModel sudokuFieldModel = new SudokuFieldModel();
        sudokuFieldModel.setX(entity.getX());
        sudokuFieldModel.setY(entity.getY());
        sudokuFieldModel.setValue(entity.getValue());
        sudokuFieldModel.setIsInitialField(entity.getIsInitialField());
        sudokuFieldModel.setPossibleValues(new PossibleValueConverter().toModelList(entity.getPossibleValues()));
        return sudokuFieldModel;
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
