package emframework.common.data;

import java.io.PrintWriter;
import java.io.StringWriter;

import emframework.common.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorDTOHelper {
	private static Logger logger = LoggerFactory.getLogger(ErrorDTOHelper.class);

	public static ErrorDTO toDto(Exception e) {
		if(e==null) return null;
		ErrorDTO r = new ErrorDTO();
		if (e instanceof ApplicationException){
			r.setMessage(e.getMessage());
		}else{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			r.setMessage("[" + e.getClass().getSimpleName()
					+ "]" +e.getMessage() + "\n" +
					"traces:\n" +
					errors.toString());
		}
		logger.error(r.toString());
		return r;
	}

}
