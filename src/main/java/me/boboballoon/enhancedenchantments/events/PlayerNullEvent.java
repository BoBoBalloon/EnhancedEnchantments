package me.boboballoon.enhancedenchantments.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * Event that is not called on bukkit api but used internally to pass a player interface when no event is present
 */
public class PlayerNullEvent extends PlayerEvent {

    public PlayerNullEvent(Player who) {
        super(who);
    }

    /**
     * Overridden method because im forced to add it
     *
     * @return null
     */
    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
