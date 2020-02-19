package at.homeproductions.sudoku.converter;

import at.homeproductions.sudoku.entity.SudokuBlock;
import at.homeproductions.sudoku.model.SudokuBlockModel;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SudokuBlockConverter extends AbstractConverter<SudokuBlockModel, SudokuBlock> {

    @Override
    public SudokuBlockModel toModel(SudokuBlock entity) {
        SudokuBlockModel model = new SudokuBlockModel();
        model.setxDim(entity.getXDim());
        model.setyDim(entity.getYDim());
        model.setX(entity.getX());
        model.setY(entity.getY());
        model.setSudokuFields(new SudokuFieldConverter().toModelList(
                Arrays.stream(entity.getFields()).flatMap(Arrays::stream).collect(Collectors.toList())));
        return model;
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
        SudokuBlock entity = new SudokuBlock();
        entity.setxDim(sudokuBlockModel.getxDim());
        entity.setyDim(sudokuBlockModel.getyDim());
        entity.setX(sudokuBlockModel.getX());
        entity.setY(sudokuBlockModel.getY());

        entity.setFields(new SudokuFieldConverter().toEntityList(sudokuBlockModel.getSudokuFields(), sudokuBlockModel.getxDim(), sudokuBlockModel.getyDim()));
        Arrays.stream(entity.getFields()).flatMap(Arrays::stream).forEach(s -> s.setBlock(entity));
        return entity;
    }

}
