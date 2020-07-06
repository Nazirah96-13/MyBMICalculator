package sg.edu.rp.c346.id17032457.mybmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView tvBMI,tvCalculate;
    EditText weight, height;
    Button btnCal, btnReset;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvBMI = findViewById(R.id.tvBmi);
        tvCalculate = findViewById(R.id.tvCalculate);
        weight = findViewById(R.id.editTextWeight);
        height = findViewById(R.id.editTextHeight);
        btnCal = findViewById(R.id.buttonCalculate);
        btnReset = findViewById(R.id.buttonReset);

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
                String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                        (now.get(Calendar.MONTH) + 1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY) + ":" +
                        now.get(Calendar.MINUTE);


                float etWeight = Float.parseFloat(weight.getText().toString());
                float etHeight = Float.parseFloat(height.getText().toString());

                float BMI = etWeight / (etHeight* etHeight);


                tvCalculate.setText("Last Calculated Date:"+datetime);
                tvBMI.setText("Last Calculated BMI:"+BMI );


            }


        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvCalculate.setText("Last Calculated Date:");
                tvBMI.setText("Last Calculated BMI:" );




            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();



        //Step 1a: Get the user input from the EditText and store it in a variable


        //Step 1b: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);


        //Step 1c: Obtain an instance of the SharedPreference Editor for update
        SharedPreferences.Editor prefEdit = prefs.edit();


        //Step 1d: Add the key-value pair
        prefEdit.putString("Last Calculated Date:", tvCalculate.getText().toString());
        prefEdit.putFloat("Last Calculated BMI:", Float.parseFloat(tvBMI.getText().toString()));


        //Step 1e: Call commit() to save the changes into SharedPreferences


        prefEdit.commit();


    }

    @Override
    protected void onResume() {
        super.onResume();

        //Step 2a: Obtain an instance of the SharedPreferences

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 2b: Retrieve the saved data from the SharedPreferences object
        String datetime = prefs.getString("date ", " ");
        Float bmi = prefs.getFloat("BMI",(float)0.0);

        //Step 2c: Update the UI element with the value

        tvCalculate.setText("Last Calculated Date:"+datetime);
        tvBMI.setText("Last Calculated BMI:"+bmi );


    }
}
