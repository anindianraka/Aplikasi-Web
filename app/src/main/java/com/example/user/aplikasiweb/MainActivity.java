package com.example.user.aplikasiweb;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ConnectInternetClass c1;
    static TextView myText;
    ArrayAdapter<CharSequence> list_spinner;
    Spinner spin;
    EditText textIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spin = (Spinner) findViewById(R.id.spin);
        myText = (TextView)findViewById(R.id.myResult);
        textIn = (EditText)findViewById(R.id.inputAlamat);

        list_spinner = ArrayAdapter.createFromResource(this, R.array.menu, android.R.layout.simple_spinner_item);
        list_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(list_spinner);

    }

    public void doSomething(View view) {
        String url, link_url, pro;

        url = textIn.getText().toString();
        pro = spin.getSelectedItem().toString();
        c1 = new ConnectInternetClass(this);

        if (!url.isEmpty()) {
            if (checkConnection()) {

                link_url = pro+url;
                c1.execute(link_url);

            } else {
                Toast.makeText(this, "check your internet connection", Toast.LENGTH_SHORT).show();
                myText.setText("Tidak ada internet koneksi, beli paketan dulu");

            }
        }

        else {
            myText.setText("Url kosong , jangan sampai kosong seperti hatimu..");
        }


    }

    private boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
