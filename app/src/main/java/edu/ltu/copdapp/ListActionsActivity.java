package edu.ltu.copdapp;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ListActionsActivity extends Activity {

    EditText editInput;
    TextView displayText;
    ListDatabaseHelper listDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_actions);
        editInput = (EditText) findViewById(R.id.editInput);
        displayText = (TextView) findViewById(R.id.displayText);
        listDatabaseHelper = new ListDatabaseHelper(this, null, null, 1);
        printDatabase();

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    //Add a note to the database
    public void addButtonClicked(View view){
        Symptoms symptom = new Symptoms(editInput.getText().toString());
        listDatabaseHelper.addSymptom(symptom);
        printDatabase();
    }

    //Delete a note from the database
    public void deleteButtonClicked(View view){
        String inputText = editInput.getText().toString();
        listDatabaseHelper.deleteSymptom(inputText);
        printDatabase();
    }

    public void printDatabase(){
        String dbString = listDatabaseHelper.databaseToString();
        displayText.setText(dbString);
        editInput.setText("");
    }
}
