package at.homeproductions.sudoku.converter;

import at.homeproductions.sudoku.entity.Sudoku;
import at.homeproductions.sudoku.entity.SudokuField;
import at.homeproductions.sudoku.model.SudokuFieldModel;
import at.homeproductions.sudoku.model.SudokuModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractConverter<M, E> {

    public abstract E toEntity(M model);
    public abstract M toModel(E entity);

    protected abstract Class<M> getModelClass();
    protected abstract Class<E> getEntityClass();

    public E[][] toEntityList(List<M> modelList, int xDim, int yDim) {
        List<List<M>> l = new ArrayList<>();
        E [][] array = (E[][])Array.newInstance(getEntityClass(), xDim, yDim);

        for (int y = 0; y < yDim; y++) {
            for (int x = 0; x < xDim;x++) {
                array[y][x] = toEntity(modelList.get(y*yDim + x));
            }
        }
        return array;
    }

    public List<E> toEntityList(List<M> modelList) {
        return modelList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<M> toModelList(List<E> entityList) {
        return entityList.stream().map(this::toModel).collect(Collectors.toList());
    }
}
