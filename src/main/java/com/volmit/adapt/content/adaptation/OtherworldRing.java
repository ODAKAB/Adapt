package com.volmit.adapt.content.adaptation;

import com.volmit.adapt.api.adaptation.SimpleAdaptation;
import com.volmit.adapt.util.C;
import com.volmit.adapt.util.Element;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;
import java.util.Random;


public class OtherworldRing extends SimpleAdaptation {
    public OtherworldRing() {
        super("other-ring");
        setDescription("THIS DOES NOTHING; AND IS JUST A PROOF OF CONCEPT FOR LATER USE");
        setIcon(Material.OBSIDIAN);
        setBaseCost(10);
        setCostFactor(0.5);
        setMaxLevel(5);
        setInitialCost(5);
        setInterval(25);
    }

    private double getPhasePercent(int level) {
        return 0.10 + (0.05 * level);
    }

    private int getPoints(int level) {
        return 5 + (10 * level);
    }

    private int getRange(int level) {
        return 2 + level;
    }

    @Override
    public void addStats(int level, Element v) {
        v.addLore(C.GREEN + "+ ");
        v.addLore(C.YELLOW + "~ ");
        v.addLore(C.RED + "- ");
    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {

        if (getLevel(e.getPlayer()) > 0) {
            Player p = e.getPlayer();
            int points = getPoints(getLevel(p)); //amount of points to be generated
            for (int i = 0; i < 360; i += 360 / points) {
                double angle = (i * Math.PI / 180);
                double x = getRange(getLevel(p)) * Math.cos(angle);
                double z = getRange(getLevel(p)) * Math.sin(angle);
                Location loc = p.getLocation().add(x, 1, z);
                Objects.requireNonNull(p.getLocation().getWorld()).spawnParticle(Particle.ASH, loc, 1);

            }

        }

    }


    @Override
    public void onTick() {


    }
}