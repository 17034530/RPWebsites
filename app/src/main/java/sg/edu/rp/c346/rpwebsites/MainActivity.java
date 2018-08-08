package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spncat,spnsub;
    Button go;
    ArrayList<String> alNumbers;
    ArrayAdapter<String> aaNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spncat = findViewById(R.id.spinnerCat);
        spnsub = findViewById(R.id.spinnerSub);
        go = findViewById(R.id.buttonGO);

        alNumbers = new ArrayList<>();
        aaNumbers = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,alNumbers);
        String[] strNumbers = getResources().getStringArray(R.array.sub_RP);
        alNumbers.addAll(Arrays.asList(strNumbers));
        spnsub.setAdapter(aaNumbers);

        spncat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                alNumbers.clear();
                switch (i){
                    case 0:
                        String[] strNumbers = getResources().getStringArray(R.array.sub_RP);
                        alNumbers.addAll(Arrays.asList(strNumbers));
                        aaNumbers.notifyDataSetChanged();
                        break;
                    case 1:
                        String[] strNumbers1 = getResources().getStringArray(R.array.sub_SOI);
                        alNumbers.addAll(Arrays.asList(strNumbers1));
                        aaNumbers.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posCat = spncat.getSelectedItemPosition();
                int posSub = spnsub.getSelectedItemPosition();
                String [][] sites = {
                        {"https://www.rp.edu.sg/",
                                "https://www.rp.edu.sg/student-life",},
                        {"https://www.rp.edu.sg/soi/full-time-diplomas/details/r47",
                                "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12"},
                };
                String URL = sites[posCat][posSub];
                Intent intent = new Intent(getBaseContext(),MainWebpageActivity.class);
                intent.putExtra("URL",URL+"");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        int posCat = spncat.getSelectedItemPosition();
        int posSubCat = spnsub.getSelectedItemPosition();
        prefEdit.putInt("cat",posCat);
        prefEdit.putInt("subCat",posSubCat);
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int catPos = prefs.getInt("cat",0);
        int catSubPos = prefs.getInt("subCat",0);
        spncat.setSelection(catPos);
        spnsub.setSelection(catSubPos);
    }
}
