package fr.orsys.ama.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;

import fr.orsys.ama.tp2.model.Client;
import fr.orsys.ama.tp2.model.ClientSet;
import fr.orsys.ama.tp2.model.Level;
import fr.orsys.ama.tp2.model.Sexe;

public class AddClientActivity extends AppCompatActivity {
    protected final static String TAG = "fr.orsys.ama.tp2";
    final int AGE_MAX = 60;
    final int AGE_MIN = 18;
    protected EditText nameText;
    protected EditText firstnameText;
    protected EditText emailText;
    protected TextView ageText;
    protected SeekBar ageBar;
    protected RadioGroup sexeGroup;
    protected SwitchMaterial activeSwitch;
    protected Spinner levelSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        nameText = findViewById(R.id.ename);
        firstnameText = findViewById(R.id.efirstname);
        emailText = findViewById(R.id.eemail);
        ageText = findViewById(R.id.tage);
        ageBar = findViewById(R.id.sbage);
        sexeGroup = findViewById(R.id.rgsexe);
        activeSwitch = findViewById(R.id.sactive);
        levelSpinner = findViewById(R.id.slevel);

        ageText.setText("" + AGE_MIN);
        ageBar.setMax(AGE_MAX - AGE_MIN);
        // Implementer le comportement de la SeekBar
        ageBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ageText.setText(String.valueOf(progress + AGE_MIN));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        activeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res = activeSwitch.isChecked();
                if (res)
                    activeSwitch.setText(getString(R.string.yes));
                else
                    activeSwitch.setText(getString(R.string.no));
            }
        });
    }

    public void addClient(View view) {
        Log.i(TAG, "Click");
        String name = nameText.getText().toString();
        String firstname = firstnameText.getText().toString();
        int sexeId = sexeGroup.getCheckedRadioButtonId();
        String seveLabel = getString(R.string.female);
        Sexe unSexe = Sexe.FEMALE;
        if (sexeId == -1) {
            Toast.makeText(this, R.string.sexe_message, Toast.LENGTH_LONG).show();
        } else {
            seveLabel = (sexeId == R.id.rbmale) ? getString(R.string.male) : getString(R.string.female);
            unSexe =  (sexeId == R.id.rbmale) ? Sexe.MALE: Sexe.FEMALE;
            String email = emailText.getText().toString();
            String age = ageText.getText().toString();
            String level = levelSpinner.getSelectedItem().toString();
            Level unLevel = Level.BEGINNER;
            switch (levelSpinner.getSelectedItemPosition()) {
                case 0: unLevel = Level.BEGINNER; break;
                case 1: unLevel = Level.INTERMEDIATE; break;
                case 2: unLevel = Level.ADVANCED; break;
            }
            String activeValue = activeSwitch.isChecked() ? getString(R.string.yes) : getString((R.string.no));
            Client client = new Client(name, firstname, unSexe, email, Integer.valueOf(age), unLevel, activeValue);
            Log.i(TAG, client.toString());
            // ajouter à un ensemble de clients: Singleton
            ClientSet.INSTANCE.getContenu().add(client);
            Log.i(TAG, "un client de plus");
            finish();
        }
    }
}