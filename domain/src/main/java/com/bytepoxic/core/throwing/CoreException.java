package com.bytepoxic.core.throwing;

public abstract class CoreException extends Exception {
	private static final long serialVersionUID = 1L;

	public CoreException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CoreException(String arg0) {
		super(arg0);
	}

	public CoreException(Throwable arg0) {
		super(arg0);
	}
}
