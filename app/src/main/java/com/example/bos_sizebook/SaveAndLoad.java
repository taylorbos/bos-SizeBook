package com.example.bos_sizebook;

import android.content.Context;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static java.lang.Boolean.TRUE;

/**
 * Created by Taylor on 2017-02-03.
 */


    public class SaveAndLoad {

        private static final String FILENAME = "file.sav";

        public SaveAndLoad() {
        }

        public ArrayList<Person> loadFromFile(Context context) {
            ArrayList<Person> mValues;
            try {
                FileInputStream fis = context.openFileInput(FILENAME);
                JsonReader in = new JsonReader(new InputStreamReader(fis));
                in.setLenient(TRUE);
                //BufferedReader in = new BufferedReader(Rin);
                //http://stackoverflow.com/questions/11484353/gson-throws-malformedjsonexception
                Gson gson = new Gson();

                mValues = gson.fromJson(in, new TypeToken<ArrayList<Person>>(){}.getType());
                fis.close();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                mValues = new ArrayList<Person>();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                throw new RuntimeException();
            }
            return mValues;
        }

        public void saveInFile(ArrayList<Person> mValues, Context context) {
            try {
                FileOutputStream fos = context.openFileOutput(FILENAME, 0);
                //FileOutputStream fos = new FileOutputStream(FILENAME);
                //BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
                JsonWriter out = new JsonWriter(new OutputStreamWriter(fos));
                Gson gson = new Gson();
                gson.toJson(mValues, new TypeToken<ArrayList<Person>>(){}.getType(), out);
                out.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                throw new RuntimeException();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                throw new RuntimeException();
            }
        }
    }

