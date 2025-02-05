package com.psilocke.curiouslanterns.client;

import com.psilocke.curiouslanterns.CuriousLanterns;
import com.psilocke.curiouslanterns.curios.LanternRenderer;
import com.psilocke.curiouslanterns.curios.LargeLanternRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@Mod.EventBusSubscriber(modid = CuriousLanterns.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {
	
	@SubscribeEvent
	public static void onClientSetupEvent(FMLClientSetupEvent e) {
		
		//normal lantern mods
		
		for(String var : CuriousLanterns.lanterns) {
			String namespace = var.substring(0, var.indexOf(':'));
			String item = var.substring(var.indexOf(':')+1);
			
			if(ModList.get().isLoaded(namespace)) {
				CuriosRendererRegistry.register(ForgeRegistries.ITEMS.getValue(new ResourceLocation(namespace, item)), LanternRenderer::new);
			}
		}
		
		//for larger lanterns
		
		for(String var : CuriousLanterns.large_lanterns) {
			String namespace = var.substring(0, var.indexOf(':'));
			String item = var.substring(var.indexOf(':')+1);
			
			if(ModList.get().isLoaded(namespace)) {
				CuriosRendererRegistry.register(ForgeRegistries.ITEMS.getValue(new ResourceLocation(namespace, item)), LargeLanternRenderer::new);
			}
		}
		
		//additional lanterns because of fricking course
		if(ModList.get().isLoaded("additionallanterns")) {
			for(String color : CuriousLanterns.lan_colors) {
				for(String material : CuriousLanterns.lan_materials) {
					String name = color;
					if(name == CuriousLanterns.lan_colors[0]) {
						name += material;
					}else name += ("_" + material);
					
					CuriosRendererRegistry.register(ForgeRegistries.ITEMS.getValue(new ResourceLocation("additionallanterns", name+"_lantern")), LanternRenderer::new);
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onModelregister(ModelEvent.RegisterAdditional event) {
		
		//normal lantern mods
		
		for(String var : CuriousLanterns.lanterns) {
			String namespace = var.substring(0, var.indexOf(':'));
			String item = var.substring(var.indexOf(':')+1);
			
			if(ModList.get().isLoaded(namespace)) {
				event.register(new ResourceLocation(namespace, "block/" + item));
			}
		}
		
		//for larger lanterns
		
		for(String var : CuriousLanterns.large_lanterns) {
			String namespace = var.substring(0, var.indexOf(':'));
			String item = var.substring(var.indexOf(':')+1);
			
			if(ModList.get().isLoaded(namespace)) {
				event.register(new ResourceLocation(namespace, "block/" + item));
			}
		}
		
		//additional lanterns because of fricking course
		if(ModList.get().isLoaded("additionallanterns")) {
			for(String color : CuriousLanterns.lan_colors) {
				for(String material : CuriousLanterns.lan_materials) {
					String name = color;
					if(name == CuriousLanterns.lan_colors[0]) {
						name += material;
					}else name += ("_" + material);
					
					event.register(new ResourceLocation("additionallanterns", "block/" + name+ "_lantern"));
				}
			}
		}
	}
}