package me.boboballoon.enhancedenchantments.enchantment;

import me.boboballoon.enhancedenchantments.EnhancedEnchantments;
import me.boboballoon.enhancedenchantments.utils.DummyEnchantment;
import me.boboballoon.enhancedenchantments.utils.TextUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class that represents an enchantment holder on an itemstack
 */
public class EnchantmentHolder {
    private final List<ActiveEnchantment> enchantments;

    public static final NamespacedKey KEY = new NamespacedKey(EnhancedEnchantments.getInstance(), "custom_enchantments");
    public static final Comparator<ActiveEnchantment> COMPARATOR = Comparator.comparingInt(x -> x.getEnchantment().getTier().getTier());

    public EnchantmentHolder() {
        this.enchantments = new ArrayList<>();
    }

    /**
     * Returns a copy of all enchantments inside the holder
     *
     * @return a copy of all enchantments inside the holder
     */
    public List<ActiveEnchantment> getEnchantments() {
        return new ArrayList<>(this.enchantments);
    }

    /**
     * Returns a boolean that is true if there are no elements present in the list
     *
     * @return true if there are no elements present in the list
     */
    public boolean isEmpty() {
        return this.enchantments.isEmpty();
    }

    /**
     * Returns an int that is the amount of elements present in the list
     *
     * @return an int that is the amount of elements present in the list
     */
    public int size() {
        return this.enchantments.size();
    }

    /**
     * Returns the specified enchantment inside the holder
     *
     * @param enchantment the enchantment you wish to get
     * @return the specified enchantment inside the holder, null if not present
     */
    public ActiveEnchantment getEnchantment(Enchantment enchantment) {
        for (ActiveEnchantment activeEnchantment : this.enchantments) {
            if (activeEnchantment.getEnchantment().getName().equals(enchantment.getName())) {
                return activeEnchantment;
            }
        }
        return null;
    }

    /**
     * Returns a boolean that states whether this holder already contains a type of enchantment
     *
     * @param enchantment the enchantment you are checking for
     * @return a boolean that states whether this holder already contains a type of enchantment
     */
    public boolean hasEnchantment(Enchantment enchantment) {
        for (ActiveEnchantment activeEnchantment : this.enchantments) {
            if (activeEnchantment.getEnchantment().getName().equals(enchantment.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes an enchantment from the holder
     *
     * @param enchantment the enchantment you are attempting to remove
     * @param item the item that this holder is to be applied to
     * @return a boolean that states whether this holder contains the enchantment you are trying to remove
     */
    public boolean removeEnchantment(Enchantment enchantment, ItemStack item) {
        for (ActiveEnchantment activeEnchantment : this.enchantments) {
            if (activeEnchantment.getEnchantment().getName().equalsIgnoreCase(enchantment.getName())) {
                this.enchantments.remove(activeEnchantment);
                this.updateItem(item);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an enchantment from the holder
     *
     * @param enchantment the enchantment you are attempting to add
     * @param item the item that this holder is to be applied to
     * @return a boolean that states whether this type of enchantment can be added to said item
     */
    public boolean addEnchantment(ActiveEnchantment enchantment, ItemStack item) {
        if (!enchantment.getEnchantment().canEnchant(item)) {
            return false;
        }

        if (this.hasEnchantment(enchantment.getEnchantment())) {
            this.getEnchantment(enchantment.getEnchantment()).setLevel(enchantment.getLevel());
        } else {
            this.enchantments.add(enchantment);
        }

        this.updateItem(item);

        return true;
    }

    /**
     * Adds an enchantment from the holder unsafely (only use if you do not need to update the item or check if an enchantment was already added)
     *
     * @param enchantment the enchantment you are attempting to remove
     */
    public void addEnchantmentUnsafe(ActiveEnchantment enchantment) {
        this.enchantments.add(enchantment);
    }

    /**
     * Updates the lore on the item based on the enchantments
     *
     * @param item the item that this holder is to be applied to
     */
    public void updateItem(ItemStack item) {
        ItemMeta meta = item.getItemMeta();

        this.enchantments.sort(COMPARATOR);

        List<String> lore = new ArrayList<>();

        for (ActiveEnchantment enchantment : this.enchantments) {
            lore.add(TextUtil.format("&r" + enchantment.getEnchantment().getTier().getColor() + enchantment.getEnchantment().getName() + " &r&7&l- " + enchantment.getLevel()));
        }
        meta.setLore(lore);

        meta.addEnchant(DummyEnchantment.DUMMY_ENCHANTMENT, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.getPersistentDataContainer().set(KEY, PersistentDataType.STRING, this.toString());

        item.setItemMeta(meta);
    }

    /**
     * Converts this object into a serialized string
     *
     * @return serialized string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (ActiveEnchantment enchantment : this.enchantments) {
            builder.append(enchantment.getEnchantment().getName()).append("-").append(enchantment.getLevel()).append(":");
        }
        if (builder.length() != 0) {
            builder.deleteCharAt(builder.lastIndexOf(":"));
        }
        return builder.toString();
    }

    /**
     * Converts a serialized string into an enchantment holder object
     *
     * @param string serialized string
     * @return enchantment holder object, null if invalid
     */
    public static EnchantmentHolder fromString(String string) {
        String[] enchantments = string.split(":");
        EnchantmentHolder holder = new EnchantmentHolder();
        for (String enchantment : enchantments) {
            String[] args = enchantment.split("-");
            Enchantment base = EnhancedEnchantments.getInstance().getEnchantmentManager().getEnchantment(args[0]);
            if (base == null) {
                continue;
            }

            int level;
            try {
                level = Integer.parseInt(args[1].trim());
            } catch (NumberFormatException e) {
                continue;
            }

            holder.addEnchantmentUnsafe(new ActiveEnchantment(base, level));
        }
        return holder;
    }
}
