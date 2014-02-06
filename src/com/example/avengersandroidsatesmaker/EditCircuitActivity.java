package com.example.avengersandroidsatesmaker;

import java.util.ArrayList;
import java.util.Set;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.Toast;

/**
 * The Activity where the user edit the circuit
 * The user can add or remove inputs, outputs, internals and edit the design
 * 
 * @author A.Aly
 */
public class EditCircuitActivity extends Activity
{
	/*
	 * The input, output, internals names and design
	 */
	private ArrayList<String> inputNames;
	private ArrayList<String> outputNames;
	private ArrayList<String> internalNames;

	/* The adapter for the grid view */
	class MyAdapter extends BaseAdapter
	{
		ArrayList<String> items;

		public MyAdapter(ArrayList<String> items)
		{
			this.items = items;
		}

		public int getCount()
		{
			return items.size();
		}

		@Override
		public Object getItem(int position)
		{
			return items.get(position);
		}

		@Override
		public long getItemId(int position)
		{
			return 0;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent)
		{
			// Make a button with the name of the item
			Button button = new Button(getApplicationContext());
			button.setText(items.get(position));

			// When it's clicked remove the input
			button.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (items == inputNames)
					{
						inputNames.remove(position);
						setInputsGrid();
					} else if (items == outputNames)
					{
						outputNames.remove(position);
						setOutputsGrid();
					} else if (items == internalNames)
					{
						internalNames.remove(position);
						setInternalsGrid();
					}

				}
			});

			return button;
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_circuit);

		// See if there's a saved preference to load
		loadSavedDesign();

	}

	/**
	 * Clears the previous info of the input, output, internals names and design
	 */
	public void clearFields(View v)
	{
		// Clear the date
		inputNames = new ArrayList<String>();
		outputNames = new ArrayList<String>();
		internalNames = new ArrayList<String>();

		// set the grids
		setInputsGrid();
		setOutputsGrid();
		setInternalsGrid();
		((EditText) findViewById(R.id.editText1)).setText("");
	}

	/**
	 * The listener for the add input button add the name to the input names and
	 * clears the text field
	 */
	public void addInput(View v)
	{
		// Get the text and remove the spaces
		String newInput = ((EditText) findViewById(R.id.editText_add_input))
				.getText().toString();
		newInput = newInput.replace(" ", "");
		newInput = newInput.replace("\n", "");

		// if it's already there make a toast else add it
		if (inputNames.contains(newInput))
		{
			Toast.makeText(getApplicationContext(),
					"The input \"" + newInput + "\" is already there",
					Toast.LENGTH_SHORT).show();
		} else
		{
			inputNames.add(0, newInput);
		}

		// clear the edit text
		((EditText) findViewById(R.id.editText_add_input)).setText("");

		// Set the grid
		setInputsGrid();

	}

	/**
	 * The listener for the add output button add the name to the output names
	 * and clears the edit text field
	 */
	public void addOutput(View v)
	{
		// Get the text and remove the spaces
		String newOutput = ((EditText) findViewById(R.id.editText_add_output))
				.getText().toString();
		newOutput = newOutput.replace(" ", "");
		newOutput = newOutput.replace("\n", "");

		// if it's already there make a toast else add it
		if (outputNames.contains(newOutput))
		{
			Toast.makeText(getApplicationContext(),
					"The output \"" + newOutput + "\" is already there",
					Toast.LENGTH_SHORT).show();
		} else
		{
			outputNames.add(0, newOutput);
		}

		// clear the edit text
		((EditText) findViewById(R.id.editText_add_output)).setText("");

		// Set the grid
		setOutputsGrid();

	}

	/**
	 * The listener for the add internal button add the name to the internal
	 * names and clears the edit text field
	 */
	public void addInternal(View v)
	{
		// Get the text and remove the spaces
		String newInternal = ((EditText) findViewById(R.id.editText_add_internal))
				.getText().toString();
		newInternal = newInternal.replace(" ", "");
		newInternal = newInternal.replace("\n", "");

		// if it's already there make a toast else add it
		if (internalNames.contains(newInternal))
		{
			Toast.makeText(getApplicationContext(),
					"The internal \"" + newInternal + "\" is already there",
					Toast.LENGTH_SHORT).show();
		} else
		{
			internalNames.add(0, newInternal);
		}

		// clear the edit text
		((EditText) findViewById(R.id.editText_add_internal)).setText("");

		// Set the grid
		setInternalsGrid();

	}

	/**
	 * Sets the grid which has the buttons with the inputs
	 */
	public void setInputsGrid()
	{
		// Find the grid view and set its adapter
		GridView inputGridView = (GridView) findViewById(R.id.gridView_inputs);
		inputGridView.setAdapter(new MyAdapter(inputNames));

		// Set the pading
		int paddingRight = ((Button) findViewById(R.id.button_add_input))
				.getWidth();
		inputGridView.setPadding(0, 0, paddingRight, 0);

	}

	/**
	 * Sets the grid which has the buttons with the ouputs
	 */
	public void setOutputsGrid()
	{
		// Find the grid view and set its adapter
		GridView outputGridView = (GridView) findViewById(R.id.gridView_outputs);
		outputGridView.setAdapter(new MyAdapter(outputNames));

		// Set the pading
		int paddingRight = ((Button) findViewById(R.id.button_add_input))
				.getWidth();
		outputGridView.setPadding(0, 0, paddingRight, 0);

	}

	/**
	 * Sets the grid which has the buttons with the internals
	 */
	public void setInternalsGrid()
	{
		// Find the grid view and set its adapter
		GridView internalGridView = (GridView) findViewById(R.id.gridView_internals);
		internalGridView.setAdapter(new MyAdapter(internalNames));

		// Set the padding
		int paddingRight = ((Button) findViewById(R.id.button_add_internal))
				.getWidth();
		internalGridView.setPadding(0, 0, paddingRight, 0);

	}

	/**
	 * Makes a text input reader from the fields and send it to the main
	 * activity
	 */
	public void sendReader(View v)
	{
		// Fill the text input reader
		TextInputReaderAndroid reader = new TextInputReaderAndroid();

		// The names of stuff
		reader.inputs = inputNames;
		reader.outputs = outputNames;
		reader.internals = internalNames;

		// The size of stuff
		reader.input_no = inputNames.size();
		reader.output_no = outputNames.size();
		reader.internals_no = internalNames.size();

		// Split the text in the edit text to array of strings
		ArrayList<ArrayList<String>> design = new ArrayList<ArrayList<String>>();
		EditText editText = (EditText) findViewById(R.id.editText1);
		String oneLineDesign = editText.getText().toString();
		String[] linesDesign = oneLineDesign.split("\n");

		// Handle each line of the design
		for (String string : linesDesign)
		{
			ArrayList<String> singleLine = new ArrayList<String>();
			string = string.replace("(", " ");
			string = string.replace(")", " ");
			string = string.replace(",", " ");
			String[] splittedDesign = string.split(" ");
			for (String word : splittedDesign)
			{
				if(!word.equals(""))
					singleLine.add(word);
			}
				
			// Make the first word upper case
			if (singleLine.size() > 0)
				singleLine.set(0, singleLine.get(0).toUpperCase());
			
			reader.design.add(singleLine);
		}

		// Save the info for when accessing the activity again
		saveCurrentDesign();

		// Send the text input reader
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), MainActivity.class);
		intent.putExtra("Reader", reader);
		startActivity(intent);
		finish();
	}

	/**
	 * Saves the current design to be loaded when we enter this activity in the
	 * future
	 */
	private void saveCurrentDesign()
	{
		Editor saved = getPreferences(MODE_PRIVATE).edit();

		// Save The design
		String design = ((EditText) findViewById(R.id.editText1)).getText()
				.toString();
		saved.putString("Design", design);
		
		// Save the inputs
		saved.putInt("Inputs Size", inputNames.size());
		for (int i = 0; i < inputNames.size(); i++)
			saved.putString("Input" + i, inputNames.get(i));
		
		// Save the outputs
		saved.putInt("Outputs Size", outputNames.size());
		for (int i = 0; i < outputNames.size(); i++)
			saved.putString("Output" + i, outputNames.get(i));
		
		// Save the internals
		saved.putInt("Internals Size", internalNames.size());
		for (int i = 0; i < internalNames.size(); i++)
			saved.putString("Internal" + i, internalNames.get(i));
		
		saved.commit();
		
		
	}

	/**
	 * Tries to load the saved design
	 */
	private void loadSavedDesign()
	{
		try
		{
			clearFields(null);
			// Get the data
			SharedPreferences saved = getPreferences(MODE_PRIVATE);

			// Get the design
			String design = saved.getString("Design", "");
			((EditText) findViewById(R.id.editText1)).setText(design);
			
			// Get the inputs
			int inputsSize = saved.getInt("Inputs Size", 0);
			for (int i = 0; i < inputsSize; i++)
				inputNames.add(saved.getString("Input" + i, ""));
			
			// Get the outputs
			int outputSize = saved.getInt("Outputs Size", 0);
			for (int i = 0; i < outputSize; i++)
				outputNames.add(saved.getString("Output" + i, ""));	
			
			// Get the Internals
			int internalsSize = saved.getInt("Internals Size", 0);
			for (int i = 0; i < internalsSize; i++)
				internalNames.add(saved.getString("Internal" + i, ""));	
			

		} catch (Exception e)
		{
			clearFields(null);
		}
	}

}
