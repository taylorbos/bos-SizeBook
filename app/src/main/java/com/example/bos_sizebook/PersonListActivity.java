package com.example.bos_sizebook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.bos_sizebook.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;


/**
 * An activity representing a list of People. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PersonDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 *
 * https://developer.android.com/training/basics/firstapp/starting-activity.html
 */
public class PersonListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private ArrayList<Person> mValues;
    //private int position_number;
    //private SaveAndLoad saveandload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle(getTitle());

        //loadFromFile();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //      .setAction("Action", null).show();
                Context context = view.getContext();
                //Intent intent = new Intent(context, EditPersonActivity.class);
                //startActivity(intent);
                SaveAndLoad saveandload = new SaveAndLoad();
                mValues = saveandload.loadFromFile(context);
                //String name = nameText.getText().toString();
                mValues = createPerson("name", mValues.size() ,mValues);
                saveandload.saveInFile(mValues, context);
                //Context context = view.getContext();
                Intent intent = new Intent(context, EditPersonActivity.class);
                //intent.putExtra(PersonDetailFragment.ARG_ITEM_ID, holder.mItem.getName());
                int i = mValues.size() - 1;
                intent.putExtra(EditPersonActivity.ARG_ITEM_ID_EDIT, i);

                context.startActivity(intent);
            }
        });
        /**
         View recyclerView = findViewById(R.id.person_list);
         assert recyclerView != null;
         setupRecyclerView((RecyclerView) recyclerView);
         */
        if (findViewById(R.id.person_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        SaveAndLoad saveandload = new SaveAndLoad();
        mValues = saveandload.loadFromFile(PersonListActivity.this);
        //(TextView) findViewById(R.id.numberString)
        //("People: " + Integer.toString(mValues.size()));
        View recyclerView = findViewById(R.id.person_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
        //(TextView) recyclerView.findViewById(R.id.numberString).
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setSubtitle("People: " + Integer.toString(mValues.size()));


    }

    public ArrayList<Person> createPerson(String name, int position, ArrayList<Person> items) {
        Person person = new Person(name, position);
        items.add(person);
        //ITEM_MAP.put(person.getName(), person);
        return items;
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(mValues));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        public SimpleItemRecyclerViewAdapter(ArrayList<Person> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.person_list_content, parent, false);
            return new ViewHolder(view);
        }

        public String createListDetails(Person person){
            String details = "";
            if (person.getBust() != 0){
                details = details + "Bust: " + Integer.toString(person.getBust());
            }
            if (person.getChest() != 0){
                details = details + " Chest: " + Integer.toString(person.getChest());
            }
            if (person.getWaist() != 0){
                details = details + " Waist: " + Integer.toString(person.getWaist());
            }
            if (person.getInseam() != 0){
                details = details + " Inseam: " + Integer.toString(person.getInseam());
            }
            return details;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).getName());
            //position_number = position;
            //id
            holder.mContentView.setText(createListDetails(mValues.get(position)));
            //content   mValues.get(position)
            //"Bust" + Integer.toString(mValues.get(position).getBust()) + "Chest" + Integer.toString(mValues.get(position).getChest())
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(PersonDetailFragment.ARG_ITEM_ID, Integer.toString(holder.mItem.getPosition()));
                        PersonDetailFragment fragment = new PersonDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.person_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, PersonDetailActivity.class);
                        //intent.putExtra(PersonDetailFragment.ARG_ITEM_ID, holder.mItem.getName());
                        //intent.putStringExtra(PersonDetailFragment.ARG_ITEM_ID, final_position);
                        intent.putExtra(PersonDetailFragment.ARG_ITEM_ID, Integer.toString(holder.mItem.getPosition()));
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public Person mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
