package com.bytepoxic.core.throwing;

public abstract class CoreRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CoreRuntimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CoreRuntimeException(String arg0) {
		super(arg0);
	}

	public CoreRuntimeException(Throwable arg0) {
		super(arg0);
	}
}
