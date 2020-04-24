package at.homeproductions.sudoku.converter.generated;

import at.homeproductions.sudoku.converter.AbstractConverter;
import at.homeproductions.sudoku.converter.SudokuFieldConverter;
import at.homeproductions.sudoku.entity.SudokuBlock;
import at.homeproductions.sudoku.entity.generator.GeneratedSudokuBlock;
import at.homeproductions.sudoku.model.SudokuBlockModel;
import at.homeproductions.sudoku.model.generator.GeneratedSudokuBlockModel;

import java.util.Arrays;
import java.util.stream.Collectors;

public class GeneratedSudokuBlockConverter extends AbstractConverter<GeneratedSudokuBlockModel, GeneratedSudokuBlock> {

    @Override
    public GeneratedSudokuBlockModel toModel(GeneratedSudokuBlock entity) {
        GeneratedSudokuBlockModel model = new GeneratedSudokuBlockModel();
        model.setxDim(entity.getXDim());
        model.setyDim(entity.getYDim());
        model.setX(entity.getX());
        model.setY(entity.getY());
        model.setGeneratedSudokuFields(new GeneratedSudokuFieldConverter().toModelList(
                Arrays.stream(entity.getFields()).flatMap(Arrays::stream).collect(Collectors.toList())));
        return model;
    }

    @Override
    protected Class<GeneratedSudokuBlockModel> getModelClass() {
        return GeneratedSudokuBlockModel.class;
    }

    @Override
    protected Class<GeneratedSudokuBlock> getEntityClass() {
        return GeneratedSudokuBlock.class;
    }

    @Override
    public GeneratedSudokuBlock toEntity(GeneratedSudokuBlockModel sudokuBlockModel) {
        GeneratedSudokuBlock entity = new GeneratedSudokuBlock();
        entity.setxDim(sudokuBlockModel.getxDim());
        entity.setyDim(sudokuBlockModel.getyDim());
        entity.setX(sudokuBlockModel.getX());
        entity.setY(sudokuBlockModel.getY());

        entity.setFields(new GeneratedSudokuFieldConverter().toEntityList(sudokuBlockModel.getGeneratedSudokuFields(), sudokuBlockModel.getxDim(), sudokuBlockModel.getyDim()));
        Arrays.stream(entity.getFields()).flatMap(Arrays::stream).forEach(s -> s.setBlock(entity));
        return entity;
    }

}
