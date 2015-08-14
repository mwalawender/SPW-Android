package com.example.spwandroid;

import db.operations.GetAllProjects;
import db.operations.GetAllTasks;
import db.operations.GetAllEmployees;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminWindow extends Activity implements OnClickListener {

	Button btnShowWorkers, btnShowProjects, btnShowTasks, btnSendEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_layout);

		btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
		btnShowProjects = (Button) findViewById(R.id.btnShowProjects);
		btnShowTasks = (Button) findViewById(R.id.btnShowTasks);
		btnShowWorkers = (Button) findViewById(R.id.btnShowWorkers);

		btnSendEmail.setOnClickListener(this);
		btnShowProjects.setOnClickListener(this);
		btnShowTasks.setOnClickListener(this);
		btnShowWorkers.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnShowWorkers:
			startActivity(new Intent(AdminWindow.this,
					GetAllEmployees.class));
			break;
		case R.id.btnShowProjects:
			startActivity(new Intent(AdminWindow.this,
					GetAllProjects.class));
			break;
		case R.id.btnSendEmail:
			startActivity(new Intent(AdminWindow.this, EmailWindow.class));
			break;
		case R.id.btnShowTasks:
			startActivity(new Intent(AdminWindow.this, GetAllTasks.class));
			break;
		default:
			break;
		}
	}

}
