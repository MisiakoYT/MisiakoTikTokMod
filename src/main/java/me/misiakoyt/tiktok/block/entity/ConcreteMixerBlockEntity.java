package me.misiakoyt.tiktok.block.entity;

import com.google.common.collect.Maps;
import me.misiakoyt.tiktok.item.ModItems;
import me.misiakoyt.tiktok.screen.ConcreteMixerScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ConcreteMixerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory, SidedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;



    private static Map<Item, Item> recipe() {
        Map<Item, Item> map = Maps.<Item, Item>newLinkedHashMap();
        map.put(Items.BLACK_CONCRETE_POWDER, Items.BLACK_CONCRETE);
        map.put(Items.WHITE_CONCRETE_POWDER, Items.WHITE_CONCRETE);
        map.put(Items.LIGHT_GRAY_CONCRETE_POWDER, Items.LIGHT_GRAY_CONCRETE);
        map.put(Items.GRAY_CONCRETE_POWDER, Items.GRAY_CONCRETE);
        map.put(Items.BROWN_CONCRETE_POWDER, Items.BROWN_CONCRETE);
        map.put(Items.RED_CONCRETE_POWDER, Items.RED_CONCRETE);
        map.put(Items.ORANGE_CONCRETE_POWDER, Items.ORANGE_CONCRETE);
        map.put(Items.YELLOW_CONCRETE_POWDER, Items.YELLOW_CONCRETE);
        map.put(Items.LIME_CONCRETE_POWDER, Items.LIME_CONCRETE);
        map.put(Items.GREEN_CONCRETE_POWDER, Items.GREEN_CONCRETE);
        map.put(Items.CYAN_CONCRETE_POWDER, Items.CYAN_CONCRETE);
        map.put(Items.LIGHT_BLUE_CONCRETE_POWDER, Items.LIGHT_BLUE_CONCRETE);
        map.put(Items.BLUE_CONCRETE_POWDER, Items.BLUE_CONCRETE);
        map.put(Items.PURPLE_CONCRETE_POWDER, Items.PURPLE_CONCRETE);
        map.put(Items.MAGENTA_CONCRETE_POWDER, Items.MAGENTA_CONCRETE);
        map.put(Items.PINK_CONCRETE_POWDER, Items.PINK_CONCRETE);
        return map;
    }


    public ConcreteMixerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CONCRETE_MIXER_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ConcreteMixerBlockEntity.this.progress;
                    case 1 -> ConcreteMixerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ConcreteMixerBlockEntity.this.progress = value;
                    case 1 -> ConcreteMixerBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }


    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("concrete_mixer.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("concrete_mixer.progress");
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {

        buf.writeBlockPos(this.pos);

    }

    @Override
    public Text getDisplayName() {

        return Text.literal("Concrete Mixer");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ConcreteMixerScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient) {
            return;
        }

        if (isOutputSlotEmpyOrReceivable()) {
            if(this.hasRecipe()) {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if(hasCraftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
        }else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        ItemStack result = new ItemStack(recipe().get(getStack(INPUT_SLOT).getItem()));
        this.removeStack(INPUT_SLOT, 1);

        this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));

    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Item inputItem = getStack(INPUT_SLOT).getItem();
        boolean hasInput = recipe().containsKey(inputItem);
        ItemStack result = hasInput ? new ItemStack(recipe().get(inputItem)) : ItemStack.EMPTY;

        return hasInput && canInsertAmountIntOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmpyOrReceivable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }



}
