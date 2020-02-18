package at.homeproductions.sudoku.converter;

import at.homeproductions.sudoku.entity.Sudoku;
import at.homeproductions.sudoku.entity.snapshot.SudokuSnapshot;
import at.homeproductions.sudoku.model.SudokuModel;
import at.homeproductions.sudoku.model.SudokuSnapshotModel;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SudokuSnapshotConverter extends AbstractConverter<SudokuSnapshotModel, SudokuSnapshot>{

    @Override
    public SudokuSnapshotModel toModel(SudokuSnapshot entity) {
        SudokuSnapshotModel sudokuSnapshotModel = new SudokuSnapshotModel();
        sudokuSnapshotModel.setxDim(entity.getxBlockDim());
        sudokuSnapshotModel.setyDim(entity.getyBlockDim());

        sudokuSnapshotModel.setSudokuBlocks(new SudokuSnapshotBlockConverter().toModelList(Arrays.stream(entity.getBlocks())
                .flatMap(Arrays::stream)
                .collect(Collectors.toList())));

        return sudokuSnapshotModel;
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
        SudokuSnapshot sudoku = new SudokuSnapshot();
        sudoku.setxBlockDim(model.getxDim());
        sudoku.setyBlockDim(model.getyDim());

        sudoku.setBlocks(new SudokuSnapshotBlockConverter().toEntityList(model.getSudokuBlocks(), model.getxDim(), model.getyDim()));
        Arrays.stream(sudoku.getBlocks()).flatMap(Arrays::stream).forEach(s -> s.setSudoku(sudoku));
        return sudoku;
    }

}
