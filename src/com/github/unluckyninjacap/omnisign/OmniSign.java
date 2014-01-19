package com.github.unluckyninjacap.omnisign;

import lib.PatPeter.SQLibrary.Database;
import lib.PatPeter.SQLibrary.SQLite;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
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

public class OmniSign extends JavaPlugin {

    private Configuration config;

    private Database sql;

    private SignsManager SM;


    @Override
    public void onEnable() {
//        SM = SignsManager.getInstance(this);
//        config = this.getConfig();
//        config.options().copyDefaults(true);

        checkDependency();
        sql = new SQLite(Logger.getLogger("Minecraft"),
                "[OmniSign] ",
                this.getDataFolder().getAbsolutePath(),
                "OmniSign",
                ".sqlite");
        if(sql.open()){
            try {
                sql.query("create table NormalSign(id int primary key)");
                info("create table succeeded.");
            } catch (SQLException e) {
                error("create table failed.", e);
            }
        }
        getLogger().info("OmniSign has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("OmniSign has been disabled.");
    }

    private void checkDependency() {

        if (!this.getServer().getPluginManager().isPluginEnabled("SQLibrary")) {
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
        }
    }

    public void info(String string) {
        getLogger().info(string);
    }

    public void error(String string, Throwable t) {
        getLogger().log(Level.WARNING, string, t);
    }

    public SignsManager getSignsManager() {
        return SM;
    }
}
