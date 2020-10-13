package sample.sortingAlgorithms;

import javafx.scene.paint.Color;

import java.util.Arrays;

public class BubbleSort implements SortAlgorithm{


    @Override
    public void runSort(SortArray array) {
        int len = array.getArray().length;


        for(int i = 0;i < len; i++){
            array.changeColor(0,Color.PINK);
            array.draw();
            if(SortArray.keepsorting){
            for(int j = 0; j<len - 1- i; j++) {
                array.addComparison();
                array.changeColor(j + 1, Color.DEEPPINK);
                array.changeColor(j, Color.WHITE);

                if (array.getArray()[j] > array.getArray()[j + 1]) {
                    array.swap(j, j + 1);
                    array.draw();
                } else {
                    array.draw();
                }
            }

            }
            if(i > 0){
                array.changeColor(len-i,Color.PINK);
            }
        }
        if(SortArray.keepsorting){
            array.finishAnimation();
        }

    }

}
