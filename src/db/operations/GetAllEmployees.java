package db.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.spwandroid.R;

import pl.project.Employee;
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

public class GetAllEmployees extends ListActivity {

	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	ArrayList<HashMap<String, String>> productsList;
	ArrayList<Employee> employeeLists;

	// url to get all products list
	private static String url_all_products = "http://192.168.1.4//android_connect//get_all_products.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_EMPLOYEES = "pracownicy";
	private static final String TAG_PID = "ID_PRACOWNIKA";
	private static final String TAG_NAME = "Imie";

	private static final String KLUCZ_IMIE = "imie";
	private static final String KLUCZ_NAZWISKO = "nazwisko";
	private static final String KLUCZ_STANOWISKO = "stanowisko";
	private static final String KLUCZ_DZIAL = "dzial";
	private static final String KLUCZ_EMAIL = "email";
	private static final String KLUCZ_PHONE_NUMBER = "numer fona";
	private static final String KLUCZ_PASSWORD = "haslo";

	// products JSONArray
	JSONArray products = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_products);

		// Hashmap for ListView
		productsList = new ArrayList<HashMap<String, String>>();
		employeeLists = new ArrayList<Employee>();

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

				// Starting new intent
				Intent in = new Intent(getApplicationContext(),
						EditEmployee.class);
				// sending pid to next activity
				in.putExtra(TAG_PID, pid);
				in.putExtra(KLUCZ_IMIE, employeeLists.get(position)
						.getFirstName());
				in.putExtra(KLUCZ_NAZWISKO, employeeLists.get(position)
						.getLastName());
				in.putExtra(KLUCZ_STANOWISKO, employeeLists.get(position)
						.getPosition());
				in.putExtra(KLUCZ_DZIAL, employeeLists.get(position)
						.getSection());
				in.putExtra(KLUCZ_PHONE_NUMBER, employeeLists.get(position)
						.getPhoneNumber());
				in.putExtra(KLUCZ_EMAIL, employeeLists.get(position).getEmail());
				in.putExtra(KLUCZ_PASSWORD, employeeLists.get(position)
						.getPassword());
				
				Log.i("PID", pid);

				// starting new activity and expecting some response back
				startActivityForResult(in, 100);
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
			pDialog = new ProgressDialog(GetAllEmployees.this);
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

					products = json.getJSONArray(TAG_EMPLOYEES);
					Log.i("PRODUCTS SIZE", " " + products.length());

					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						// Storing each json item in variable
						String id = c.getString(TAG_PID);
						String name = c.getString(TAG_NAME);

						String workerId = c.getString("ID_PRACOWNIKA");
						String workerName = c.getString("Imie");
						String workerSurname = c.getString("Nazwisko");
						String workerPosition = c.getString("Stanowisko");
						String workerSection = c.getString("Dzial");
						String workerPhoneNumber = c
								.getString("Numer telefonu");
						String workerEmail = c.getString("Email");
						String workerPassword = c.getString("Haslo");

						employeeLists
								.add(new Employee(Integer.parseInt(workerId),
										workerName, workerSurname,
										workerPosition, workerSection,
										workerPhoneNumber, workerEmail,
										workerPassword));

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

			// Log.i("TAG: ", employeeLists.toString());

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
							GetAllEmployees.this, productsList,
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
