package ViewModel;

import Model.IModel;
import algorithms.search.AState;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MyViewModel extends Observable implements Observer {

    private IModel model;

    private int characterPositionRowIndex;
    private int characterPositionColumnIndex;
    private int goalPositionRow;
    private int goalPositionCol;
    private ArrayList<AState> solutionPath;
    private boolean showSolution = false;
    private boolean achivedGoal = false;

    public StringProperty characterPositionRow = new SimpleStringProperty("1"); //For Binding
    public StringProperty characterPositionColumn = new SimpleStringProperty("1"); //For Binding


    String mazeGenerator = "";
    String searchingAlgorithm = "";
    String numOfTreads = "";


    public MyViewModel(IModel model) {
        this.model = model;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == model) {
            characterPositionRowIndex = model.getCharacterPositionRow();
            characterPositionRow.set(characterPositionRowIndex + "");
            characterPositionColumnIndex = model.getCharacterPositionColumn();
            characterPositionColumn.set(characterPositionColumnIndex + "");
            goalPositionRow = model.getGoalPositionRow();
            goalPositionCol = model.getGoalPositionCol();
            if(model.isShowSolution()) {
                showSolution = true;
                solutionPath = model.getMazeSolutionSteps();
            }
            achivedGoal = model.isGoalAchived();
            setChanged();
            notifyObservers();
        }
    }

    public void createBoard(int width, int height) {
        showSolution = false;
        model.generateMaze(width, height);
    }

    public void moveCharacter(KeyCode movement) {
        model.moveCharacter(movement);
    }

    public void saveBoard(File file) throws Exception{
        model.saveMaze(file);
    }

    public void loadBoard(File file) throws Exception{
        showSolution = false;
        model.loadMaze(file);
    }

    public void showSolution(){
        model.solveMaze();
    }

    public boolean isShowSolution() {
        return showSolution;
    }

    public ArrayList<AState> getSolutionPath() {
        return solutionPath;
    }

    public int[][] getBoard() {
        return model.getMaze();
    }

    public void clearBoard(){

        model.clearMaze();
    }

        public int getGoalPositionRow() {
        return goalPositionRow;
    }

    public int getGoalPositionCol() {
        return goalPositionCol;
    }

    public int getCharacterPositionRow() {
        return characterPositionRowIndex;
    }

    public int getCharacterPositionColumn() {
        return characterPositionColumnIndex;
    }

    public String getMazeGenerator() {
        return mazeGenerator;
    }

    public String getSearchingAlgorithm() {
        return searchingAlgorithm;
    }

    public String getNumOfTreads() {
        return numOfTreads;
    }

    public void showProperties(){

        model.showProperties();
        mazeGenerator = model.getMazeGenerator();
        searchingAlgorithm = model.getSearchingAlgorithm();
        numOfTreads = model.getNumOfThreads();

    }

    public boolean isAchivedGoal() {
        return achivedGoal;
    }

    public void closeProgram(){

        model.closeProgram();

    }
}
