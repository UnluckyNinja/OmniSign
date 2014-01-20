package com.github.unluckyninja.omnisign.util;

import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 14-1-20.
 */
public class Cost {
    private BigDecimal price;
    private int exp;
    private ArrayList<ItemStack> items;
    private int times;

    public Cost(BigDecimal price, int exp, ArrayList<ItemStack> items, int times) {
        this.price = price;
        this.exp = exp;
        this.items = items;
        this.times = times;
    }

    static Pattern pricePattern = Pattern.compile("^[^-\\d\\.]\\d+(\\.?\\d+)?$");

    public static Cost parse(String string) throws NumberFormatException{
        BigDecimal price = null;
        int exp = 0;
        ArrayList<ItemStack> items = null;
        int times = 0;

        string = string.toLowerCase();
        string = string.replaceAll("[\\s]+", " ");
        string = string.replaceAll("^[\\s]", "");
        string = string.replaceAll("[\\s]$", "");
        if (pricePattern.matcher(string).matches()){
            String sub = string.substring(1);
            price = parseMoney(sub);
        }else{
            String[] strings = string.split("[ :]+", 2);
            if(strings.length != 2){
                throw new NumberFormatException("Invalid cost format: "+string);
            }
            if (strings[1].matches("e?xp")){
                exp = parseInt(strings[0]);
            }else if(strings[1].matches("times?")){
                times = parseInt(strings[0]);
            }else{
                items = parseItemStack(string);
            }
        }
        return new Cost(price, exp, items, times);
    }

    /**
     * This ArrayList is just prepare for upcoming appending items.
     * Cannot parse multiple item stacks.
     *
     * @param string
     * @return an ArrayList that contains only one ItemStack
     */
    public static ArrayList<ItemStack> parseItemStack(String string){
        String[] items = string.split(" +", 2);
        int quantity = parseInt(items[0]);
        if(!items[1].matches("^\\d+[:;,.'+]\\d+$")){
            throw new NumberFormatException("Invalid material format: "+items[1]);
        }
        String[] material = items[1].split("[:;,.'+]");
        int id = parseInt(material[0]);
        short meta = Short.parseShort(material[1]);
        ItemStack item = new ItemStack(id, quantity, meta);
        ArrayList<ItemStack> result = new ArrayList<ItemStack>();
        result.add(item);
        return result;
    }

    public static int parseInt(String string){
        if (!string.matches("\\d*+")){
            throw new NumberFormatException("Invalid number: "+string);
        }
        return Integer.parseInt(string);
    }

    public static BigDecimal parseMoney(String string){
        return new BigDecimal(string);
    }

    /**
     * You should call this every time someone pays the bill.
     *
     * @return true if the rest times equals to 0, or it doesn't cost times(value = -1).
     */
    public boolean check() {
        return (--times) == -1 || (0 == times ? false : true);
    }
}
