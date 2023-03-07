package com.example.assignment_7l;

import Controller.Controller;
import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Heap.MyIHeap;
import Domain.ProgramState.PrgState;
import Domain.Statement.IStatement;
import Domain.Value.IValue;
import Exception.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import Domain.ADT.Table.MyILatchTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Pair class used for easily setting up bi-column TableViews
 * @param <T1> Address if Heap | String if Symbol Table
 * @param <T2> IValue
 */
class Pair<T1, T2> {
    T1 first;
    T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
}

/**
 * PROGRAM EXECUTOR FX:CONTROLLER CLASS.
 * This class is used in creating the window that will run the program selected.
 */
public class ProgramExecutorController {
    private Controller controller;

    @FXML
    private TextField nrProgramStatesTextField;

    // Sets TableViews for the heap & symbol table for easier reading of address/value or string/value pairs
    @FXML
    private TableView<Pair<Integer, IValue>> heapTableView;

    @FXML
    private TableColumn<Pair<Integer, IValue>, Integer> addressColumn;

    @FXML
    private TableColumn<Pair<Integer, IValue>, String> valueColumn;

    @FXML
    private TableView<Pair<String, IValue>> symbolTableView;

    @FXML
    private TableColumn<Pair<String, IValue>, String> symbolNameColumn;

    @FXML
    private TableColumn<Pair<String, IValue>, String> symbolValueColumn;

    @FXML
    private TableView<Pair<Integer, Integer>> latchTableView;

    @FXML
    private TableColumn<Pair<Integer, Integer>, Integer> latchLocationColumn;

    @FXML
    private TableColumn<Pair<Integer, Integer>, Integer> latchValueColumn;

    @FXML
    private ListView<String> outputListView;

    @FXML
    private ListView<String> fileTableListView;

    @FXML
    private ListView<Integer> idListView;

    @FXML
    private ListView<String> executionStackListView;

    @FXML
    private Button runOneStepButton;

    /**
     * Used within ProgramExecutorController.fxml to check for CLICK EVENTS
     * Changes program state upon click
     * @param event LEFT MOUSE BUTTON
     */
    @FXML
    private void changeProgramState(MouseEvent event) {
        populateExecutionStackListView();
        populateSymbolTableView();
    }

    /**
     * Populates the Text Field of nrProgramStatesTextField with the current list of Program States
     * Basically, puts there how many Program States we've got going on right now
     */
    private void populateProgramStatesTextField() {
        List<PrgState> programStateList = controller.getProgramStateList();
        nrProgramStatesTextField.setText(String.valueOf(programStateList.size()));
    }

    /**
     * Populates the Heap Table View with the current Heap of the current (SELECTED) Program State
     * Adds the Pairs of Address/Value to heapTableView via setItems
     */
    private void populateHeapTableView() {
        PrgState prgState = getCurrentProgramState();
        MyIHeap heap = Objects.requireNonNull(prgState).getHeap();
        ArrayList<Pair<Integer, IValue>> heapEntries = new ArrayList<>();
        for(Map.Entry<Integer, IValue> entry: heap.getHeap().entrySet()) {
            heapEntries.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        heapTableView.setItems(FXCollections.observableArrayList(heapEntries));
    }

    /**
     * Populates the Symbol Table View with the current SymbolTable of the current (SELECTED) Program State
     * Adds the Pairs of String/Value to symbolTableView via setItems
     */
    private void populateSymbolTableView() {
        PrgState prgState = getCurrentProgramState();
        MyIDictionary<String, IValue> symbolTable = Objects.requireNonNull(prgState).getSymTable();
        ArrayList<Pair<String, IValue>> symbolTableEntries = new ArrayList<>();
        for (Map.Entry<String, IValue> entry: symbolTable.getContent().entrySet()) {
            symbolTableEntries.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        symbolTableView.setItems(FXCollections.observableArrayList(symbolTableEntries));
    }

    /**
     * Populates the Output List View via setItems with the current Output of the current (SELECTED) program state
     */
    private void populateOutputListView() {
        PrgState prgState = getCurrentProgramState();
        List<String> out = new ArrayList<>();
        List<IValue> outList = Objects.requireNonNull(prgState).getOut().getList();
        int i;
        for (i=0; i<outList.size(); i++){
            out.add(outList.get(i).toString());
        }
        outputListView.setItems(FXCollections.observableArrayList(out));
    }

    /**
     * Populates the File Table List View via setItems with the current FileTable of the current (SELECTED) program state
     */
    private void populateFileTableListView() {
        PrgState prgState = getCurrentProgramState();
        List<String> files = new ArrayList<>(Objects.requireNonNull(prgState).getFileTable().getContent().keySet());
        fileTableListView.setItems(FXCollections.observableList(files));
    }

    /**
     * Populates the Execution Stack List View via setItems with the current ExeStack of the current (SELECTED) program state
     * Also checks in case the program state has been terminated
     */
    private void populateExecutionStackListView() {
        PrgState programState = getCurrentProgramState();
        List<String> executionStackToString = new ArrayList<>();
        if (programState != null)
            for (IStatement statement: programState.getExeStack().getStatementList()) {
                executionStackToString.add(statement.toString());
            }
        executionStackListView.setItems(FXCollections.observableList(executionStackToString));
    }

    /**
     * Populates the Program State IDs List View via setItems with the current IDs that can be run
     */
    private void populateIdListView() {
        List<PrgState> prgStateList = controller.getProgramStateList();
        List<Integer> idList = prgStateList.stream().map(PrgState::getId).collect(Collectors.toList());
        idListView.setItems(FXCollections.observableList(idList));
        populateProgramStatesTextField();
    }

    private void populateLatchTableView() {
        PrgState prgState = getCurrentProgramState();
        MyILatchTable latchTable = Objects.requireNonNull(prgState).getLatchTable();
        ArrayList<Pair<Integer, Integer>> latchTableValues = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: latchTable.getLatchTable().entrySet()) {
            latchTableValues.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        latchTableView.setItems(FXCollections.observableArrayList(latchTableValues));
    }

    /**
     * Populates all the Views
     */
    private void populate() {
        populateHeapTableView();
        populateOutputListView();
        populateFileTableListView();
        populateIdListView();
        populateSymbolTableView();
        populateExecutionStackListView();
        populateLatchTableView();
    }

    /**
     * Setter for the controller (used in ChooserController)
     * @param controller = the controller of the program we want to run
     */
    public void setController(Controller controller) {
        this.controller = controller;
        populate();
    }

    /**
     * Initializes the ExecutorController
     * Allows selection on program state IDs
     * Sets the columns of the map TableViews so that they're ordered and id-ed
     */
    @FXML
    public void initialize() {
        idListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        addressColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().first).asObject());
        valueColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().second.toString()));
        symbolNameColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().first));
        symbolValueColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().second.toString()));
        latchLocationColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().first).asObject());
        latchValueColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().second).asObject());
    }

    /**
     * Getter for the current program state via prgState ID
     * @return current selected program state
     */
    private PrgState getCurrentProgramState() {
        if (controller.getProgramStateList().size() == 0)
            return null;
        else {
            int currentId = idListView.getSelectionModel().getSelectedIndex();
            if (currentId == -1)
                // If we haven't selected any ID, it selects the first one
                return controller.getProgramStateList().get(0);
            else
                // If we did select one, it returns that one
                return controller.getProgramStateList().get(currentId);
        }
    }

    /**
     * Used in the FXML file to assign a function when we click on the 'run step' button.
     * In this case, it makes sure that a program was selected, then does oneStep of its controller
     * @param mouseEvent LEFT MOUSE BUTTON
     */
    @FXML
    private void runOneStep(MouseEvent mouseEvent) {
        if (controller != null) {
            try {
                List<PrgState> prgStateList = Objects.requireNonNull(controller.getProgramStateList());
                if (prgStateList.size() > 0) {
                    controller.oneStep();
                    populate();
                    prgStateList = controller.removeCompletedProgram(controller.getProgramStateList());
                    controller.setProgramStateList(prgStateList);
                    populateIdListView();
                } else {
                    Alert endAlert = new Alert(Alert.AlertType.ERROR);
                    endAlert.setTitle("END OF PROGRAM");
                    endAlert.showAndWait();
                }
            } catch (InterpreterException | InterruptedException e) {
                Alert exceptionAlert = new Alert(Alert.AlertType.ERROR);
                exceptionAlert.setTitle("EXCEPTION CAUGHT");
                exceptionAlert.setContentText(e.getMessage());
                exceptionAlert.showAndWait();
            }
        } else {
            Alert selectAlert = new Alert(Alert.AlertType.ERROR);
            selectAlert.setTitle("SELECT A PROGRAM");
            selectAlert.showAndWait();
        }
    }
}
