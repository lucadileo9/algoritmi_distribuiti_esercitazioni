/**
 * GreetLocalTimeClient.java
 * ====================================
 * 
 * Client RMI che:
 * 1. Si connette al Registry RMI   
 * 2. Ottiene il riferimento all'oggetto remoto
 * 3. Invoca i metodi remoti greetUser() e getLocalTime() sull'oggetto remoto
 * 4. Stampa i risultati ricevuti
 * 
 * Data: Dicembre 2025
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GreetLocalTimeClient {
    
    public static final int PORT = 1099;
    public static final String HOST = "localhost";
    public static final String SERVICE_NAME = "GreetLocalTimeService";

    public static void main(String[] args) {
        // Intestazione
        System.out.println("=".repeat(50));
        System.out.println("CLIENT RMI - Greet and Local Time");
        System.out.println("=".repeat(50));

        try {
            // 1. Si connette al Registry RMI sulla porta 1099 (default)
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);
            System.out.println("✓ Connesso al Registry RMI sulla porta " + PORT);
            // 2. Ottiene il riferimento all'oggetto remoto
            GreetLocalTimeInterface stub = (GreetLocalTimeInterface) registry.lookup(SERVICE_NAME);
            System.out.println("✓ Riferimento a '" + SERVICE_NAME + "' ottenuto");
            System.out.println("─".repeat(50));

            // 3. Invoca i metodi remoti greetUser() e getLocalTime() sull'oggetto remoto
            String greeting = stub.greetUser("Alice");
            String localTime = stub.getLocalTime();

            // 4. Stampa i risultati ricevuti
            System.out.println("📥 Risposta ricevuta dal server!");
            System.out.println("Greeting: " + greeting);
            System.out.println("Local Time: " + localTime);
        } catch (java.rmi.NotBoundException e) {
            System.err.println("❌ Servizio 'StringReverseService' non trovato nel Registry");
            System.err.println("   Assicurati che il server RMI sia in esecuzione");
        } catch (java.rmi.ConnectException e) {
            System.err.println("❌ Impossibile connettersi al Registry RMI");
            System.err.println("   Assicurati che il server RMI sia in esecuzione su localhost:1099");
        } catch (Exception e) {
            System.err.println("❌ Errore nel client RMI: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("✓ Client RMI terminato");
        }
    }
}