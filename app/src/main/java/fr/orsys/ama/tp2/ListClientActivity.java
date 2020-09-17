package fr.orsys.ama.tp2;

import android.app.ListActivity;
import android.os.Bundle;

public class ListClientActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_client);
        // ClientAdapter sert à creer une ligne
        ClientAdapter clientAdapter = new ClientAdapter(this);
        setListAdapter(clientAdapter);
    }
}
