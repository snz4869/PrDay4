package id.adiyusuf.projectaccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class ListDetailDataActivity extends AppCompatActivity {

    String id;
    EditText edit_id, edit_nama_dpn,edit_nama_blk, edit_alamat, edit_rek,edit_status,edit_cif;
    Button btn_delete,btn_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail_data);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Account");

        edit_id = findViewById(R.id.edit_id);
        edit_nama_dpn = findViewById(R.id.edit_nama_dpn);
        edit_nama_blk = findViewById(R.id.edit_nama_blk);
        edit_alamat = findViewById(R.id.edit_alamat);
        edit_rek = findViewById(R.id.edit_rek);
        edit_status = findViewById(R.id.edit_status);
        edit_cif = findViewById(R.id.edit_cif);
        btn_delete = findViewById(R.id.btn_delete);
        btn_edit = findViewById(R.id.btn_edit);

        Intent receiveIntent = getIntent();
        id = receiveIntent.getStringExtra(Konfigurasi.ACC_ID);
        edit_id.setText(id);

        getJSON();

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ListDetailDataActivity.this);
                alertDialogBuilder.setMessage("Delete Data");

                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                delete();
                            }
                        });

                alertDialogBuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });


        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit();
            }
        });
    }

    private void edit() {
        final String nama_dpn = edit_nama_dpn.getText().toString();
        final String nama_blk = edit_nama_blk.getText().toString();
        final String alamat = edit_alamat.getText().toString();
        final String no_rek = edit_rek.getText().toString();
        final String status = edit_status.getText().toString();
        final String cif = edit_cif.getText().toString();

        class Edit extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ListDetailDataActivity.this,
                        "Updating Data...", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_ACC_ID, id);
                hashMap.put(Konfigurasi.KEY_ACC_NAMA_DPN, nama_dpn);
                hashMap.put(Konfigurasi.KEY_ACC_NAMA_BLK, nama_blk);
                hashMap.put(Konfigurasi.KEY_ACC_ALAMAT, alamat);
                hashMap.put(Konfigurasi.KEY_ACC_REK, no_rek);
                hashMap.put(Konfigurasi.KEY_ACC_STATUS, status);
                hashMap.put(Konfigurasi.KEY_ACC_CIF, cif);

                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_UPDATE, hashMap);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                displayDetailData(message);
            }
        }

        Edit ed = new Edit();
        ed.execute();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ListDetailDataActivity.this);
        alertDialogBuilder.setMessage("Update Data");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(ListDetailDataActivity.this, ListDataActivity.class));
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void delete() {
        class Delete extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ListDetailDataActivity.this,
                        "Deleting Data...", "Harap menunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... params) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_DELETE, id);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                displayDetailData(message);
            }
        }
        Delete del = new Delete();
        del.execute();
        startActivity(new Intent(ListDetailDataActivity.this, ListDataActivity.class));
    }

    private void getJSON() {

        // batuan dari class AsynTask
        class GetJSON extends AsyncTask<Void,Void,String> { // boleh membuat class dalam method (Inner Class)
            ProgressDialog loading;

            @Override
            protected void onPreExecute() { // sebelum proses
                super.onPreExecute();
                loading = ProgressDialog.show(ListDetailDataActivity.this,
                        "Mengambil Data","Harap Menunggu...",
                        false,false);
            }

            @Override
            protected String doInBackground(Void... voids) { // saat proses
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL,id);
                return result;
            }

            @Override
            protected void onPostExecute(String message) { // setelah proses
                super.onPostExecute(message);
                loading.dismiss();
                displayDetailData(message);
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void displayDetailData(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject object = result.getJSONObject(0);

            String nama_dpn = object.getString(Konfigurasi.TAG_JSON_NAMA_DPN);
            String nama_blk = object.getString(Konfigurasi.TAG_JSON_NAMA_BLK);
            String alamat = object.getString(Konfigurasi.TAG_JSON_ALAMAT);
            String rek = object.getString(Konfigurasi.TAG_JSON_REK);
            String status = object.getString(Konfigurasi.TAG_JSON_STATUS);
            String cif = object.getString(Konfigurasi.TAG_JSON_CIF);

            edit_nama_dpn.setText(nama_dpn);
            edit_nama_blk.setText(nama_blk);
            edit_alamat.setText(alamat);
            edit_rek.setText(rek);
            edit_status.setText(status);
            edit_cif.setText(cif);

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        onBackPressed();
        return true;
    }

}