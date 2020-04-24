package at.homeproductions.sudoku.converter.snapshot;

import at.homeproductions.sudoku.converter.AbstractConverter;
import at.homeproductions.sudoku.entity.snapshot.SudokuSnapshot;
import at.homeproductions.sudoku.model.snapshot.SudokuSnapshotModel;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SudokuSnapshotConverter extends AbstractConverter<SudokuSnapshotModel, SudokuSnapshot> {

    @Override
    public SudokuSnapshotModel toModel(SudokuSnapshot entity) {
        SudokuSnapshotModel model = new SudokuSnapshotModel();
        model.setxDim(entity.getxBlockDim());
        model.setyDim(entity.getyBlockDim());
        model.setMessage(entity.getMessage());

        model.setSudokuBlocks(new SudokuSnapshotBlockConverter().toModelList(Arrays.stream(entity.getBlocks())
                .flatMap(Arrays::stream)
                .collect(Collectors.toList())));

        return model;
    }

    @Override
    protected Class<SudokuSnapshotModel> getModelClass() {
        return SudokuSnapshotModel.class;
    }

    @Override
    protected Class<SudokuSnapshot> getEntityClass() {
        return SudokuSnapshot.class;
    }

    @Override
    public SudokuSnapshot toEntity(SudokuSnapshotModel model) {
        SudokuSnapshot entity = new SudokuSnapshot();
        entity.setxBlockDim(model.getxDim());
        entity.setyBlockDim(model.getyDim());
        entity.setMessage(model.getMessage());

        entity.setBlocks(new SudokuSnapshotBlockConverter().toEntityList(model.getSudokuBlocks(), model.getxDim(), model.getyDim()));
        Arrays.stream(entity.getBlocks()).flatMap(Arrays::stream).forEach(s -> s.setSudoku(entity));
        return entity;
    }

}
