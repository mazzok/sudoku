package at.homeproductions.sudoku.converter;

import at.homeproductions.sudoku.entity.snapshot.SudokuSnapshotField;
import at.homeproductions.sudoku.model.SudokuSnapshotFieldModel;

public class SudokuSnapshotFieldConverter extends AbstractConverter<SudokuSnapshotFieldModel, SudokuSnapshotField> {

    @Override
    public SudokuSnapshotField toEntity(SudokuSnapshotFieldModel model) {
        SudokuSnapshotField entity = new SudokuSnapshotField();
        entity.setX(model.getX());
        entity.setY(model.getY());
        entity.setIsActor(model.getIsActor());
        entity.setIsReactor(model.getIsReactor());
        entity.setValue(model.getValue());
        entity.setPossibleValues(new PossibleValueConverter().toEntityList(model.getPossibleValues()));
        entity.setIsValueReserved(model.getIsValueReserved());
        return entity;
    }

    @Override
    public SudokuSnapshotFieldModel toModel(SudokuSnapshotField entity) {
        SudokuSnapshotFieldModel model = new SudokuSnapshotFieldModel();
        model.setX(entity.getX());
        model.setY(entity.getY());
        model.setIsActor(entity.getIsActor());
        model.setIsReactor(entity.getIsReactor());
        model.setValue(entity.getValue());
        model.setPossibleValues(new PossibleValueConverter().toModelList(entity.getPossibleValues()));
        model.setIsValueReserved(entity.getIsValueReserved());
        return model;
    }

    @Override
    protected Class<SudokuSnapshotFieldModel> getModelClass() {
        return SudokuSnapshotFieldModel.class;
    }

    @Override
    protected Class<SudokuSnapshotField> getEntityClass() {
        return SudokuSnapshotField.class;
    }

}
