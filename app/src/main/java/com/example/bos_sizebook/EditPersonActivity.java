package com.example.bos_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * <p>EditPersonActivity is the activity that is called when the user wants to add a person <br>
 *     or edit an existing person. This activity is also passed an extra called ARG_ITEM_ID_EDIT <br>
 *         which includes the position number of the person that is too be changed. If a person <br>
 *             is being added then a black person is to be added to the list of people before <br>
 *                 this activity is called. The methods in SaveAndLoad were used to load and save the person list.</p>
 * <p>The XML file for this activity consists of a TextView and an EditText for each of the <br>
 *     attributes of Person. If values exist for these attributes than they are written in <br>
 *         When the save button is pressed what ever is written in the EditText boxes are set <br>
 *             in the attributes of the person.</p>
 * <p>http://stackoverflow.com/questions/4590957/how-to-set-text-in-an-edittext <br>
 *     This showed how to have the class set the text in an EditText.</p>
 * @author bos
 * @see Person
 * @see SaveAndLoad
 */

public class EditPersonActivity extends AppCompatActivity {

    private ArrayList<Person> mValues;
    public static final String ARG_ITEM_ID_EDIT = "item_id";
    private Person mItem;
    private SaveAndLoad saveandload = new SaveAndLoad();
    private int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText nameText = (EditText) findViewById(R.id.name);
        final EditText neckText = (EditText) findViewById(R.id.neck);
        final EditText bustText = (EditText) findViewById(R.id.bust);
        final EditText chestText = (EditText) findViewById(R.id.chest);
        final EditText waistText = (EditText) findViewById(R.id.waist);
        final EditText hipText = (EditText) findViewById(R.id.hip);
        final EditText inseamText = (EditText) findViewById(R.id.inseam);
        final EditText commentText = (EditText) findViewById(R.id.comment);

        mValues = saveandload.loadFromFile(EditPersonActivity.this);

        position = getIntent().getIntExtra(EditPersonActivity.ARG_ITEM_ID_EDIT, 0);
        mItem = mValues.get(position);

        nameText.setText(mItem.getName(), TextView.BufferType.EDITABLE);
        //http://stackoverflow.com/questions/4590957/how-to-set-text-in-an-edittext
        neckText.setText(Integer.toString(mItem.getNeck()), TextView.BufferType.EDITABLE);

        bustText.setText(Integer.toString(mItem.getBust()), TextView.BufferType.EDITABLE);

        chestText.setText(Integer.toString(mItem.getChest()), TextView.BufferType.EDITABLE);

        waistText.setText(Integer.toString(mItem.getWaist()), TextView.BufferType.EDITABLE);

        hipText.setText(Integer.toString(mItem.getHip()), TextView.BufferType.EDITABLE);

        inseamText.setText(Integer.toString(mItem.getInseam()), TextView.BufferType.EDITABLE);

        commentText.setText(mItem.getComment(), TextView.BufferType.EDITABLE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mValues.get(position).setName(nameText.getText().toString());
                mValues.get(position).setDate(new Date());
                mValues.get(position).setNeck(Integer.parseInt(neckText.getText().toString()));
                mValues.get(position).setBust(Integer.parseInt(bustText.getText().toString()));
                mValues.get(position).setChest(Integer.parseInt(chestText.getText().toString()));
                mValues.get(position).setWaist(Integer.parseInt(waistText.getText().toString()));
                mValues.get(position).setHip(Integer.parseInt(hipText.getText().toString()));
                mValues.get(position).setInseam(Integer.parseInt(inseamText.getText().toString()));
                mValues.get(position).setComment(commentText.getText().toString());
                saveandload.saveInFile(mValues, EditPersonActivity.this);
                Context context = view.getContext();
                Intent intent = new Intent(context, PersonListActivity.class);
                startActivity(intent);
            }
        });
    }
}
