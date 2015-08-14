package com.example.spwandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.operations.AddNewWorker;

import pl.project.Employee;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginWindow extends Activity {

	Button btnLogin;
	EditText editTextEmail, editTextPassword;

	private ProgressDialog pDialog;

	JSONParser jParser = new JSONParser();

	ArrayList<HashMap<String, String>> productsList;
	ArrayList<Employee> employeeLists;
	boolean isValidationOk = false;

	private static final String URL_EMPLOYEES = "http://192.168.1.4//android_connect//get_all_products.php";

	private static final String TAG_SUCCESS = "success";
	private static final String TAG_EMPLOYEES = "pracownicy";
	private static final String TAG_PID = "ID_PRACOWNIKA";
	private static final String TAG_NAME = "Imie";

	private static final String TAG = "LoginWindow: ";

	JSONArray employees = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);

		productsList = new ArrayList<HashMap<String, String>>();
		employeeLists = new ArrayList<Employee>();

		btnLogin = (Button) findViewById(R.id.btnLogin);
		editTextEmail = (EditText) findViewById(R.id.editTextEmail);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);

		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String email = editTextEmail.getText().toString();
				String password = editTextPassword.getText().toString();
				if (email.isEmpty() || password.isEmpty()) {
					Toast.makeText(getApplicationContext(),
							"BRAK EMAIL LUB HASLA", Toast.LENGTH_SHORT).show();
				} else {
					new LoadAllProducts().execute();
				}
			}
		});

	}

	class LoadAllProducts extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(LoginWindow.this);
			pDialog.setMessage("Connecting with server, please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		protected String doInBackground(String... args) {

			List<NameValuePair> params = new ArrayList<NameValuePair>();

			JSONObject json = jParser.makeHttpRequest(URL_EMPLOYEES, "GET",
					params);
			try {

				int success = json.getInt(TAG_SUCCESS);
				Log.i(TAG, " " + success);
				if (success == 1) {

					employees = json.getJSONArray(TAG_EMPLOYEES);
					Log.i(TAG, " " + employees.length());

					for (int i = 0; i < employees.length(); i++) {
						JSONObject c = employees.getJSONObject(i);

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

						HashMap<String, String> map = new HashMap<String, String>();

						map.put(TAG_PID, id);
						map.put(TAG_NAME, name);

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

			return null;
		}

		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {

					String email = editTextEmail.getText().toString();
					String password = editTextPassword.getText().toString();
					for (Employee employee : employeeLists) {

						if (employee.getEmail().equals(email)) {
							if (employee.getPassword().equals(password)) {
								Log.i("LOGOWANIE: ", "LOGOWANIE POMYSLNIE");
								Toast.makeText(getApplicationContext(),
										"Logowanie pomyslnie",
										Toast.LENGTH_LONG).show();
								startActivity(new Intent(LoginWindow.this,
										AdminWindow.class));
								break;
							} else {
								Toast.makeText(getApplicationContext(),
										"Wrong password", Toast.LENGTH_LONG)
										.show();
							}
						} else {
							Toast.makeText(getApplicationContext(),
									"Wrong email", Toast.LENGTH_LONG).show();
						}
					}
				}
			});
		}
	}
}