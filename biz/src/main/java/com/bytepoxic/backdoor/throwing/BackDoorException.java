package com.bytepoxic.backdoor.throwing;

public class BackDoorException extends Exception {
	private static final long serialVersionUID = 1L;

	public BackDoorException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BackDoorException(String arg0) {
		super(arg0);
	}
}
