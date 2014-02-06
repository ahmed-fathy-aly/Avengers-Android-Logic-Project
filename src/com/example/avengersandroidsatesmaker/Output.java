package com.example.avengersandroidsatesmaker;


/**
 *  The output gate
 *  
 * @author A.Aly
 * 
 */
public class Output extends Gate
{
	// Constructor
	public Output(String name)
	{
		super();
		this.name = name;
	}
	
	// Methods


	public boolean getValue()
	{
		try
		{
			Wire inputWire = this.getInputWires().get(0);
			return inputWire.getValue();
		} catch (Exception e)
		{
			MainActivity.storeMessage("Error, Output " + this.name + " failed to calculate its value, please check its connections\n");
			return false;
		}
	}
}
