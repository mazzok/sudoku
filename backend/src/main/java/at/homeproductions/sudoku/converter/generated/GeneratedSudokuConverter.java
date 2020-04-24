package at.homeproductions.sudoku.converter.generated;

import at.homeproductions.sudoku.converter.AbstractConverter;
import at.homeproductions.sudoku.converter.SudokuBlockConverter;
import at.homeproductions.sudoku.converter.generated.snapshot.GeneratedSudokuSnapshotConverter;
import at.homeproductions.sudoku.converter.snapshot.SudokuSnapshotConverter;
import at.homeproductions.sudoku.entity.Sudoku;
import at.homeproductions.sudoku.entity.generator.GeneratedSudoku;
import at.homeproductions.sudoku.model.SudokuModel;
import at.homeproductions.sudoku.model.generator.GeneratedSudokuModel;

import java.util.Arrays;
import java.util.stream.Collectors;

public class GeneratedSudokuConverter extends AbstractConverter<GeneratedSudokuModel, GeneratedSudoku>{

    @Override
    public GeneratedSudokuModel toModel(GeneratedSudoku entity) {
        GeneratedSudokuModel model = new GeneratedSudokuModel();
        model.setxDim(entity.getxBlockDim());
        model.setyDim(entity.getyBlockDim());
        model.setGeneratedSudokuSnapshots(new GeneratedSudokuSnapshotConverter().toModelList(entity.getGeneratedSudokuSnapshots()));

        model.setGeneratedSudokuBlocks(new GeneratedSudokuBlockConverter().toModelList(Arrays.stream(entity.getBlocks())
                .flatMap(Arrays::stream)
                .collect(Collectors.toList())));

        return model;
    }

    @Override
    protected Class<GeneratedSudokuModel> getModelClass() {
        return GeneratedSudokuModel.class;
    }

    @Override
    protected Class<GeneratedSudoku> getEntityClass() {
        return GeneratedSudoku.class;
    }

    @Override
    public GeneratedSudoku toEntity(GeneratedSudokuModel model) {
        GeneratedSudoku entity = new GeneratedSudoku();
        entity.setxBlockDim(model.getxDim());
        entity.setyBlockDim(model.getyDim());
        entity.setGeneratedSudokuSnapshots(new GeneratedSudokuSnapshotConverter().toEntityList(model.getGeneratedSudokuSnapshots()));

        entity.setBlocks(new GeneratedSudokuBlockConverter().toEntityList(model.getGeneratedSudokuBlocks(), model.getxDim(), model.getyDim()));
        Arrays.stream(entity.getBlocks()).flatMap(Arrays::stream).forEach(s -> s.setSudoku(entity));
        return entity;
    }

}
