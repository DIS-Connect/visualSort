package sample.sortingAlgorithms;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectionSort implements SortAlgorithm {


    @Override
    public void runSort(SortArray array) {
        int len = array.getArray().length;

        for(int i = 0; i < len ;i++){

            int minimumElement = array.getArray()[i];
            int minimumPosition = i;

            if(SortArray.keepsorting) {
                for (int k = i; k < len; k++) {

                    array.changeColor(k, Color.PINK);
                    array.draw();
                    array.addComparison();
                    if (array.getArray()[k] < minimumElement) {

                        array.changeColor(minimumPosition, Color.WHITE);
                        minimumElement = array.getArray()[k];
                        minimumPosition = k;
                        array.changeColor(k, Color.DEEPPINK);
                    } else {
                        array.changeColor(k, Color.WHITE);
                    }


                }
            }
            array.changeColor(minimumPosition, Color.WHITE);
            array.changeColor(i,Color.DEEPPINK);
            array.swap(minimumPosition, i);
            array.draw();
            if(i>0){
                array.changeColor(i-1, Color.PINK);
            }
            if(i==len-1){
                array.changeColor(i,Color.PINK);
            }


        }
        if(SortArray.keepsorting) {
            array.finishAnimation();
        }
    }
}
