package turou.fantasy_metropolis.fabric;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import turou.fantasy_metropolis.fabric.item.ItemSwordWhiter;

public class NetworkHandler {
    public static final ResourceLocation SCROLL_SWORD_PACKET = new ResourceLocation(FantasyMetropolis.MODID, "scroll_sword");

    public static void registerPackets() {
        ServerPlayNetworking.registerGlobalReceiver(SCROLL_SWORD_PACKET, (server, player, handler, buf, responseSender) -> {
            int scroll = buf.readInt();
            if (player != null) {
                ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
                if (stack.getItem() instanceof ItemSwordWhiter) {
                    CompoundTag tag = stack.getOrCreateTag();
                    int value = tag.getInt("range") + scroll;
                    tag.putInt("range", Math.max(value, 0));
                }
            }
        });
    }
}
