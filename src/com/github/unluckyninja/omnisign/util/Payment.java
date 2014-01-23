package com.github.unluckyninja.omnisign.util;

import com.github.unluckyninja.omnisign.OmniSignMain;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 14-1-20.
 */
public class Payment {
    private BigDecimal price;
    private int exp;
    private HashMap<Material, ItemStack> items;
    private int times;

    public Payment(BigDecimal price, int exp, HashMap<Material, ItemStack> items, int times) {
        this.price = price;
        this.exp = exp;
        this.items = items;
        this.times = times;
    }

    static Pattern pricePattern = Pattern.compile("^[^-\\d\\.]\\d+?(\\.?\\d\\d?)?$");

    public static Payment parse(String string) throws NumberFormatException {
        BigDecimal price = null;
        int exp = 0;
        HashMap<Material, ItemStack> items = null;
        int times = 0;

        string = string.toLowerCase();
        string = string.replaceAll("[\\s]+", " ");
        string = string.replaceAll("^[\\s]", "");
        string = string.replaceAll("[\\s]$", "");
        if (pricePattern.matcher(string).matches()) {
            String sub = string.substring(1);
            price = parseMoney(sub);
        } else {
            String[] strings = string.split("[ :]+", 2);
            if (strings.length != 2) {
                throw new NumberFormatException("Invalid cost format: " + string);
            }
            if (strings[1].matches("e?xp")) {
                exp = parseInt(strings[0]);
            } else if (strings[1].matches("times?")) {
                times = parseInt(strings[0]);
            } else {
                items = parseItemStack(string);
            }
        }
        return new Payment(price, exp, items, times);
    }

    /**
     * This ArrayList is just prepare for upcoming appending items.
     * Cannot parse multiple item stacks.
     *
     * @param string
     * @return an ArrayList that contains only one ItemStack
     */
    public static HashMap<Material, ItemStack>  parseItemStack(String string) {
        String[] items = string.split(" ", 2);
        int quantity = parseInt(items[0]);
        if (!items[1].matches("^\\d+[:;,.'+]\\d+$")) {
            throw new NumberFormatException("Invalid material format: " + items[1]);
        }
        String[] material = items[1].split("[:;,.'+]");
        int id = parseInt(material[0]);
        short meta = Short.parseShort(material[1]);
        ItemStack item = new ItemStack(id, quantity, meta);
        HashMap<Material, ItemStack> result = new HashMap<Material, ItemStack>();
        result.put(item.getType(), item);
        return result;
    }

    public static int parseInt(String string) {
        if (!string.matches("\\d*+")) {
            throw new NumberFormatException("Invalid number: " + string);
        }
        return Integer.parseInt(string);
    }

    public static BigDecimal parseMoney(String string) {
        return new BigDecimal(string);
    }

    public boolean canAffordBy(Player player) {
        if (price != BigDecimal.ZERO) {
            if (!OmniSignMain.economy.has(player.getName(), price.doubleValue())) {
                return false;
            }
        }
        if (exp != 0) {
            if (player.getTotalExperience() < exp) {
                return false;
            }
        }
        if (items.size() != 0) {
            for (ItemStack item : items.values()) {
                if (!player.getInventory().contains(item.getType(), item.getAmount())) {
                    return false;
                }
            }
        }
        if (times == 0) {
            return false;
        }
        return true;
    }

    /**
     * You should call this to make user pay the bill with the sign.
     *
     * @return true if the player can afford this bill along with paying it.
     */
    public boolean charge(Player player) {
        if (!canAffordBy(player)) {
            return false;
        }
        if (price != BigDecimal.ZERO) {
            OmniSignMain.economy.withdrawPlayer(player.getName(), price.doubleValue());
        }
        if (exp != 0) {
            player.setTotalExperience(player.getTotalExperience() - exp);
        }
        if (items.size() != 0) {
            player.getInventory().removeItem((ItemStack[])items.values().toArray());
        }
        if (times > 0) {
            times--;
        }
        return true;
    }

    public boolean canGiveTo(Player player) {
        if (times == 0) {
            return false;
        }

        if (items.size() != 0) {
            int slotNeed = 0;
            HashMap<Material, Integer> remainder = new HashMap<Material, Integer>();
            for (ItemStack i:items.values()){
                remainder.put(i.getType(), i.getAmount());
            }

            for(ItemStack i :items.values()){
                int differ = remainder.get(i.getType()) - i.getMaxStackSize();
                while(differ>0){
                    slotNeed++;
                    differ -= i.getMaxStackSize();
                }
            }

            for (ItemStack i : player.getInventory()) {
                if(slotNeed <= 0){
                    break;
                }
                if (i == null) {
                    slotNeed --;
                }
            }
            if(slotNeed > 0){
                return false;
            }
        }

        return true;
    }

    public boolean pay(Player player) {
        if (!canGiveTo(player)) {
            return false;
        }
        if (price != BigDecimal.ZERO) {
            OmniSignMain.economy.depositPlayer(player.getName(), price.doubleValue());
        }
        if (exp != 0) {
            player.setTotalExperience(player.getTotalExperience() + exp);
        }
        if (items.size() != 0) {
            player.getInventory().addItem((ItemStack[]) items.values().toArray());
        }
        if (times > 0) {
            times--;
        }
        return true;
    }
}
