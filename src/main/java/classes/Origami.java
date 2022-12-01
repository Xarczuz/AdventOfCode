package classes;

import java.util.ArrayList;

public class Origami {
    public int[][] matrix;
    public final ArrayList<Fold> folds;

    public Origami(int[][] matrix, ArrayList<Fold> folds) {
        this.matrix = matrix;
        this.folds = folds;
    }

}
