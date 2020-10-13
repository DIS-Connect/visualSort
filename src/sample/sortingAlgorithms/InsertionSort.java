package sample.sortingAlgorithms;


import javafx.scene.paint.Color;

import java.util.Arrays;

public class InsertionSort implements SortAlgorithm{
    @Override
    public void runSort(SortArray array) {
        int len = array.getArray().length;
        for(int i=0;i<len;i++) {
            if (SortArray.keepsorting){
                for (int j = 0; j < i; j++) {
                    array.addComparison();
                    array.changeColor(j, Color.DEEPPINK);
                    if (array.getArray()[i] < array.getArray()[j]) {
                        array.insert(i, j);
                        array.changeColor(i, Color.RED);
                        array.changeColor(j, Color.RED);
                    }
                    array.draw();
                    array.changeColor(i, Color.WHITE);
                    array.changeColor(j, Color.WHITE);
                }
        }

        }
        if (SortArray.keepsorting) {
            array.finishAnimation();
        }
    }

}
