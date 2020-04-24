package at.homeproductions.sudoku.converter.generated.snapshot;

import at.homeproductions.sudoku.converter.AbstractConverter;
import at.homeproductions.sudoku.converter.PossibleValueConverter;
import at.homeproductions.sudoku.entity.generator.GeneratedSudokuSnapshotField;
import at.homeproductions.sudoku.entity.snapshot.SudokuSnapshotField;
import at.homeproductions.sudoku.model.generator.snapshot.GeneratedSudokuSnapshotFieldModel;
import at.homeproductions.sudoku.model.snapshot.SudokuSnapshotFieldModel;

public class GeneratedSudokuSnapshotFieldConverter extends AbstractConverter<GeneratedSudokuSnapshotFieldModel, GeneratedSudokuSnapshotField> {

    @Override
    public GeneratedSudokuSnapshotField toEntity(GeneratedSudokuSnapshotFieldModel model) {
        GeneratedSudokuSnapshotField entity = new GeneratedSudokuSnapshotField();
        entity.setX(model.getX());
        entity.setY(model.getY());

        entity.setInColumnSelection(model.isInColumnSelection());
        entity.setInRowSelection(model.isInRowSelection());
        entity.setInBlockSelection(model.isInBlockSelection());
        entity.setCurrentlyInSelection(model.isCurrentlyInSelection());
        entity.setSorted(model.isSorted());

        entity.setFreeField(model.isFreeField());
        entity.setAdjacent(model.isAdjacent());
        entity.setDuplicate(model.isDuplicate());

        entity.setValue(model.getValue());

        return entity;
    }

    @Override
    public GeneratedSudokuSnapshotFieldModel toModel(GeneratedSudokuSnapshotField entity) {
        GeneratedSudokuSnapshotFieldModel model = new GeneratedSudokuSnapshotFieldModel();
        model.setX(entity.getX());
        model.setY(entity.getY());

        model.setInColumnSelection(entity.isInColumnSelection());
        model.setInRowSelection(entity.isInRowSelection());
        model.setInBlockSelection(entity.isInBlockSelection());
        model.setCurrentlyInSelection(entity.isCurrentlyInSelection());
        model.setSorted(entity.isSorted());

        model.setFreeField(entity.isFreeField());
        model.setAdjacent(entity.isAdjacent());
        model.setDuplicate(entity.isDuplicate());

        model.setValue(entity.getValue());

        return model;
    }

    @Override
    protected Class<GeneratedSudokuSnapshotFieldModel> getModelClass() {
        return GeneratedSudokuSnapshotFieldModel.class;
    }

    @Override
    protected Class<GeneratedSudokuSnapshotField> getEntityClass() {
        return GeneratedSudokuSnapshotField.class;
    }

}
