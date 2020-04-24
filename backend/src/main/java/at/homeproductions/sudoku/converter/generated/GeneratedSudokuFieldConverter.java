package at.homeproductions.sudoku.converter.generated;

import at.homeproductions.sudoku.converter.AbstractConverter;
import at.homeproductions.sudoku.converter.PossibleValueConverter;
import at.homeproductions.sudoku.entity.SudokuField;
import at.homeproductions.sudoku.entity.generator.GeneratedSudokuField;
import at.homeproductions.sudoku.model.SudokuFieldModel;
import at.homeproductions.sudoku.model.generator.GeneratedSudokuFieldModel;

public class GeneratedSudokuFieldConverter extends AbstractConverter<GeneratedSudokuFieldModel, GeneratedSudokuField> {

    @Override
    public GeneratedSudokuField toEntity(GeneratedSudokuFieldModel model) {
        GeneratedSudokuField entity = new GeneratedSudokuField();
        entity.setX(model.getX());
        entity.setY(model.getY());
        entity.setValue(model.getValue());
        entity.setSorted(model.isSorted());
        return entity;
    }

    @Override
    public GeneratedSudokuFieldModel toModel(GeneratedSudokuField entity) {
        GeneratedSudokuFieldModel model = new GeneratedSudokuFieldModel();
        model.setX(entity.getX());
        model.setY(entity.getY());
        model.setValue(entity.getValue());
        model.setSorted(entity.isSorted());
        return model;
    }

    @Override
    protected Class<GeneratedSudokuFieldModel> getModelClass() {
        return GeneratedSudokuFieldModel.class;
    }

    @Override
    protected Class<GeneratedSudokuField> getEntityClass() {
        return GeneratedSudokuField.class;
    }

}
