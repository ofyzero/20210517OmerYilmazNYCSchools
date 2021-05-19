package com.example.a20210517_omeryilmaz_nycschools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;

import com.opencsv.CSVReader;

import org.apache.commons.lang3.ObjectUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    // dictionary that holds data from csv files.  Two csv files combined according to DBN
    private static HashMap<String, ArrayList<String> > data ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        data = new HashMap<String,ArrayList<String> >();

        // read csv files
        readNames();
        readSAT();


        // values from csv files
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> maths = new ArrayList<>();
        ArrayList<String> reading = new ArrayList<>();
        ArrayList<String> writing = new ArrayList<>();

        for (ArrayList<String> datas : data.values() ){

            // check school has details
            if (datas.size() == 1 ){
                names.add( datas.get(0) );
                maths.add( "-1");
                reading.add( "-1" );
                writing.add( "-1" );
            }else{

                // check school has correct details / whether parser correct or not
                try {
                    int x = Integer.parseInt( datas.get(1));
                    x = Integer.parseInt( datas.get(2));
                    x = Integer.parseInt( datas.get(3));

                    names.add( datas.get(0) );
                    maths.add( datas.get(1) );
                    reading.add( datas.get(2) );
                    writing.add( datas.get(3) );
                }
                catch(NumberFormatException nFE) {

                    names.add( datas.get(0) );
                    maths.add( "-1");
                    reading.add( "-1" );
                    writing.add( "-1" );
                }
            }
        }

        // declare recycler view for school names
        NameAdapter adapter = new NameAdapter(this ,names,maths,reading,writing);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    // reads school names
    private  void readNames() {

        String[] colums ;
        InputStream names = getResources().openRawResource(R.raw.high_school_directory);

        BufferedReader reader = new BufferedReader( new InputStreamReader( names, Charset.forName("UTF-8") ) );

        String line = "";

        try{

            reader.readLine();
            while ( (line = reader.readLine())  != null){

                colums = line.split(",");

                try{
                    ArrayList itemsList = new ArrayList<String>();
                    itemsList.add(colums[1]);
                    data.put(colums[0],itemsList);
                }catch (Exception e ){
                    Log.e("ccc" , e.toString());
                }
            }

        }catch (Exception e) {

            Log.e("aaa" , e.toString());
        }
    }


    // reads sat details
    private  void readSAT() {

        String[] colums ;
        InputStream names = getResources().openRawResource(R.raw.sat_results);

        BufferedReader reader = new BufferedReader( new InputStreamReader( names, Charset.forName("UTF-8") ) );

        String line = "";

        try{

            reader.readLine();
            while ( (line = reader.readLine())  != null){

                colums = line.split(",");

                try{
                    if (data.containsKey(colums[0])) {
                        ArrayList itemsList = data.get(colums[0]);
                        itemsList.add(colums[3]);
                        itemsList.add(colums[4]);
                        itemsList.add(colums[5]);

                        data.put(colums[0],itemsList);
                    }
                }catch (Exception e ){
                    Log.e("ccc" , e.toString());
                }
            }

        }catch (Exception e) {

            Log.e("aaa" , e.toString());
        }
    }
}