package me.boboballoon.enhancedenchantments.enchantment;

/**
 * Class that represents an enchantment on an itemstack
 */
public class ActiveEnchantment {
    private final Enchantment enchantment;
    private int level;

    public ActiveEnchantment(Enchantment enchantment, int level) {
        this.enchantment = enchantment;
        this.setLevel(level);
    }

    @Deprecated
    public ActiveEnchantment(Enchantment enchantment, EnchantmentHolder holder, int level) {
        this.enchantment = enchantment;
        this.setLevel(level);
    }

    /**
     * Returns the enchantment
     *
     * @return enchantment
     */
    public Enchantment getEnchantment() {
        return this.enchantment;
    }

    /**
     * Returns null
     *
     * @return null
     */
    @Deprecated
    public EnchantmentHolder getHolder() {
        return null;
    }

    /**
     * Returns the current level of the enchantment
     *
     * @return current level of the enchantment
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Set the current level of the enchantment
     *
     * @param level the level the enchantment should be set to
     */
    public void setLevel(int level) {
        this.level = Math.min(level, this.enchantment.getMaxLevel());
    }

    /**
     * Returns an enchantment book with this enchantment
     *
     * @return an enchantment book with this enchantment
     */
    public EnchantedBook getBook() {
        return new EnchantedBook(this.enchantment, this.level);
    }
}
