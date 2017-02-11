package com.cooksys.assessment;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * This Items class is a wrapper object for the list computer parts (String objects)
 * to be saved as XML file.
 * @author Tan Pham
 * @version 2/10/2017
 */

@XmlRootElement (name = "Computer_parts")
public class Items{
	private List<String> items = new ArrayList<String>();
	
	@XmlElements(value = {@XmlElement(name = "Item", type = String.class)})
	public List<String> getItems(){
		return items;
	}
		
	public void addItems(String it){
		items.add(it);
	}
}
