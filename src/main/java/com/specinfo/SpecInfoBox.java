package com.specinfo;

import net.runelite.api.Client;
import net.runelite.api.VarPlayer;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.ui.overlay.infobox.InfoBoxPriority;

import java.awt.*;
import java.awt.image.BufferedImage;

class SpecInfoBox extends InfoBox
{
    private static final Color ABOVE_THRESHOLD = new Color(255, 255, 255, 220);
    private static final Color BELOW_THRESHOLD = new Color(255, 0, 0, 220);

    private final SpecInfoPlugin plugin;
    private final SpecInfoConfig config;
    private final Client client;


    SpecInfoBox(BufferedImage image, SpecInfoPlugin plugin, Client client, SpecInfoConfig config)
    {
        super(image, plugin);
        this.plugin = plugin;
        this.config = config;
        this.client = client;
        setTooltip("Special Attack");
        setPriority(InfoBoxPriority.HIGH);
    }

    public Integer getSpecialAttack()
    {
        return (int) (client.getVarpValue(VarPlayer.SPECIAL_ATTACK_PERCENT)/10f);
    }

    @Override
    public String getText()
    {
        return String.valueOf(getSpecialAttack());
    }

    @Override
    public Color getTextColor()
    {
        if (config.lowSpecThreshold() > 0 & getSpecialAttack() < config.lowSpecThreshold())
        {
            return BELOW_THRESHOLD;
        }
        else
        {
            return ABOVE_THRESHOLD;
        }
    }

    @Override
    public boolean render()
    {
        return config.displayInfobox();
    }

}