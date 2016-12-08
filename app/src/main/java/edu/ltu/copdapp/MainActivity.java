package edu.ltu.copdapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

public class MainActivity extends Activity {

    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.sendnote);
        shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
        setIntent("Enter note to doctor");
        return super.onCreateOptionsMenu(menu);
    }

    private void setIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.runsteps:
                intent = new Intent(this, StepCountActivity.class);
                startActivity(intent);
                return true;
            case R.id.symnotes:
                intent = new Intent(this, ListActionsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Call onStartTest() when button is clicked
    public void onStartTest (View view) {
        Intent intent = new Intent(this, StepCountActivity.class);
        startActivity(intent);
    }
    //Call onViewDBMenu() when button is clicked
    public void onViewDBMenu (View view) {
        Intent intent = new Intent(this, ListActionsActivity.class);
        startActivity(intent);
    }
}