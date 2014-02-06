package com.example.avengersandroidsatesmaker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * The activity which contains the basic buttons which allow the user to 
 * either go to the circuit editor or show the output
 * 
 * @author A.Aly
 *	 * */
public class MainActivity extends Activity
{

	TextInputReaderAndroid reader;
	static boolean foundError = false;
	static String message;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		foundError = false;

		// Receive the reader if it's found
		if (getIntent().getExtras() != null)
		{
			reader = (TextInputReaderAndroid) getIntent().getExtras()
					.getSerializable("Reader");
			Toast.makeText(getApplicationContext(), reader.toString(),
					Toast.LENGTH_SHORT).show();
			Toast.makeText(getApplicationContext(),
					"Now, Choose  the Output type", Toast.LENGTH_SHORT).show();
		} else
		{
			Toast.makeText(getApplicationContext(), "Edit the Circuit",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * The listener for the Edit Circuit button starts the Edit Circuit Activity
	 * for result The result is a Text Input Reader
	 */
	public void goToCircuitEditor(View v)
	{
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), EditCircuitActivity.class);
		startActivity(intent);
		finish();
	}

	/**
	 * The listener for the normal output button tries to create a circuit from
	 * the reader and get its normal state diagram
	 */
	public void generateNormalOutput(View v)
	{
		foundError = false;
		// Try creating a circuit
		Circuit circuit = new Circuit();
		try
		{
			circuit.createCicuit(reader);
		} catch (Exception e)
		{
			Toast.makeText(
					getApplicationContext(),
					"Failed to create the circuit...Edit again" + "("
							+ e.getMessage() + ")", Toast.LENGTH_SHORT).show();
		}
		if (foundError)
			Toast.makeText(getApplicationContext(),
					"Error when creating : " + message, Toast.LENGTH_SHORT)
					.show();

		// Try generating an output
		String result = "";
		try
		{
			result = circuit.generateAllOutputs();
		} catch (Exception e)
		{
			Toast.makeText(
					getApplicationContext(),
					"Failed to generate the Output...Edit again" + "("
							+ e.getMessage() + ")", Toast.LENGTH_SHORT).show();
		}

		if (foundError)
			Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
					.show();

		// Call the activity the displays the output
		Intent intent = new Intent(getApplicationContext(),
				OutputDisplayActivity.class);
		intent.putExtra("Result", result);
		startActivity(intent);
	}

	/**
	 * The listener for the simplified output button tries to create a circuit
	 * from the reader and get its normal state diagram
	 */
	public void generateSimplifiedOutput(View v)
	{
		foundError = false;

		// Try creating a circuit
		Circuit circuit = new Circuit();
		try
		{
			circuit.createCicuit(reader);
		} catch (Exception e)
		{
			Toast.makeText(
					getApplicationContext(),
					"Failed to create the circuit...Edit again" + "("
							+ e.getMessage() + ")", Toast.LENGTH_SHORT).show();
		}
		if (foundError)
			Toast.makeText(getApplicationContext(),
					"Error when creating : " + message, Toast.LENGTH_SHORT)
					.show();

		// Try generating an output
		String result = "";
		try
		{
			result = circuit.generateSimplifiedOutput();
		} catch (Exception e)
		{
			Toast.makeText(
					getApplicationContext(),
					"Failed to generate the Output...Edit again" + "("
							+ e.getMessage() + ")", Toast.LENGTH_SHORT).show();
		}

		if (foundError)
			Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
					.show();

		// Call the activity the displays the output
		Intent intent = new Intent(getApplicationContext(),
				OutputDisplayActivity.class);
		intent.putExtra("Result", result);
		startActivity(intent);
	}

	/**
	 * If it's the first error message then store it
	 */
	public static void storeMessage(String errorMessage)
	{
		if (!foundError)
		{
			message = errorMessage;
			foundError = true;
		}
	}
}
