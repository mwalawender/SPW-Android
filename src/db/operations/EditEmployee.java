package db.operations;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.spwandroid.R;

public class EditEmployee extends Activity {

	EditText txtName, txtSurname, txtStanowisko, txtDzial, txtPhoneNumber,
			txtEmail, txtPassword;
	Button btnSave;
	Button btnDelete;

	String pid;

	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();

	private static final String URL_UPDATE_EMPLOYEE = "http://192.168.1.4//android_connect//get_product_details.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_EMPLOYEE = "pracownicy";
	private static final String TAG_PID = "ID_PRACOWNIKA";
	private static final String TAG_NAME = "Imie";
	private static final String TAG_SURNAME = "Nazwisko";
	private static final String TAG_STANOWISKO = "Stanowisko";
	private static final String TAG_DZIAL = "Dzial";
	private static final String TAG_PHONE_NUMBER = "Numer telefonu";
	private static final String TAG_EMAIL = "Email";
	private static final String TAG_PASSWORD = "Haslo";

	private static final String KLUCZ_IMIE = "imie";
	private static final String KLUCZ_NAZWISKO = "nazwisko";
	private static final String KLUCZ_STANOWISKO = "stanowisko";
	private static final String KLUCZ_DZIAL = "dzial";
	private static final String KLUCZ_EMAIL = "email";
	private static final String KLUCZ_PHONE_NUMBER = "numer fona";
	private static final String KLUCZ_PASSWORD = "haslo";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_worker);

		btnSave = (Button) findViewById(R.id.btnSave);
		btnDelete = (Button) findViewById(R.id.btnDelete);

		txtName = (EditText) findViewById(R.id.inputName);
		txtSurname = (EditText) findViewById(R.id.inputSurname);
		txtStanowisko = (EditText) findViewById(R.id.inputStanowisko);
		txtDzial = (EditText) findViewById(R.id.inputDzial);
		txtPhoneNumber = (EditText) findViewById(R.id.inputPhoneNumber);
		txtEmail = (EditText) findViewById(R.id.inputEmail);
		txtPassword = (EditText) findViewById(R.id.inputPassword);

		Intent i = getIntent();

		pid = i.getStringExtra(TAG_PID);

		txtName.setText(i.getExtras().getString(KLUCZ_IMIE));
		txtSurname.setText(i.getExtras().getString(KLUCZ_NAZWISKO));
		txtStanowisko.setText(i.getExtras().getString(KLUCZ_STANOWISKO));
		txtDzial.setText(i.getExtras().getString(KLUCZ_DZIAL));
		txtPhoneNumber.setText(i.getExtras().getString(KLUCZ_PHONE_NUMBER));
		txtEmail.setText(i.getExtras().getString(KLUCZ_EMAIL));
		txtPassword.setText(i.getExtras().getString(KLUCZ_PASSWORD));

		Log.i("LOADED PID", pid);

		btnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

			}
		});

		btnDelete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

			}
		});

	}

	class SaveEmployeeDetails extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(EditEmployee.this);
			pDialog.setMessage("Saving product ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {

			String workerName = txtName.getText().toString();
			String workerSurname = txtSurname.getText().toString();
			String workerPosition = txtStanowisko.getText().toString();
			String workerSection = txtDzial.getText().toString();
			String workerPhoneNumber = txtPhoneNumber.getText().toString();
			String workerEmail = txtEmail.getText().toString();
			String workerPassword = txtPassword.getText().toString();

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(TAG_PID, pid));
			params.add(new BasicNameValuePair(TAG_NAME, workerName));
			params.add(new BasicNameValuePair(TAG_SURNAME, workerSurname));
			params.add(new BasicNameValuePair(TAG_STANOWISKO, workerPosition));
			params.add(new BasicNameValuePair(TAG_DZIAL, workerSection));
			params.add(new BasicNameValuePair(TAG_PHONE_NUMBER,
					workerPhoneNumber));
			params.add(new BasicNameValuePair(TAG_EMAIL, workerEmail));
			params.add(new BasicNameValuePair(TAG_PASSWORD, workerPassword));

			JSONObject json = jsonParser.makeHttpRequest(URL_UPDATE_EMPLOYEE,
					"POST", params);

			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {

					Intent i = getIntent();

					setResult(100, i);
					finish();
				} else {

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(String file_url) {

			pDialog.dismiss();
		}
	}
}