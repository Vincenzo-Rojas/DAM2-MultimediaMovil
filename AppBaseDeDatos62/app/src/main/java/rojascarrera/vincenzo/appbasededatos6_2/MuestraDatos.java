package rojascarrera.vincenzo.appbasededatos6_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MuestraDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestradatos);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<String> lista =
                getIntent().getStringArrayListExtra("lista");

        myAdapter adapter = new myAdapter(lista);
        recyclerView.setAdapter(adapter);
    }
}
