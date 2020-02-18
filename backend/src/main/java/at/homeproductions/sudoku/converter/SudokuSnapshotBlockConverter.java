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
        SudokuSnapshotBlockModel sudokuSnapshotBlockModel = new SudokuSnapshotBlockModel();
        sudokuSnapshotBlockModel.setxDim(entity.getXDim());
        sudokuSnapshotBlockModel.setyDim(entity.getYDim());
        sudokuSnapshotBlockModel.setX(entity.getX());
        sudokuSnapshotBlockModel.setY(entity.getY());
        sudokuSnapshotBlockModel.setSudokuFields(new SudokuSnapshotFieldConverter().toModelList(
                Arrays.stream(entity.getFields()).flatMap(Arrays::stream).collect(Collectors.toList())));
        return sudokuSnapshotBlockModel;
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
        SudokuSnapshotBlock sudokuSnapshotBlock = new SudokuSnapshotBlock();
        sudokuSnapshotBlock.setxDim(sudokuSnapshotBlockModel.getxDim());
        sudokuSnapshotBlock.setyDim(sudokuSnapshotBlockModel.getyDim());
        sudokuSnapshotBlock.setX(sudokuSnapshotBlockModel.getX());
        sudokuSnapshotBlock.setY(sudokuSnapshotBlockModel.getY());

        sudokuSnapshotBlock.setFields(new SudokuSnapshotFieldConverter().toEntityList(sudokuSnapshotBlockModel.getSudokuFields(), sudokuSnapshotBlockModel.getxDim(), sudokuSnapshotBlockModel.getyDim()));
        Arrays.stream(sudokuSnapshotBlock.getFields()).flatMap(Arrays::stream).forEach(s -> s.setBlock(sudokuSnapshotBlock));
        return sudokuSnapshotBlock;
    }

}
