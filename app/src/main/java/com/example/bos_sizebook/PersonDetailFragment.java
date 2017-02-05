package com.example.bos_sizebook;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bos_sizebook.dummy.DummyContent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * <p>This fragment is adapted from the code generated from the Master/Detail Flow activity <br>
 *     provided by Android Studio. It represents a Person detail screen. It gets passed the int <br>
 *         ARG_ITEM_ID representing the position of the Person. </p>
 * <p>The method createDetails makes the string to be shown on the screen. It consists of all the <br>
 *     attributes of the person properly formatted.</p>
 * <p>//http://javarevisited.blogspot.ca/2011/09/convert-date-to-string-simpledateformat.html <br>
 *     This website showed how to properly format the date.</p>
 * @author bos
 * @see Person
 * @see SaveAndLoad
 */
public class PersonDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private ArrayList<Person> mValues;
    private Person mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PersonDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SaveAndLoad saveandload = new SaveAndLoad();
        mValues = saveandload.loadFromFile(getContext());
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = mValues.get(Integer.parseInt(getArguments().getString(ARG_ITEM_ID)));


            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle("Bust " + Integer.toString(mItem.getBust()) + " Chest " + Integer.toString(mItem.getChest()));
                //mItem.content
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.person_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.person_detail)).setText(createDetails(mItem));
            //mItem.details
        }

        return rootView;
    }

    private String createDetails(Person person){
        String details = person.getName();
        SimpleDateFormat dateformatJava = new SimpleDateFormat("yyyy-MM-dd");
        String date_to_string = dateformatJava.format(person.getDate());
        details = details + "\nDate: " + date_to_string;
        //http://javarevisited.blogspot.ca/2011/09/convert-date-to-string-simpledateformat.html
        if (person.getNeck() != 0){
            details = details + "\nNeck Circumference: " + Integer.toString(person.getNeck()) + " Inches";
        }
        if (person.getBust() != 0){
            details = details + "\nBust Circumference: " + Integer.toString(person.getBust()) + " Inches";
        }
        if (person.getChest() != 0){
            details = details + "\nChest Circumference: " + Integer.toString(person.getChest()) + " Inches";
        }
        if (person.getWaist() != 0){
            details = details + "\nWaist Circumference: " + Integer.toString(person.getWaist()) + " Inches";
        }
        if (person.getHip() != 0) {
            details = details + "\nHip Circumference: " + Integer.toString(person.getHip()) + " Inches";
        }
        if (person.getInseam() != 0){
            details = details + "\nInseam Length: " + Integer.toString(person.getInseam()) + " Inches";
        }
        if (person.getComment() != ""){
            details = details + "\nComment: " + person.getComment();
        }
        return details;
    }
}
