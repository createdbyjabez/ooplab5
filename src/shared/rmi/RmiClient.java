package shared.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public final class RmiClient {

    private static final String DEFAULT_HOST = "localhost";

    private static VideoLibraryService service;

    private RmiClient() {
    }

    public static VideoLibraryService getService() {
        if (service == null) {
            service = lookupService();
        }

        return service;
    }

    private static VideoLibraryService lookupService() {
        try {
            String host = System.getProperty("vls.server.host", DEFAULT_HOST);
            int port = Integer.parseInt(System.getProperty(
                    "vls.rmi.port",
                    String.valueOf(VideoLibraryService.DEFAULT_PORT)
            ));

            Registry registry = LocateRegistry.getRegistry(host, port);
            return (VideoLibraryService) registry.lookup(VideoLibraryService.SERVICE_NAME);
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Could not connect to VLS RMI server. Start server.ServerApp first.",
                    e
            );
        }
    }
}