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

public class EditPersonActivity extends AppCompatActivity {

    private ArrayList<Person> mValues;
    //private static final String FILENAME = "file.sav";
    public static final String ARG_ITEM_ID_EDIT = "item_id";
    private Person mItem;
    /**
    private EditText nameText = (EditText) findViewById(R.id.name);
    private EditText neckText = (EditText) findViewById(R.id.neck);
    private EditText bustText = (EditText) findViewById(R.id.bust);
    private EditText chestText = (EditText) findViewById(R.id.chest);
    private EditText waistText = (EditText) findViewById(R.id.waist);
    private EditText hipText = (EditText) findViewById(R.id.hip);
    private EditText inseamText = (EditText) findViewById(R.id.inseam);
    private EditText commentText = (EditText) findViewById(R.id.comment);
     */
    private SaveAndLoad saveandload = new SaveAndLoad();
    private int position;
    //private SaveAndLoad saveandload;



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

        //go back to PersonListActivity and save the Person to GSON
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String text = something.getText().toString()

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
