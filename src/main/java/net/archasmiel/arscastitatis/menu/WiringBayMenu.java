package net.archasmiel.arscastitatis.menu;

import net.archasmiel.arscastitatis.block.ModBlocks;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;

/** Wiring bay menu. */
public class WiringBayMenu extends AbstractContainerMenu {

  /** Container access. Takes matter only fo server. */
  private final ContainerLevelAccess access;

  /**
   * Menu constructor (client-side).
   * The container access is set to NULL, not needed on the client side.
   *
   * @param containerId menu ID
   * @param player player inventory
   */
  public WiringBayMenu(int containerId, Inventory player) {
    this(containerId, player, ContainerLevelAccess.NULL);
  }

  /**
   * Menu constructor (server-side).
   *
   * @param containerId menu ID
   * @param player player inventory
   * @param access container access
   */
  public WiringBayMenu(int containerId, Inventory player, ContainerLevelAccess access) {
    super(ModMenus.WIRING_BAY.get(), containerId);
    this.access = access;
  }

  @Override
  public ItemStack quickMoveStack(Player player, int i) {
    return this.slots.get(i).getItem();
  }

  @Override
  public boolean stillValid(Player player) {
    return AbstractContainerMenu.stillValid(this.access, player, ModBlocks.WIRING_BAY.get());
  }
}
