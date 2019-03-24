package com.ocoolcraft.plugins.config;

import org.bukkit.Sound;

public class BundleSound {
    private String name;
    private float volume, pitch;

    public static BundleSound getDefaultSound() {
        BundleSound bundleSound = new BundleSound();
        bundleSound.setName(Sound.LEVEL_UP.name());
        bundleSound.setPitch(1.0f);
        bundleSound.setVolume(1.0f);
        return bundleSound;
    }

    public Sound getSound() {
        return Sound.valueOf(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }
}
