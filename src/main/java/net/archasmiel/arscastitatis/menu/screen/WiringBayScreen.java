package net.archasmiel.arscastitatis.menu.screen;

import net.archasmiel.arscastitatis.menu.WiringBayMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

/** Wiring bay screen. */
public class WiringBayScreen extends AbstractContainerScreen<WiringBayMenu> {

  /**
   * Screen constructor.
   *
   * @param menu wiring bay menu {@link WiringBayMenu}
   * @param playerInventory player inventory {@link Inventory}
   * @param title screen title
   */
  public WiringBayScreen(WiringBayMenu menu, Inventory playerInventory, Component title) {
    super(menu, playerInventory, title);
  }

  @Override
  protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {

  }

  @Override
  public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
    super.render(guiGraphics, mouseX, mouseY, partialTick);
  }
}
