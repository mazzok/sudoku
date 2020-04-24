package at.homeproductions.sudoku.converter;

import at.homeproductions.sudoku.converter.snapshot.SudokuSnapshotConverter;
import at.homeproductions.sudoku.entity.Sudoku;
import at.homeproductions.sudoku.model.SudokuModel;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SudokuConverter extends AbstractConverter<SudokuModel, Sudoku>{

    @Override
    public SudokuModel toModel(Sudoku entity) {
        SudokuModel model = new SudokuModel();
        model.setxDim(entity.getxBlockDim());
        model.setyDim(entity.getyBlockDim());
        model.setSnapshots(new SudokuSnapshotConverter().toModelList(entity.getSnapshots()));

        model.setSudokuBlocks(new SudokuBlockConverter().toModelList(Arrays.stream(entity.getBlocks())
                .flatMap(Arrays::stream)
                .collect(Collectors.toList())));

        return model;
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
        Sudoku entity = new Sudoku();
        entity.setxBlockDim(model.getxDim());
        entity.setyBlockDim(model.getyDim());
        entity.setSnapshots(new SudokuSnapshotConverter().toEntityList(model.getSnapshots()));

        entity.setBlocks(new SudokuBlockConverter().toEntityList(model.getSudokuBlocks(), model.getxDim(), model.getyDim()));
        Arrays.stream(entity.getBlocks()).flatMap(Arrays::stream).forEach(s -> s.setSudoku(entity));
        return entity;
    }

}
