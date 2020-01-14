package at.homeproductions.sudoku.converter;

import at.homeproductions.sudoku.entity.Sudoku;
import at.homeproductions.sudoku.entity.SudokuBlock;
import at.homeproductions.sudoku.model.SudokuModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuConverter {

    public static SudokuModel fromEntity(Sudoku entity) {
        SudokuModel sudokuModel = new SudokuModel();
        sudokuModel.setxDim(entity.getxBlockDim());
        sudokuModel.setyDim(entity.getyBlockDim());

        sudokuModel.setSudokuBlocks(SudokuBlockConverter.fromEntityList(Arrays.stream(entity.getBlocks())
                .flatMap(Arrays::stream)
                .collect(Collectors.toList())));

        return sudokuModel;
    }
}
