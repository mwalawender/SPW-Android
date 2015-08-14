package db.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pl.project.Task;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.spwandroid.R;

public class GetAllTasks extends ListActivity {

	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	ArrayList<HashMap<String, String>> productsList;
	ArrayList<Task> taskLists;

	// url to get all products list
	private static String url_all_products = "http://192.168.1.4//android_connect//get_all_tasks.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PROJECTS = "zadania";
	private static final String TAG_PID = "ID_TASKU";
	private static final String TAG_NAME = "Nazwa_tasku";

	private static final String KLUCZ_NAZWA = "nazwa";
	private static final String KLUCZ_OPIS = "opis";
	private static final String KLUCZ_DATA_START = "datas";
	private static final String KLUCZ_DATA_END = "datae";

	// products JSONArray
	JSONArray products = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_products);

		// Hashmap for ListView
		productsList = new ArrayList<HashMap<String, String>>();
		taskLists = new ArrayList<Task>();

		// Loading products in Background Thread
		new LoadAllProducts().execute();

		// Get listview
		ListView lv = getListView();

		// on seleting single product
		// launching Edit Product Screen
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String pid = ((TextView) view.findViewById(R.id.pid)).getText()
						.toString();
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == 100) {

			Intent intent = getIntent();
			finish();
			startActivity(intent);
		}

	}

	class LoadAllProducts extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(GetAllTasks.this);
			pDialog.setMessage("Loading products. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {

			List<NameValuePair> params = new ArrayList<NameValuePair>();

			JSONObject json = jParser.makeHttpRequest(url_all_products, "GET",
					params);

			Log.i("NANA", "DO IN BACKGROUND");
			try {

				int success = json.getInt(TAG_SUCCESS);
				Log.i("SUCCESS: ", " " + success);
				if (success == 1) {

					Log.i("SIZE 2", "SUCCESS 1");

					products = json.getJSONArray(TAG_PROJECTS);
					Log.i("PRODUCTS SIZE", " " + products.length());

					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						String id = c.getString(TAG_PID);
						String name = c.getString(TAG_NAME);

						String taskId = c.getString("ID_TASKU");
						String taskName = c.getString("Nazwa_tasku");
						String taskDescription = c.getString("Opis");
						String taskStartDate = c.getString("Data_rozpoczecia");
						String taskEndDate = c.getString("Data_zakonczenia");

						taskLists.add(new Task(Integer.parseInt(taskId),
								taskName, taskDescription, taskStartDate,
								taskEndDate));

						HashMap<String, String> map = new HashMap<String, String>();

						map.put(TAG_PID, id);
						map.put(TAG_NAME, name);

						// adding HashList to ArrayList
						productsList.add(map);
					}
				} else {

					Intent i = new Intent(getApplicationContext(),
							AddNewWorker.class);

					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			} catch (JSONException e) {
				e.getMessage();
				Log.e("EXEPSZYN", e.getMessage().toString());
			}

			Log.i("TAG: ", taskLists.toString());

			return null;
		}

		protected void onPostExecute(String file_url) {

			pDialog.dismiss();

			runOnUiThread(new Runnable() {
				public void run() {

					ListAdapter adapter = new SimpleAdapter(GetAllTasks.this,
							productsList, R.layout.list_item, new String[] {
									TAG_PID, TAG_NAME }, new int[] { R.id.pid,
									R.id.name });
					setListAdapter(adapter);

				}
			});

		}

	}
}
