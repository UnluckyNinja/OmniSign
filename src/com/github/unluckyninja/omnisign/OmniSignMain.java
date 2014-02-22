package com.github.unluckyninja.omnisign;

import com.github.unluckyninja.omnisign.listener.SignsListener;
import com.github.unluckyninja.omnisign.listener.TimeSignListener;
import com.github.unluckyninja.omnisign.listener.WeatherSignListner;
import lib.PatPeter.SQLibrary.Database;
import lib.PatPeter.SQLibrary.SQLite;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPlugin;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OmniSignMain extends JavaPlugin {
    private Database sql;

    private static SignsManager SM;

    public static Permission permission = null;
    public static Economy economy = null;
    public static Chat chat = null;

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    @Override
    public void onEnable() {
        SM = SignsManager.getInstance(this);
//        config = this.getConfig();
//        config.options().copyDefaults(true);

        checkDependency();

        setupChat();
        setupEconomy();
        setupPermissions();

        getServer().getPluginManager().registerEvents(new SignsListener(this), this);
        getServer().getPluginManager().registerEvents(new TimeSignListener(this), this);
        getServer().getPluginManager().registerEvents(new WeatherSignListner(this), this);

        info(getDescription().getName() + " has been enabled.");
    }

    @Override
    public void onDisable() {
        info(getDescription().getName() + " has been disabled.");
    }

    private void setupSQL() {
        sql = new SQLite(Logger.getLogger("Minecraft"),
                "[" + getDescription().getName() + "]",
                this.getDataFolder().getAbsolutePath(),
                getDescription().getName(),
                ".sqlite");
        if (sql.open()) {
            try {
                sql.query("create table NormalSign(id int primary key)");
                info("create table succeeded.");
            } catch (SQLException e) {
                error("create table failed.", e);
            }
        }
    }

    private void checkDependency() {
        if (!this.getServer().getPluginManager().isPluginEnabled("SQLibrary")) {
            try {
                info("Downloadind dependecy: SQLibrary");
                URL url = new URL("http://repo.dakanilabs.com/content/repositories/public/lib/PatPeter/SQLibrary/SQLibrary/maven-metadata.xml");
                URLConnection urlConnection = url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(in);
                final String version = doc.getElementsByTagName("latest").item(0).getTextContent();
                in.close();
                url = new URL("http://repo.dakanilabs.com/content/repositories/public/lib/PatPeter/SQLibrary/SQLibrary/" + version + "/SQLibrary-" + version + ".jar");
                final String path = this.getDataFolder().getParentFile().getAbsoluteFile() + "/SQLibrary-" + version + ".jar";
                ReadableByteChannel rbc = Channels.newChannel(url.openStream());
                FileOutputStream fos = new FileOutputStream(path);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                info("Finished downloading SQLibrary-" + version + ". Loading dependecy");
                this.getServer().getPluginManager().loadPlugin(new File(path));
                info("Loaded SQLibrary");
            } catch (MalformedURLException ex) {
                error("Failed to download dependency", ex);
            } catch (IOException ex) {
                error("Failed to download dependency", ex);
            } catch (ParserConfigurationException ex) {
                error("Failed to download dependency", ex);
            } catch (SAXException ex) {
                error("Failed to download dependency", ex);
            } catch (InvalidPluginException ex) {
                error("Failed to load dependency", ex);
            } catch (InvalidDescriptionException ex) {
                error("Failed to load dependency", ex);
            } catch (UnknownDependencyException ex) {
                error("Failed to load dependency", ex);
            }
        }

        if (!this.getServer().getPluginManager().isPluginEnabled("Vault")) {
            try {
                info("Downloadind dependecy: Vault");
                URL url = new URL("http://nexus.theyeticave.net/content/repositories/pub_releases/net/milkbowl/vault/Vault/maven-metadata.xml");
                URLConnection urlConnection = url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(in);
                final String version = doc.getElementsByTagName("release").item(0).getTextContent();
                in.close();
                url = new URL("http://nexus.theyeticave.net/content/repositories/pub_releases/net/milkbowl/vault/Vault/" + version + "/Vault-" + version + ".jar");
                final String path = this.getDataFolder().getParentFile().getAbsoluteFile() + "/Vault-" + version + ".jar";
                ReadableByteChannel rbc = Channels.newChannel(url.openStream());
                FileOutputStream fos = new FileOutputStream(path);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                info("Finished downloading Vault-" + version + ". Loading dependecy");
                this.getServer().getPluginManager().loadPlugin(new File(path));
                info("Loaded Vault");
            } catch (MalformedURLException ex) {
                error("Failed to download dependency", ex);
            } catch (IOException ex) {
                error("Failed to download dependency", ex);
            } catch (ParserConfigurationException ex) {
                error("Failed to download dependency", ex);
            } catch (SAXException ex) {
                error("Failed to download dependency", ex);
            } catch (InvalidPluginException ex) {
                error("Failed to load dependency", ex);
            } catch (InvalidDescriptionException ex) {
                error("Failed to load dependency", ex);
            } catch (UnknownDependencyException ex) {
                error("Failed to load dependency", ex);
            }
        }
    }

    public void info(String string) {
        getLogger().info(string);
    }

    public void error(String string, Throwable t) {
        getLogger().log(Level.SEVERE, string, t);
    }

    public static SignsManager getSignsManager() {
        return SM;
    }
}
