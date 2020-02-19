package at.homeproductions.sudoku.converter;

import at.homeproductions.sudoku.entity.SudokuBlock;
import at.homeproductions.sudoku.entity.snapshot.SudokuSnapshotBlock;
import at.homeproductions.sudoku.model.SudokuBlockModel;
import at.homeproductions.sudoku.model.SudokuSnapshotBlockModel;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SudokuSnapshotBlockConverter extends AbstractConverter<SudokuSnapshotBlockModel, SudokuSnapshotBlock> {

    @Override
    public SudokuSnapshotBlockModel toModel(SudokuSnapshotBlock entity) {
        SudokuSnapshotBlockModel model = new SudokuSnapshotBlockModel();
        model.setxDim(entity.getXDim());
        model.setyDim(entity.getYDim());
        model.setX(entity.getX());
        model.setY(entity.getY());
        model.setSudokuFields(new SudokuSnapshotFieldConverter().toModelList(
                Arrays.stream(entity.getFields()).flatMap(Arrays::stream).collect(Collectors.toList())));
        return model;
    }

    @Override
    protected Class<SudokuSnapshotBlockModel> getModelClass() {
        return SudokuSnapshotBlockModel.class;
    }

    @Override
    protected Class<SudokuSnapshotBlock> getEntityClass() {
        return SudokuSnapshotBlock.class;
    }

    @Override
    public SudokuSnapshotBlock toEntity(SudokuSnapshotBlockModel sudokuSnapshotBlockModel) {
        SudokuSnapshotBlock entity = new SudokuSnapshotBlock();
        entity.setxDim(sudokuSnapshotBlockModel.getxDim());
        entity.setyDim(sudokuSnapshotBlockModel.getyDim());
        entity.setX(sudokuSnapshotBlockModel.getX());
        entity.setY(sudokuSnapshotBlockModel.getY());

        entity.setFields(new SudokuSnapshotFieldConverter().toEntityList(sudokuSnapshotBlockModel.getSudokuFields(), sudokuSnapshotBlockModel.getxDim(), sudokuSnapshotBlockModel.getyDim()));
        Arrays.stream(entity.getFields()).flatMap(Arrays::stream).forEach(s -> s.setBlock(entity));
        return entity;
    }

}
