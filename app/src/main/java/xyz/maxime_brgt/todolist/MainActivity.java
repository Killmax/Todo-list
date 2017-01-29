package xyz.maxime_brgt.todolist;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    private EditText title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }

    private void setupListViewListener() {
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        Toast.makeText(getApplicationContext(), R.string.description, Toast.LENGTH_SHORT).show();
                    }
                });

        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {

                        final int Npos = pos;

                        AlertDialog.Builder adBuilder = new AlertDialog.Builder(MainActivity.this);
                        adBuilder.setMessage("Detail of the task");
                        adBuilder.setCancelable(true);

                        final Context context = adBuilder.getContext();
                        final LayoutInflater inflater = LayoutInflater.from(context);
                        final View view = inflater.inflate(R.layout.detail_task, null, false);
                        adBuilder.setView(view);

                        title = (EditText) view.findViewById(R.id.editTitle);
                        content = (EditText) view.findViewById(R.id.editContent);
                        title.setText(items.get(pos).split(" - ")[0]);
                        content.setText(items.get(pos).split(" - ")[1]);

                        adBuilder.setPositiveButton(
                                R.string.save_edit_dial,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        if (title.getText().toString().equals("") || content.getText().toString().equals("")) {
                                            Toast.makeText(getApplicationContext(), R.string.error_add_dial, Toast.LENGTH_SHORT).show();
                                        } else {
                                            String res = title.getText().toString().replace("-", " ") + " - " + content.getText().toString().replace("-", " ");
                                            items.set(Npos, res);
                                            itemsAdapter.notifyDataSetChanged();
                                            writeItems();
                                            Toast.makeText(getApplicationContext(), R.string.success_add_dial, Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }
                                });

                        adBuilder.setNegativeButton(
                                R.string.done_edit_dial,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        items.remove(Npos);
                                        itemsAdapter.notifyDataSetChanged();
                                        writeItems();
                                        Toast.makeText(getApplicationContext(), R.string.deleted_edit_task, Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                });

                        adBuilder.setNeutralButton(
                                R.string.cancel_add_dial,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert11 = adBuilder.create();
                        alert11.show();
                        return true;
                    }
                });
    }

    public void onAddItem(View v) {
        AlertDialog.Builder adBuilder = new AlertDialog.Builder(MainActivity.this);
        adBuilder.setMessage(R.string.title_add_dial);
        final Context context = adBuilder.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.add_task, null, false);
        adBuilder.setView(view);
        adBuilder.setPositiveButton(
                R.string.save_add_dial,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        title = (EditText) view.findViewById(R.id.addTitle);
                        content = (EditText) view.findViewById(R.id.addContent);

                        if (title.getText().toString().equals("") || content.getText().toString().equals("")) {
                            Toast.makeText(getApplicationContext(), R.string.error_add_dial, Toast.LENGTH_SHORT).show();
                        } else {
                            String res = title.getText().toString().replace("-", " ") + " - " + content.getText().toString().replace("-", " ");
                            itemsAdapter.add(res);
                            Toast.makeText(getApplicationContext(), R.string.success_add_dial, Toast.LENGTH_SHORT).show();
                            writeItems();
                            dialog.dismiss();
                        }
                    }
                });

        adBuilder.setNegativeButton(
                R.string.cancel_add_dial,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert11 = adBuilder.create();
        alert11.show();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.app");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.app");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
