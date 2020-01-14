package at.homeproductions.sudoku.converter;

import at.homeproductions.sudoku.entity.SudokuField;
import at.homeproductions.sudoku.model.SudokuFieldModel;

import java.util.List;
import java.util.stream.Collectors;

public class SudokuFieldConverter {

    public static SudokuFieldModel fromEntity(SudokuField entity) {
        SudokuFieldModel sudokuFieldModel = new SudokuFieldModel();
        sudokuFieldModel.setX(entity.getX());
        sudokuFieldModel.setY(entity.getY());
        sudokuFieldModel.setValue(entity.getValue());
        sudokuFieldModel.setPossibleValues(entity.getPossibleValues());
        return sudokuFieldModel;
    }

    public static List<SudokuFieldModel> fromEntityList(List<SudokuField> sudokuFieldList) {
        return sudokuFieldList.stream().map(SudokuFieldConverter::fromEntity).collect(Collectors.toList());
    }
}
