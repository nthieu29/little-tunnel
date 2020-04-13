package little.tunnel.site;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Site {
    INSTANCE;
    private final String SITE_CONFIG_FILE = "sites.txt";
    private Set<String> configSites;

    Site() {
        load();
    }

    public Set<String> getConfigSites() {
        return configSites;
    }

    public boolean isBlockedByISP(String site) {
        return configSites.contains(site.toLowerCase());
    }

    private void load() {
        Path path = Paths.get(SITE_CONFIG_FILE);
        try {
            if (Files.exists(path)) {
                List<String> allLines = Files.readAllLines(path);
                configSites = allLines.stream().collect(Collectors.toSet());
            } else {
                configSites = new HashSet<>();
                Files.write(path, configSites);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        Path path = Paths.get(SITE_CONFIG_FILE);
        try {
            Files.write(path, this.configSites);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSite(String site) {
        this.configSites.add(site);
    }
}
