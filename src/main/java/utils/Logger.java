package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {
	
	/** Prefix of info logs */
	private final String prefixInfo = "[i] ";
	/** Prefix of debug logs */
	private final String prefixDebug = "[D] ";
	/** Prefix of warning logs */
	private final String prefixWarning = "[W] ";
	/** Prefix of error logs */
	private final String prefixError = "[E] ";
	
	/** Current context of log */
	private String sContext;
	private String horaActual;
	/**
	 * Set the current context
	 */
	public Logger(String context){	
		this.sContext = context;
		this.horaActual = null;
		this.sContext = "[" + sContext + "]: ";
	}
	
	/**
	 * Logs para los mensajes de información
	 * @param mensaje para el log
	 */
	public void info(int level, String message){
		horaActual = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println(prefixInfo + ("["+ horaActual +"] " + sContext) + message);
	}
	
	/**
	 * Logs para los mensajes de debug
	 * @param mensaje para el log
	 */
	public void debug(String message){
		horaActual = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println(prefixDebug + ("["+ horaActual +"] " + sContext) + message);
	}
	
	/**
	 * Logs para los mensajes de alerta
	  * @param mensaje para el log
	 */
	public void warning(String message){
		horaActual = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println(prefixWarning + ("["+ horaActual +"] " + sContext) + message);
	}
	
	/**
	 * Logs para los mensajes de error
	  * @param mensaje para el log
	 */
	public void error(String message){
		horaActual = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println(prefixError + ("["+ horaActual +"] " + sContext) + message);
	}
}
