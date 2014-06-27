package com.dianping.zebra.group.exception;

public class WriteDsNotFoundException extends DalException {

   private static final long serialVersionUID = -1726616148252641312L;

	public WriteDsNotFoundException() {
		super();
	}

	public WriteDsNotFoundException(String message) {
		super(message);
	}

	public WriteDsNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public WriteDsNotFoundException(Throwable cause) {
		super(cause);
	}
}
