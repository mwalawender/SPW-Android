package db.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pl.project.Project;
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

public class GetAllProjects extends ListActivity {

	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	ArrayList<HashMap<String, String>> productsList;
	ArrayList<Project> projectLists;

	// url to get all products list
	private static String url_all_products = "http://192.168.1.103//android_connect//get_all_projects.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PROJECTS = "projekty";
	private static final String TAG_PID = "ID_PROJEKTU";
	private static final String TAG_NAME = "Nazwa";

	private static final String TAG_OPIS = "Opis";
	private static final String TAG_DATA_START = "Data rozpoczecia";
	private static final String TAG_DATA_END = "Data zakonczenia";

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
		projectLists = new ArrayList<Project>();

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

	// Response from Edit Product Activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// if result code 100
		if (resultCode == 100) {
			// if result code 100 is received
			// means user edited/deleted product
			// reload this screen again
			Intent intent = getIntent();
			finish();
			startActivity(intent);
		}

	}

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllProducts extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(GetAllProjects.this);
			pDialog.setMessage("Loading products. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All products from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_products, "GET",
					params);

			Log.i("NANA", "DO IN BACKGROUND");
			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);
				Log.i("SUCCESS: ", " " + success);
				if (success == 1) {
					// products found
					// Getting Array of Products

					Log.i("SIZE 2", "SUCCESS 1");

					products = json.getJSONArray(TAG_PROJECTS);
					Log.i("PRODUCTS SIZE", " " + products.length());

					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						// Storing each json item in variable
						String id = c.getString(TAG_PID);
						String name = c.getString(TAG_NAME);

						String projectId = c.getString("ID_PROJEKTU");
						String projectName = c.getString("Nazwa");
						String projectDescription = c.getString("Opis");
						String projectStartDate = c
								.getString("Data_rozpoczecia");
						String projectEndDate = c.getString("Data_zakonczenia");

						projectLists.add(new Project(Integer
								.parseInt(projectId), projectName,
								projectDescription, projectStartDate,
								projectEndDate));

						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_PID, id);
						map.put(TAG_NAME, name);

						// adding HashList to ArrayList
						productsList.add(map);
					}
				} else {
					// no products found
					// Launch Add New product Activity
					Intent i = new Intent(getApplicationContext(),
							AddNewWorker.class);

					// Closing all previous activities
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			} catch (JSONException e) {
				e.getMessage();
				Log.e("EXEPSZYN", e.getMessage().toString());
			}

			Log.i("TAG: ", projectLists.toString());

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					ListAdapter adapter = new SimpleAdapter(
							GetAllProjects.this, productsList,
							R.layout.list_item, new String[] { TAG_PID,
									TAG_NAME },
							new int[] { R.id.pid, R.id.name });
					// updating listview
					setListAdapter(adapter);

				}
			});

		}

	}
}
