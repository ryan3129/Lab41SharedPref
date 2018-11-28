package my.edu.tarc.lab41sharedpref;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREF_NAME = "my.edu.tarc.lab41sharedpref;";
    private EditText editTextName;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radiobuttonFemale;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editTextName = findViewById(R.id.editTextName);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radiobuttonFemale = findViewById(R.id.radioButtonFemale);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String name;
        int gender;

        name = sharedPreferences.getString(getString(R.string.user_name), getString(R.string.no_name));
        gender = sharedPreferences.getInt(getString(R.string.gender), -1);

        editTextName.setText(name);
        if(gender==1){
            radioButtonMale.setChecked(true);
        }
        else if(gender==0){
            radiobuttonFemale.setChecked(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(!TextUtils.isEmpty(editTextName.getText().toString())){
            String name = editTextName.getText().toString();
            editor.putString(getString(R.string.user_name), name);
        }

        int gender = radioGroupGender.getCheckedRadioButtonId();
        if(gender == R.id.radioButtonMale){
            editor.putInt(getString(R.string.gender), 1);
        }
        else if(gender == R.id.radioButtonFemale){
            editor.putInt(getString(R.string.gender), 0);
        }

        editor.apply();
    }
}
