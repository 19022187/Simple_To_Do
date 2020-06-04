package sg.edu.rp.c346.id19022187.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btnAdd, btnClear, btnDelete;
    ListView lv;
    Spinner spn;

    ArrayList<String>alToDo;
    ArrayAdapter<String> aaToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        lv = findViewById(R.id.listView);
        spn = findViewById(R.id.spinner);

        alToDo = new ArrayList<>();

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        et.setHint("Add a new task");
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;

                    case 1:
                        et.setHint("Type in the index of the task to be removed");
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        if (alToDo.isEmpty()){
                            Toast.makeText(MainActivity.this,"You don't have any task to remove", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toDo = et.getText().toString();
                alToDo.add(toDo);
                aaToDo.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(et.getText().toString());
                if (pos>=alToDo.size()){
                    Toast.makeText(MainActivity.this,"Wrong index number", Toast.LENGTH_LONG).show();
                }
                else{
                    alToDo.remove(pos);
                }
                aaToDo.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alToDo.clear();
                aaToDo.notifyDataSetChanged();
            }
        });

        aaToDo = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, alToDo);
        lv.setAdapter(aaToDo);
    }
}
