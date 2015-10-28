package quiz.mobile.hiliti.com.hiltimobileapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;

import java.util.ArrayList;

public class Quiz_selection extends AppCompatActivity {

    ArrayList<String> selection = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_selection);
    }

    public void selectItem(View v)
    {

        boolean checked  = ((CheckBox)v).isChecked();
        switch(((CheckBox) v).getId())
        {
            case R.id.checkBox :
                                  if(checked)
                                  {
                                      selection.add("Drill");
                                  }
                                    else
                                  {
                                      selection.remove("Drill");
                                  }

            case R.id.checkBox2 :
                                     if(checked)
                                     {
                                         selection.add("Hammer");
                                     }
                                     else
                                     {
                                         selection.remove("Hammer");
                                     }

            case R.id.checkBox3 :
                                    if(checked)
                                    {
                                        selection.add("Axe");
                                    }
                                    else
                                    {
                                        selection.remove("Axe");
                                    }

            case R.id.checkBox4 :
                                    if(checked)
                                    {
                                        selection.add("Chainsaw");
                                    }
                                    else
                                    {
                                        selection.remove("Chainsaw");
                                    }
                                    break;
                            }
    }

    public void finalItem(View v)
    {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
