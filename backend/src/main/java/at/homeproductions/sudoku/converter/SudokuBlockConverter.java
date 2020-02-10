package at.homeproductions.sudoku.converter;

import at.homeproductions.sudoku.entity.SudokuBlock;
import at.homeproductions.sudoku.model.SudokuBlockModel;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SudokuBlockConverter extends AbstractConverter<SudokuBlockModel, SudokuBlock> {

    @Override
    public SudokuBlockModel toModel(SudokuBlock entity) {
        SudokuBlockModel sudokuBlockModel = new SudokuBlockModel();
        sudokuBlockModel.setxDim(entity.getXDim());
        sudokuBlockModel.setyDim(entity.getYDim());
        sudokuBlockModel.setX(entity.getX());
        sudokuBlockModel.setY(entity.getY());
        sudokuBlockModel.setSudokuFields(new SudokuFieldConverter().toModelList(
                Arrays.stream(entity.getFields()).flatMap(Arrays::stream).collect(Collectors.toList())));
        return sudokuBlockModel;
    }

    @Override
    protected Class<SudokuBlockModel> getModelClass() {
        return SudokuBlockModel.class;
    }

    @Override
    protected Class<SudokuBlock> getEntityClass() {
        return SudokuBlock.class;
    }

    @Override
    public SudokuBlock toEntity(SudokuBlockModel sudokuBlockModel) {
        SudokuBlock sudokuBlock = new SudokuBlock();
        sudokuBlock.setxDim(sudokuBlockModel.getxDim());
        sudokuBlock.setyDim(sudokuBlockModel.getyDim());
        sudokuBlock.setX(sudokuBlockModel.getX());
        sudokuBlock.setY(sudokuBlockModel.getY());

        sudokuBlock.setFields(new SudokuFieldConverter().toEntityList(sudokuBlockModel.getSudokuFields(), sudokuBlockModel.getxDim(), sudokuBlockModel.getyDim()));
        Arrays.stream(sudokuBlock.getFields()).flatMap(Arrays::stream).forEach(s -> s.setBlock(sudokuBlock));
        return sudokuBlock;
    }

}
