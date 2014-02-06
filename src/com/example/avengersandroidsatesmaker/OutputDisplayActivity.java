package com.example.avengersandroidsatesmaker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

/**
 * a very simple activity with just a edit text for the output I don't know why
 * i documented this !
 * 
 * @author A.Aly
 */
public class OutputDisplayActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_output);
		// Get the result sent and put it in an edit text
		String result = getIntent().getExtras().getString("Result");
		((EditText) findViewById(R.id.editText_output_display)).setText(result);
	}
}
