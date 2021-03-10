package com.eltech.elhealth.Onboarding;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputType;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eltech.elhealth.HomeActivity;
import com.eltech.elhealth.R;
import com.eltech.elhealth.Tools.DataClass;
import com.google.android.material.chip.Chip;

import java.util.Calendar;

public class OnboardingActivity extends AppCompatActivity {

    Button activity_onboarding_finish_button;
    ImageButton male_image_button,female_image_button;
    TextView seekBar_current_text;
    EditText name_edit_text,dob_edit_text,height_edit_text,weight_edit_text;
    SeekBar activity_onboarding_seekBar;
    DatePickerDialog picker;
    Chip lose_fat_chip,build_muscle_chip,improve_endurance_chip,maintain_body_shape,improve_athletic_skills_chip;
    Chip using_bodyweight_chip,using_gym_equipment_chip,using_weights_chip;
    Chip im_fine_chip,no_jumping_chip,low_impact_chip;
    int gender = 0;
    private static final String SHARED_PREFS = "sharedPrefs", FINISH = "finish";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //finding all the components in layout
        setContentView(R.layout.activity_onboarding);
        //name section
        name_edit_text = findViewById(R.id.name_edit_text);
        //gender section
        male_image_button = findViewById(R.id.male__image_button);
        female_image_button = findViewById(R.id.female_image_button);
        //personal details section
        dob_edit_text = findViewById(R.id.dob_edit_text);
        height_edit_text = findViewById(R.id.height_edit_text);
        weight_edit_text = findViewById(R.id.weight_edit_text);
        //fitness level section
        activity_onboarding_seekBar = findViewById(R.id.activity_onboarding_seekBar);
        seekBar_current_text = findViewById(R.id.seekBar_current_text);
        //fitness goals section
        lose_fat_chip = findViewById(R.id.lose_fat_chip);
        build_muscle_chip = findViewById(R.id.build_muscle_chip);
        improve_endurance_chip = findViewById(R.id.improve_endurance_chip);
        maintain_body_shape = findViewById(R.id.maintain_body_shape);
        improve_athletic_skills_chip = findViewById(R.id.improve_athletic_skills_chip);
        //how achieve goals section
        using_bodyweight_chip = findViewById(R.id.using_bodyweight_chip);
        using_gym_equipment_chip = findViewById(R.id.using_gym_equipment_chip);
        using_weights_chip = findViewById(R.id.using_weights_chip);
        //knee problems section
        im_fine_chip = findViewById(R.id.im_fine_chip);
        no_jumping_chip = findViewById(R.id.no_jumping_chip);
        low_impact_chip = findViewById(R.id.low_impact_chip);
        //finish button section
        activity_onboarding_finish_button = findViewById(R.id.activity_onboarding_finish_button);
        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        /**
         * setting the various buttons and chips
         */
        //setting the male button
        male_image_button.setOnClickListener(v->{
            gender = 1; //1 is male
            Toast.makeText(OnboardingActivity.this,getString(R.string.male_selected),Toast.LENGTH_SHORT).show();//
        });
        //setting the female button
        female_image_button.setOnClickListener(v->{
            gender = 2; //2 is female
            Toast.makeText(OnboardingActivity.this,R.string.female_selected,Toast.LENGTH_SHORT).show();//string
        });
        //if finish value is true then load the data from shared preferences
        if(sharedPreferences.getBoolean(FINISH, false)){
            name_edit_text.setText(sharedPreferences.getString("name",""));
            gender = sharedPreferences.getInt("gender",0);
            dob_edit_text.setText(sharedPreferences.getString("dob",""));
            height_edit_text.setText(sharedPreferences.getString("height",""));
            weight_edit_text.setText(sharedPreferences.getString("weight_text",""));
            activity_onboarding_seekBar.setProgress(Integer.parseInt(String.valueOf(sharedPreferences.getString("seekBar_current_text","0"))));
            seekBar_current_text.setText(sharedPreferences.getString("seekBar_current_text",""));
            lose_fat_chip.setChecked(sharedPreferences.getBoolean("lose_fat_chip",false));
            build_muscle_chip.setChecked(sharedPreferences.getBoolean("build_muscle_chip",false));
            improve_endurance_chip.setChecked(sharedPreferences.getBoolean("improve_endurance_chip",false));
            maintain_body_shape.setChecked(sharedPreferences.getBoolean("maintain_body_shape",false));
            improve_athletic_skills_chip.setChecked(sharedPreferences.getBoolean("improve_athletic_skills_chip",false));//
            using_bodyweight_chip.setChecked(sharedPreferences.getBoolean("using_bodyweight_chip",false));
            using_gym_equipment_chip.setChecked(sharedPreferences.getBoolean("using_gym_equipment_chip",false));
            using_weights_chip.setChecked(sharedPreferences.getBoolean("using_weights_chip",false));
            im_fine_chip.setChecked(sharedPreferences.getBoolean("im_fine_chip",false));
            no_jumping_chip.setChecked(sharedPreferences.getBoolean("no_jumping_chip",false));
            low_impact_chip.setChecked(sharedPreferences.getBoolean("low_impact_chip",false));
        }
        //setting date picker for date edit text
        dob_edit_text.setInputType(InputType.TYPE_NULL);
        dob_edit_text.setOnClickListener(v->{
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int current_year = cldr.get(Calendar.YEAR);
            DataClass.print("year is before: " + current_year);
            picker = new DatePickerDialog(OnboardingActivity.this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        //setting the edit text text itself.
                        if(year >= current_year-8){
                            dob_edit_text.setText("");
                            Toast.makeText(OnboardingActivity.this,"You have to be at least 10",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            dob_edit_text.setText(String.format("%02d/%02d/%02d", dayOfMonth, monthOfYear + 1, year));
                        }
                    }, current_year, month, day);
            picker.show();
        });
        //setting fitness level seekbar
        activity_onboarding_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar_current_text.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //saving all of the chips values
        //fitness goals chips
        lose_fat_chip.setOnClickListener(v->{
                editor.putBoolean("lose_fat_chip",lose_fat_chip.isChecked());
                editor.apply();
            DataClass.print("lose_fat_chip has been clicked: " + sharedPreferences.getBoolean("lose_fat_chip",false));
        });
        build_muscle_chip.setOnClickListener(v->{
            editor.putBoolean("build_muscle_chip",build_muscle_chip.isChecked());
            editor.apply();
            DataClass.print("build_muscle_chip has been clicked: " + sharedPreferences.getBoolean("build_muscle_chip",false));
        });
        improve_endurance_chip.setOnClickListener(v->{
            editor.putBoolean("improve_endurance_chip",improve_endurance_chip.isChecked());
            editor.apply();
            DataClass.print("improve_endurance_chip has been clicked: " + sharedPreferences.getBoolean("improve_endurance_chip",false));
        });
        maintain_body_shape.setOnClickListener(v->{
            editor.putBoolean("maintain_body_shape",maintain_body_shape.isChecked());
            editor.apply();
            DataClass.print("maintain_body_shape has been clicked: " + sharedPreferences.getBoolean("maintain_body_shape",false));
        });
        improve_athletic_skills_chip.setOnClickListener(v->{
            editor.putBoolean("improve_athletic_skills_chip",improve_athletic_skills_chip.isChecked());
            editor.apply();
            DataClass.print("improve_athletic_skills_chip has been clicked: " + sharedPreferences.getBoolean("improve_athletic_skills_chip",false));
        });
        //how achieve fitness goals
        using_bodyweight_chip.setOnClickListener(v->{
            editor.putBoolean("using_bodyweight_chip",using_bodyweight_chip.isChecked());
            editor.apply();
            DataClass.print("using_bodyweight_chip has been clicked: " + sharedPreferences.getBoolean("using_bodyweight_chip",false));
        });
        using_gym_equipment_chip.setOnClickListener(v->{
            editor.putBoolean("using_gym_equipment_chip",using_gym_equipment_chip.isChecked());
            editor.apply();
            DataClass.print("using_gym_equipment_chip has been clicked: " + sharedPreferences.getBoolean("using_gym_equipment_chip",false));
        });
        using_weights_chip.setOnClickListener(v->{
            editor.putBoolean("using_weights_chip",using_weights_chip.isChecked());
            editor.apply();
            DataClass.print("using_weights_chip has been clicked: " + sharedPreferences.getBoolean("using_weights_chip",false));
        });
        //knee problems chips
        im_fine_chip.setOnClickListener(v->{
            editor.putBoolean("im_fine_chip",im_fine_chip.isChecked());
            editor.apply();
            DataClass.print("im_fine_chip has been clicked: " + sharedPreferences.getBoolean("im_fine_chip",false));
        });
        no_jumping_chip.setOnClickListener(v->{
            editor.putBoolean("no_jumping_chip",no_jumping_chip.isChecked());
            editor.apply();
            DataClass.print("no_jumping_chip has been clicked: " + sharedPreferences.getBoolean("no_jumping_chip",false));
        });
        low_impact_chip.setOnClickListener(v->{
            editor.putBoolean("low_impact_chip",low_impact_chip.isChecked());
            editor.apply();
            DataClass.print("low_impact_chip has been clicked: " + sharedPreferences.getBoolean("low_impact_chip",false));
        });
        //setting finish button by checking if the required fields have been checked.
        activity_onboarding_finish_button.setOnClickListener(v -> {
            if(canFinish()){
                Intent homeIntent = new Intent(OnboardingActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                editor.putBoolean(FINISH,true);
                editor.putString("name",String.valueOf(name_edit_text.getText()));
                editor.putInt("gender",gender);
                editor.putString("dob",String.valueOf(dob_edit_text.getText()));
                editor.putString("height",String.valueOf(height_edit_text.getText()));
                editor.putString("weight_text",String.valueOf(weight_edit_text.getText()));
                editor.putString("seekBar_current_text",String.valueOf(seekBar_current_text.getText()));
                editor.apply();
              DataClass.print(
                      "name is: " + String.valueOf(name_edit_text.getText()) + "\n" +
                      "gender is: " + gender + "\n"+
                        "date is: " + String.valueOf(dob_edit_text.getText()) + "\n" +
                        "height is: " + String.valueOf(height_edit_text.getText())+ "\n" +
                        "weight is: " + String.valueOf(weight_edit_text.getText())+ "\n" +
                      "current fitness level is: " + String.valueOf(seekBar_current_text.getText()));
                if(gender==1){
                    Toast.makeText(getBaseContext(),getString(R.string.welcome_masculine), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getBaseContext(),getString(R.string.welcome_feminine), Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    public boolean canFinish(){
        if(name_edit_text.getText().toString().trim().length() > 0) {
            if (gender != 0) {
                if (dob_edit_text.getText().toString().trim().length() > 0) {
                    if(lose_fat_chip.isChecked()|| build_muscle_chip.isChecked()||
                            improve_endurance_chip.isChecked()||maintain_body_shape.isChecked()
                            ||improve_athletic_skills_chip.isChecked()){
                        if(using_bodyweight_chip.isChecked()||using_gym_equipment_chip.isChecked()||using_weights_chip.isChecked()){
                            if(im_fine_chip.isChecked()|| no_jumping_chip.isChecked()||low_impact_chip.isChecked()){
                                return true;
                            }
                            else{
                                Toast.makeText(OnboardingActivity.this,getString(R.string.any_knee_problems),Toast.LENGTH_SHORT).show();
                                return  false;
                            }
                        }
                        else{
                            Toast.makeText(OnboardingActivity.this,getString(R.string.how_achieve_goals),Toast.LENGTH_SHORT).show();
                            return  false;
                        }
                    }
                    else{
                        Toast.makeText(OnboardingActivity.this,getString(R.string.select_one_fitness_goal),Toast.LENGTH_SHORT).show();
                        return  false;
                    }
                } else {
                    Toast.makeText(OnboardingActivity.this, getString(R.string.dob_insert), Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(OnboardingActivity.this, getString(R.string.gender_insert), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
            else {
            Toast.makeText(OnboardingActivity.this,getString(R.string.name_insert),Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onBackPressed() {

            new AlertDialog.Builder(OnboardingActivity.this)
                    .setTitle(getString(R.string.exit_onboarding))
                    .setMessage(getString(R.string.are_you_sure_to_leave_onboarding))
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (!sharedPreferences.getBoolean(FINISH, false)) {
                                OnboardingActivity.super.onBackPressed();
                            }
                            else{
                                Intent homeIntent = new Intent(OnboardingActivity.this, HomeActivity.class);
                                startActivity(homeIntent);
                                finish();
                            }

                        }
                    })
                    .setNegativeButton(R.string.no, null)
                    .show();


    }
}