package microservicesgft.teammicroservicesgft;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class H2DatabaseConnectivityTest {
    @Test
    void testH2DatabaseConnectivity() {
        String url = "jdbc:h2:~/test"; 
        String user = "microservices"; 
        String password = "microservices"; 

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            assertTrue(conn != null, "La conexión a la base de datos H2 se ha establecido correctamente.");
        } catch (SQLException e) {
            fail("Error al conectar a la base de datos H2: " + e.getMessage());
        }
    }
}
