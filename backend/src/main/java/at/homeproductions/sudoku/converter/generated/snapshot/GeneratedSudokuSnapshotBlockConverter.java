package at.homeproductions.sudoku.converter.generated.snapshot;

import at.homeproductions.sudoku.converter.AbstractConverter;
import at.homeproductions.sudoku.converter.snapshot.SudokuSnapshotFieldConverter;
import at.homeproductions.sudoku.entity.generator.GeneratedSudokuSnapshotBlock;
import at.homeproductions.sudoku.entity.snapshot.SudokuSnapshotBlock;
import at.homeproductions.sudoku.model.generator.snapshot.GeneratedSudokuSnapshotBlockModel;
import at.homeproductions.sudoku.model.snapshot.SudokuSnapshotBlockModel;

import java.util.Arrays;
import java.util.stream.Collectors;

public class GeneratedSudokuSnapshotBlockConverter extends AbstractConverter<GeneratedSudokuSnapshotBlockModel, GeneratedSudokuSnapshotBlock> {

    @Override
    public GeneratedSudokuSnapshotBlockModel toModel(GeneratedSudokuSnapshotBlock entity) {
        GeneratedSudokuSnapshotBlockModel model = new GeneratedSudokuSnapshotBlockModel();
        model.setxDim(entity.getXDim());
        model.setyDim(entity.getYDim());
        model.setX(entity.getX());
        model.setY(entity.getY());
        model.setGeneratedSudokuFields(new GeneratedSudokuSnapshotFieldConverter().toModelList(
                Arrays.stream(entity.getFields()).flatMap(Arrays::stream).collect(Collectors.toList())));
        return model;
    }

    @Override
    protected Class<GeneratedSudokuSnapshotBlockModel> getModelClass() {
        return GeneratedSudokuSnapshotBlockModel.class;
    }

    @Override
    protected Class<GeneratedSudokuSnapshotBlock> getEntityClass() {
        return GeneratedSudokuSnapshotBlock.class;
    }

    @Override
    public GeneratedSudokuSnapshotBlock toEntity(GeneratedSudokuSnapshotBlockModel sudokuSnapshotBlockModel) {
        GeneratedSudokuSnapshotBlock entity = new GeneratedSudokuSnapshotBlock();
        entity.setxDim(sudokuSnapshotBlockModel.getxDim());
        entity.setyDim(sudokuSnapshotBlockModel.getyDim());
        entity.setX(sudokuSnapshotBlockModel.getX());
        entity.setY(sudokuSnapshotBlockModel.getY());

        entity.setFields(new GeneratedSudokuSnapshotFieldConverter().toEntityList(sudokuSnapshotBlockModel.getGeneratedSudokuFields(), sudokuSnapshotBlockModel.getxDim(), sudokuSnapshotBlockModel.getyDim()));
        Arrays.stream(entity.getFields()).flatMap(Arrays::stream).forEach(s -> s.setBlock(entity));
        return entity;
    }

}
