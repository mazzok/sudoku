package at.homeproductions.sudoku.converter;

import at.homeproductions.sudoku.entity.Sudoku;
import at.homeproductions.sudoku.model.SudokuBlockModel;
import at.homeproductions.sudoku.model.SudokuModel;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SudokuConverter extends AbstractConverter<SudokuModel, Sudoku>{

    @Override
    public SudokuModel toModel(Sudoku entity) {
        SudokuModel sudokuModel = new SudokuModel();
        sudokuModel.setxDim(entity.getxBlockDim());
        sudokuModel.setyDim(entity.getyBlockDim());

        sudokuModel.setSudokuBlocks(new SudokuBlockConverter().toModelList(Arrays.stream(entity.getBlocks())
                .flatMap(Arrays::stream)
                .collect(Collectors.toList())));

        return sudokuModel;
    }

    @Override
    protected Class<SudokuModel> getModelClass() {
        return SudokuModel.class;
    }

    @Override
    protected Class<Sudoku> getEntityClass() {
        return Sudoku.class;
    }

    @Override
    public Sudoku toEntity(SudokuModel model) {
        Sudoku sudoku = new Sudoku();
        sudoku.setxBlockDim(model.getxDim());
        sudoku.setyBlockDim(model.getyDim());

        sudoku.setBlocks(new SudokuBlockConverter().toEntityList(model.getSudokuBlocks(), model.getxDim(), model.getyDim()));
        Arrays.stream(sudoku.getBlocks()).flatMap(Arrays::stream).forEach(s -> s.setSudoku(sudoku));
        return sudoku;
    }

}
