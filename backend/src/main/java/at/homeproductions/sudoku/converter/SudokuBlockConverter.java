package at.homeproductions.sudoku.converter;

import at.homeproductions.sudoku.entity.SudokuBlock;
import at.homeproductions.sudoku.model.SudokuBlockModel;
import at.homeproductions.sudoku.model.SudokuModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuBlockConverter {

    public static SudokuBlockModel fromEntity(SudokuBlock entity) {
        SudokuBlockModel sudokuBlockModel = new SudokuBlockModel();
        sudokuBlockModel.setX(entity.getX());
        sudokuBlockModel.setY(entity.getY());
        sudokuBlockModel.setSudokuFields(SudokuFieldConverter.fromEntityList(
                Arrays.stream(entity.getFields()).flatMap(Arrays::stream).collect(Collectors.toList())));
        return sudokuBlockModel;
    }

    public static List<SudokuBlockModel> fromEntityList(List<SudokuBlock> sudokuBlockList) {
        return sudokuBlockList.stream().map(SudokuBlockConverter::fromEntity).collect(Collectors.toList());
    }
}
