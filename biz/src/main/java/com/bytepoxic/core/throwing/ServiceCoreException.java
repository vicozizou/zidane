package com.bytepoxic.core.throwing;

public class ServiceCoreException extends CoreException {
	private static final long serialVersionUID = 1L;

	public ServiceCoreException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ServiceCoreException(String arg0) {
		super(arg0);
	}

	public ServiceCoreException(Throwable arg0) {
		super(arg0);
	}
}
