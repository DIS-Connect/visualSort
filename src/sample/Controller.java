package sample;

import com.sun.media.jfxmediaimpl.platform.Platform;
import javafx.animation.AnimationTimer;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.sortingAlgorithms.BubbleSort;
import sample.sortingAlgorithms.InsertionSort;
import sample.sortingAlgorithms.SelectionSort;
import sample.sortingAlgorithms.SortArray;

public class Controller {

    private int arraySize = 81;
    private int speed;
    private SortArray sort;
    Thread workerThread;
    ObservableList<String> sortAlgorithms = FXCollections.observableArrayList("Bubble Sort", "Selection Sort", "Insertion Sort");

    @FXML
    Pane pane;

    @FXML
    Button sortButton;

    @FXML
    Button stopButton;

    @FXML
    ChoiceBox sortAlgorithmBox;

    @FXML
    Button resetButton;



    @FXML
    Slider speedSlider;

    @FXML
    Label speedLabel;


    @FXML
    Slider arraySizeSlider;

    @FXML
    Label sizeLabel;

    @FXML
    Label comparisonLabel;

    @FXML
    Label arraySizeLabel;


    @FXML
    public void initialize() {
        speedLabel.setTextFill(Color.web("#ffffff"));
        sizeLabel.setTextFill(Color.web("#ffffff"));
        arraySizeLabel.setText(""+(int)arraySizeSlider.getValue());
        sortAlgorithmBox.setItems(sortAlgorithms);
        sortAlgorithmBox.setValue("Bubble Sort");
        disable(false,false,false,true,true);
        arraySizeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                arraySize = (int)arraySizeSlider.getValue();
                arraySizeLabel.setText(""+arraySize);
                build();
            }
        });

        speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                setSpeed((int)speedSlider.getValue());

            }
        });
    }

    @FXML
    public void sort(){
        setSpeed((int)speedSlider.getValue());
        disable(true, true,true,true,false);
        workerThread = new Thread(this::startSortThread);
        workerThread.start();

    }

    public void startSortThread() {

        if(sortAlgorithmBox.getValue() == "Bubble Sort"){
            BubbleSort sorter = new BubbleSort();
            sorter.runSort(sort);

        }else if(sortAlgorithmBox.getValue() == "Selection Sort"){
            SelectionSort selSorter = new SelectionSort();
            selSorter.runSort(sort);

        }else if(sortAlgorithmBox.getValue() == "Insertion Sort") {
            InsertionSort insSorter = new InsertionSort();
            insSorter.runSort(sort);
        }


            disable(false,false,false,true,true);

    }

    public void stop() {
        SortArray.keepsorting = false;
    }

    public void build() {
        disable(false,false,false,false,true);
        sort =  new SortArray((int)arraySizeSlider.getValue(),speed, pane, comparisonLabel);
        sort.draw();

    }

    public void disable(boolean arraysize,boolean sortingAlgirithm,boolean reset,boolean sort, boolean stop){
        arraySizeSlider.setDisable(arraysize);
        sortAlgorithmBox.setDisable(sortingAlgirithm);
        sortButton.setDisable(sort);
        stopButton.setDisable(stop);
        resetButton.setDisable(reset);
    }

    public void setSpeed(int speed){

        if(speed == 1){
            speed = 100;
        }else{
            speed = ((11-speed));
        }

        this.speed = speed;
        sort.setSpeed(speed);
    }
}

