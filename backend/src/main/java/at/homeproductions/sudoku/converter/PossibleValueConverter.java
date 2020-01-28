package at.homeproductions.sudoku.converter;

import at.homeproductions.sudoku.entity.PossibleValue;
import at.homeproductions.sudoku.model.PossibleValueModel;

public class PossibleValueConverter extends AbstractConverter<PossibleValueModel, PossibleValue> {
    @Override
    public PossibleValue toEntity(PossibleValueModel model) {
        PossibleValue entity = new PossibleValue(model.getValue());
        entity.setHide(model.isHide());
        return entity;
    }

    @Override
    public PossibleValueModel toModel(PossibleValue entity) {
        PossibleValueModel model = new PossibleValueModel(entity.getValue());
        model.setHide(entity.isHide());
        return model;
    }

    @Override
    protected Class<PossibleValueModel> getModelClass() {
        return PossibleValueModel.class;
    }

    @Override
    protected Class<PossibleValue> getEntityClass() {
        return PossibleValue.class;
    }
}
