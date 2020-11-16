/*
 * Copyright (c) 2020.
 * Author: Bernie G. (Gecko)
 */

package software.bernie.example;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.example.block.tile.BotariumTileEntity;
import software.bernie.example.block.tile.FertilizerTileEntity;
import software.bernie.example.client.renderer.armor.PotatoArmorRenderer;
import software.bernie.example.client.renderer.entity.BikeGeoRenderer;
import software.bernie.example.client.renderer.entity.ExampleGeoRenderer;
import software.bernie.example.client.renderer.tile.BotariumTileRenderer;
import software.bernie.example.client.renderer.tile.FertilizerTileRenderer;
import software.bernie.example.entity.BikeEntity;
import software.bernie.example.entity.GeoExampleEntity;
import software.bernie.example.item.PotatoArmorItem;
import software.bernie.example.registry.ItemRegistry;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod(modid = GeckoLib.ModID)
public class GeckoLibMod
{
	private static CreativeTabs geckolibItemGroup;

	public static CreativeTabs getGeckolibItemGroup()
	{
		if (geckolibItemGroup == null)
		{
			geckolibItemGroup = new CreativeTabs(0, "geckolib_examples")
			{
				@Override
				public ItemStack getTabIconItem()
				{
					return new ItemStack(ItemRegistry.JACK_IN_THE_BOX);
				}
			};
		}

		return geckolibItemGroup;
	}

	public GeckoLibMod()
	{
		GeckoLib.initialize();

		MinecraftForge.EVENT_BUS.register(new CommonListener());
	}

	@SideOnly(Side.CLIENT)
	@Mod.EventHandler
	public void registerRenderers(FMLInitializationEvent event)
	{
		RenderingRegistry.registerEntityRenderingHandler(GeoExampleEntity.class, (manager) -> new ExampleGeoRenderer(manager));
		RenderingRegistry.registerEntityRenderingHandler(BikeEntity.class, (manager) -> new BikeGeoRenderer(manager));

		GeoArmorRenderer.registerArmorRenderer(PotatoArmorItem.class, new PotatoArmorRenderer());

		ClientRegistry.bindTileEntitySpecialRenderer(BotariumTileEntity.class, new BotariumTileRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(FertilizerTileEntity.class, new FertilizerTileRenderer());

		/* RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		ReplacedCreeperRenderer creeperRenderer = new ReplacedCreeperRenderer(renderManager);
		renderManager.entityRenderMap.put(EntityCreeper.class, creeperRenderer);
		GeoReplacedEntityRenderer.registerReplacedEntity(ReplacedCreeperEntity.class, creeperRenderer); */
	}
}
