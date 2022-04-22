package basics;
import org.junit.jupiter.api.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

import java.util.List;

public class Loggings {
    @Test void logSomething(){
        final Logger LOGGER = LogManager.getLogger(Loggings.class);
        Configurator.setLevel(Loggings.class.getName(), Level.DEBUG);
        LOGGER.debug("topic successfully created: {} ", 1);
    }


}
