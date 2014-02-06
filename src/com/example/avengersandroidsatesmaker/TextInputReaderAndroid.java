package com.example.avengersandroidsatesmaker;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *   The class that parses the inputs and stores it as strings to be used to build the circuit
 *   
 * @author M.Anwer
 */
public class TextInputReaderAndroid implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String full_input;
	public String ff_type;
	public int ff_no;
	public int input_no;
	public int output_no;
	public int internals_no;
	public ArrayList<String> inputs;
	public ArrayList<String> outputs;
	public ArrayList<String> internals;
	public ArrayList<ArrayList<String>> design ;
	
	/**
	 * Constructor
	 */
	public TextInputReaderAndroid()
	{
		// Initialized the fields
		inputs = new ArrayList<String>();
		outputs = new ArrayList<String>();
		internals = new ArrayList<String>();
		design = new ArrayList<ArrayList<String>>();
	}
	
	/**
	 * @return All the file
	 */
	public String getFull_input()
	{
		return full_input;
	}

	/**
	 * @return the type of the flip flop
	 */
	public String getFF_type()
	{
		return ff_type;
	}

	/**
	 * @return the number of flipflops
	 */
	public int getFF_no()
	{
		return ff_no;
	}

	/**
	 * @return The number of inputs
	 */
	public int getInput_no()
	{
		return input_no;
	}

	/**
	 * @return The number of outputs
	 */
	public int getOutput_no()
	{
		return output_no;
	}

	/**
	 * @return The number of internals
	 */
	public int getInternals_no()
	{
		return internals_no;
	}

	/**
	 * @return a list of the inputs
	 */
	public ArrayList<String> getInputs()
	{
		return inputs;
	}

	/**
	 * @return a list of the outputs
	 */
	public ArrayList<String> getOutputs()
	{
		return outputs;
	}

	/**
	 * @return a list of the wires(internals)
	 */
	public ArrayList<String> getInternals()
	{
		return internals;
	}

	/**
	 * @return a list containing the connections of the gates
	 */
	public ArrayList<ArrayList<String>> getDesign()
	{
		return design;
	}


	public String toString()
	{
		String result = "";
		
		result += "Inputs : ";
		for (String input : this.inputs)
			result += input + " ";
		result +=   "(" + this.input_no + ")\n";
		
		result += "Outputs : ";
		for (String input : this.outputs)
			result += input + " ";
		result +=   "(" + this.output_no + ")\n";
		
		result += "Internals : ";
		for (String input : this.internals)
			result += input + " ";
		result +=   "(" + this.internals_no + ")\n";
		
		result += "Design :\n";
		for (ArrayList<String> designLine : this.design)
			result += designLine.toString() + "\n";
		
		return result;
	}
	
}