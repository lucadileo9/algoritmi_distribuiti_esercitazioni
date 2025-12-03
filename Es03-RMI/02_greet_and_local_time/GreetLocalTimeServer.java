import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class GreetLocalTimeServer extends UnicastRemoteObject implements GreetLocalTimeInterface {
    private static final long serialVersionUID = 1L;

    // Costruttore
    public GreetLocalTimeServer() throws RemoteException {
        super();
    }

    /**
     * Implementazione del metodo remoto per salutare un utente
     */
    @Override
    public String greetUser(String name) throws RemoteException {
        System.out.println(" Ricevuta richiesta di saluto per: " + name);
        String greeting = "Ciao, " + name + "! Benvenuto al server RMI.";
        return greeting;
    }

    /**
     * Implementazione del metodo remoto per ottenere l'ora locale del server
     */
    @Override
    public String getLocalTime() throws RemoteException {
        System.out.println(" Ricevuta richiesta per l'ora locale");
        String localTime = java.time.LocalTime.now().toString();
        return localTime;
    }

    /**
     * Main del server: crea e registra l'oggetto remoto
     */
    public static void main(String[] args) {
        // Intestazione
        System.out.println("=".repeat(50));
        System.out.println("SERVER RMI - Greet and Local Time");
        System.out.println("=".repeat(50));

        try {
            // 1. Crea l'istanza dell'oggetto remoto
            GreetLocalTimeServer server = new GreetLocalTimeServer();

            // 2. Crea o ottiene il Registry RMI sulla porta 1099 (default)
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(1099);
                System.out.println("✓ Registry RMI creato sulla porta 1099");
            } catch (RemoteException e) {
                registry = LocateRegistry.getRegistry(1099);
                System.out.println("✓ Connesso al Registry RMI esistente sulla porta 1099");
            }

            // 3. Registra l'oggetto remoto nel Registry con un nome
            registry.rebind("GreetLocalTimeService", server);

            System.out.println("✓ Servizio 'GreetLocalTimeService' registrato nel Registry");
            System.out.println("✓ Server RMI pronto per ricevere chiamate remote");

        } catch (Exception e) {
            System.err.println("❌ Errore nel server RMI: " + e.getMessage());
            e.printStackTrace();
            
        }
    }
}
