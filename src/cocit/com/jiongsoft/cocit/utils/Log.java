package com.jiongsoft.cocit.utils;

import com.jiongsoft.cocit.utils.log.ILog;
import com.jiongsoft.cocit.utils.log.Logs;

public abstract class Log {
	private static ILog log = Logs.getLog(Log.class);
	private static boolean traceDebug = false;
	private static boolean traceInfo = false;
	private static boolean traceWarn = false;
	private static boolean traceError = true;

	/**
	 * 
	 * @param message
	 * @param operationArgs
	 *            可变参数，最后一个参数可以是Throwable
	 */
	public static void debug(String message, Object... args) {
		if (args.length == 0) {
			log.debug(message);
		} else if (args[args.length - 1] instanceof Throwable) {
			if (traceDebug) {
				if (args.length == 1) {
					log.debug(message, (Throwable) args[0]);
				} else {
					log.debug(String.format(message, args), (Throwable) args[args.length - 1]);
				}
			} else {
				Throwable ex;
				if (args.length == 1) {
					ex = (Throwable) args[0];
				} else {
					ex = (Throwable) args[args.length - 1];
				}
				Throwable cause = ex.getCause();
				log.debug(message + "\n\t" + ex.toString() + "\n\t" + cause);
			}
		} else {
			log.debugf(message, args);
		}
	}

	/**
	 * 
	 * @param message
	 * @param operationArgs
	 *            可变参数，最后一个参数可以是Throwable
	 */
	public static void info(String message, Object... args) {
		if (args.length == 0) {
			log.info(message);
		} else if (args[args.length - 1] instanceof Throwable) {
			if (traceInfo) {
				if (args.length == 1) {
					log.info(message, (Throwable) args[0]);
				} else {
					log.info(String.format(message, args), (Throwable) args[args.length - 1]);
				}
			} else {
				Throwable ex;
				if (args.length == 1) {
					ex = (Throwable) args[0];
				} else {
					ex = (Throwable) args[args.length - 1];
				}
				Throwable cause = ex.getCause();
				log.info(message + "\n\t" + ex.toString() + "\n\t" + cause);
			}
		} else {
			log.infof(message, args);
		}
	}

	/**
	 * 
	 * @param message
	 * @param operationArgs
	 *            可变参数，最后一个参数可以是Throwable
	 */
	public static void warn(String message, Object... args) {
		if (args.length == 0) {
			log.warn(message);
		} else if (args[args.length - 1] instanceof Throwable) {
			if (traceWarn) {
				if (args.length == 1) {
					log.warn(message, (Throwable) args[0]);
				} else {
					log.warn(String.format(message, args), (Throwable) args[args.length - 1]);
				}
			} else {
				Throwable ex;
				if (args.length == 1) {
					ex = (Throwable) args[0];
				} else {
					ex = (Throwable) args[args.length - 1];
				}
				Throwable cause = ex.getCause();
				log.warn(message + "\n\t" + ex.toString() + "\n\t" + cause);
			}
		} else {
			log.warnf(message, args);
		}
		// if (traceWarn)
		// new Exception().printStackTrace();
	}

	/**
	 * 
	 * @param fmt
	 * @param operationArgs
	 *            可变参数，最后一个参数可以是Throwable
	 */
	public static void error(String message, Object... args) {
		if (args.length == 0) {
			log.error(message);
		} else if (args[args.length - 1] instanceof Throwable) {
			if (traceError) {
				if (args.length == 1) {
					log.error(message, (Throwable) args[0]);
				} else {
					log.error(String.format(message, args), (Throwable) args[args.length - 1]);
				}
			} else {
				Throwable ex;
				if (args.length == 1) {
					ex = (Throwable) args[0];
				} else {
					ex = (Throwable) args[args.length - 1];
				}
				Throwable cause = ex.getCause();
				log.error(message + "\n\t" + ex.toString() + "\n\t" + cause);
			}
		} else {
			log.errorf(message, args);
		}
		// if (traceError)
		// new Exception().printStackTrace();
	}

}
