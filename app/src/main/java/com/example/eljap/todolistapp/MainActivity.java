package com.example.eljap.todolistapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText itemET;
    private Button itemBTN;
    private ListView itemListV;

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemET = findViewById(R.id.editText);
        itemBTN = findViewById(R.id.addBtn);
        itemListV = findViewById(R.id.itemList);

        items = FileHelper.readData( this );
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items );
        itemListV.setAdapter(itemsAdapter);

        itemBTN.setOnClickListener(this);
        itemListV.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addBtn:
                String itemEntered = itemET.getText().toString();
                itemsAdapter.add(itemEntered);
                itemET.setText("");

                FileHelper.writeData(items, this);

                Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        itemsAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
    }
}
