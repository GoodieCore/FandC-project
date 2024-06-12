package Filter;

import Channel.DataModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;


public class LoggingErrorFilter implements Filter<DataModel>{
    private static final Logger logger = LogManager.getLogger(LoggingErrorFilter.class);

    @Override
    public DataModel apply(DataModel dataModel) {
        for (String errorMessage : dataModel.getErrorMessages()) {
            logger.error(errorMessage);
        }
        dataModel.setErrorMessages(new ArrayList<>()); // Очищаем список ошибок после логирования
        return dataModel;
    }
}
