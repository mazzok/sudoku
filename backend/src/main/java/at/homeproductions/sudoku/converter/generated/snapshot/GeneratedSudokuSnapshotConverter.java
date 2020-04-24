package at.homeproductions.sudoku.converter.generated.snapshot;

import at.homeproductions.sudoku.converter.AbstractConverter;
import at.homeproductions.sudoku.converter.snapshot.SudokuSnapshotBlockConverter;
import at.homeproductions.sudoku.entity.generator.GeneratedSudokuSnapshot;
import at.homeproductions.sudoku.entity.snapshot.SudokuSnapshot;
import at.homeproductions.sudoku.model.generator.snapshot.GeneratedSudokuSnapshotModel;
import at.homeproductions.sudoku.model.snapshot.SudokuSnapshotModel;

import java.util.Arrays;
import java.util.stream.Collectors;

public class GeneratedSudokuSnapshotConverter extends AbstractConverter<GeneratedSudokuSnapshotModel, GeneratedSudokuSnapshot> {

    @Override
    public GeneratedSudokuSnapshotModel toModel(GeneratedSudokuSnapshot entity) {
        GeneratedSudokuSnapshotModel model = new GeneratedSudokuSnapshotModel();
        model.setxDim(entity.getxBlockDim());
        model.setyDim(entity.getyBlockDim());
        model.setMessage(entity.getMessage());

        model.setGeneratedSudokuBlocks(new GeneratedSudokuSnapshotBlockConverter().toModelList(Arrays.stream(entity.getBlocks())
                .flatMap(Arrays::stream)
                .collect(Collectors.toList())));

        return model;
    }

    @Override
    protected Class<GeneratedSudokuSnapshotModel> getModelClass() {
        return GeneratedSudokuSnapshotModel.class;
    }

    @Override
    protected Class<GeneratedSudokuSnapshot> getEntityClass() {
        return GeneratedSudokuSnapshot.class;
    }

    @Override
    public GeneratedSudokuSnapshot toEntity(GeneratedSudokuSnapshotModel model) {
        GeneratedSudokuSnapshot entity = new GeneratedSudokuSnapshot();
        entity.setxBlockDim(model.getxDim());
        entity.setyBlockDim(model.getyDim());
        entity.setMessage(model.getMessage());

        entity.setBlocks(new GeneratedSudokuSnapshotBlockConverter().toEntityList(model.getGeneratedSudokuBlocks(), model.getxDim(), model.getyDim()));
        Arrays.stream(entity.getBlocks()).flatMap(Arrays::stream).forEach(s -> s.setSudoku(entity));
        return entity;
    }

}
