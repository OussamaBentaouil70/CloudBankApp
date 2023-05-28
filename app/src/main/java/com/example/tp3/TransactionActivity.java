package com.example.tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

    }
    ListView trans;

    @Override
    protected void onResume() {
        super.onResume();
        trans = findViewById(R.id.transactions);
        DBHelper dbManager = new DBHelper(this);
        ArrayList<Transaction> ListTrans = new ArrayList<Transaction>();
        Transaction tr1 = new Transaction(R.drawable.phone, "Facture Internet", "295", "12/12/21", "9786534", "R1234567", 10000);
        Transaction tr2 = new Transaction(R.drawable.emission, "Emission d'un", "455", "13/12/21", "9786534", "R1234568", 14000);
        Transaction tr3 = new Transaction(R.drawable.pourcentage, "Paiement d'un", "2450", "22/12/21", "978653", "R1234569", 20000);
        Transaction tr4 = new Transaction(R.drawable.visa, "Paiement par carte ", "2150", "29/12/21", "978653", "R1234560", 17000);
        Transaction tr5 = new Transaction(R.drawable.argent, "Retrait d'espècess", "1000", "09/10/21", "9786534", "R1234564", 10900);
       /* dbManager.addTransaction(tr1);
        dbManager.addTransaction(tr2);
        dbManager.addTransaction(tr3);
        dbManager.addTransaction(tr4);
        dbManager.addTransaction(tr5);*/
        ListTrans.add(tr1);
        ListTrans.add(tr2);
        ListTrans.add(tr3);
        ListTrans.add(tr4);
        ListTrans.add(tr5);
     //   ArrayList<Transaction> ListTrans = (ArrayList<Transaction>) dbManager.getAllTransactions();
        TransactionAdapter adapter = new TransactionAdapter(getApplicationContext(), R.layout.cellule, ListTrans);
        trans.setFocusable(true);
        trans.setEnabled(true);
        trans.setItemsCanFocus(false);
        trans.setAdapter(adapter);
        trans.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), TransactionDetailsActivity.class);
                intent.putExtra("transactionObject", ListTrans.get(position));
                startActivity(intent);
                // Toast.makeText(getApplicationContext(), "item clicked", Toast.LENGTH_LONG);
            }
        });
    }

}