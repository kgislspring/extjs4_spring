package com.extjs.java.model;

public class ExtJSFormResult {

	private boolean result;
	
	public void setResult(boolean result) {
		this.result = result;
	}
	public boolean isResult() {
		return result;
	}
	
	@Override
	public String toString(){
		if(result)
			return "{success:"+this.result+"}";
		else
			return "{error:"+this.result+"}";
	}
	
		
}
