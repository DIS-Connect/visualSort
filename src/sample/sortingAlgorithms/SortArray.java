package sample.sortingAlgorithms;


import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class SortArray {

    private int arrayLenght;
    private int[] array;
    private Color[] colorArray;
    private Pane pane;
    private int width;
    private int test = 0;
    private int speed;
    private int gap = 2;
    public static boolean keepsorting = true;
    private int comparisons = 0;
    private Label comparisonLabel;


    public SortArray(int arrayLenght, int speed,Pane pane, Label comparisonLabel) {
        this.pane = pane;
        this.arrayLenght = arrayLenght;
        //this.gap = (int)(0.2*(pane.getWidth()/arrayLenght));
        this.width = (int)(pane.getWidth() - arrayLenght*gap)/arrayLenght;
        this.speed = speed;
        this.array = createSortArray();
        this.colorArray = createColorArray();
        keepsorting = true;
        this.comparisonLabel = comparisonLabel;


    }

    public void insert(int current, int insertionPoint){

        int[] temp_array = new int[array.length];

        for(int i=0;i < array.length;i++){

            temp_array[i] = array[i];
        }

        int index = 0;
        for(int i = 0; i < array.length; i++){


            if(i == current){
                array[i] = temp_array[i+index];
                index = 0;
            }else if(i == insertionPoint){
                array[i] = temp_array[current];
                index = -1;
            }else{
                array[i] = temp_array[i+index];
            }
        }

    }

    public void addComparison(){
        comparisons++;

        Platform.runLater(this::comarisonThread);
    }

    private void comarisonThread() {
        this.comparisonLabel.setText(" "+comparisons);
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public void stopSorting(){
        this.keepsorting = false;
    }


    public int[] createSortArray(){
        int [] temp_array = new int[arrayLenght];

        for(int i = 0; i < arrayLenght; i++){
            temp_array[i] = ((int)(Math.random()*pane.getHeight()));
        }

        return temp_array;
    }

    public void changeColor(int index, Color color){
        colorArray[index] = color;
    }

    public Color getColor(int index){
        return colorArray[index];
    }

    public Color[] createColorArray(){
        Color[] colorArray = new Color[arrayLenght];

        for(int i = 0; i < arrayLenght; i++){
            colorArray[i] = Color.WHITE;
        }
        return colorArray;
    }

    public void swap(int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;

    }

    public int[] getArray() {
        return array;
    }

    public void finishAnimation(){
        for(int i = 0; i< arrayLenght;i++){
            changeColor(i,Color.LIGHTGREEN);
            draw();
        }
    }

    public void draw() {

        try {
            Thread.sleep(speed);
        } catch (InterruptedException ignore) {

        }
        Platform.runLater(this::drawThread);
    }

    public void drawThread(){
        int offset = width;
        pane.getChildren().clear();
        for (int i = arrayLenght -1 ; i >= 0 ; i--) {
            Rectangle rect = new Rectangle(width, array[i], colorArray[i]);
            rect.setTranslateX(pane.getWidth() - offset);
            rect.setTranslateY(pane.getHeight() - array[i]);
            pane.getChildren().add(rect);
            offset += width + gap;
        }
    }

}



