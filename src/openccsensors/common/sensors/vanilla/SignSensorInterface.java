package openccsensors.common.sensors.vanilla;

import java.util.Map;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.src.Block;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntitySign;
import net.minecraft.src.World;
import openccsensors.OpenCCSensors;
import openccsensors.common.api.ISensorAccess;
import openccsensors.common.api.ISensorInterface;
import openccsensors.common.api.ISensorTarget;
import openccsensors.common.api.ITargetWrapper;
import openccsensors.common.helper.TargetHelper;
import openccsensors.common.sensors.SensorCard;
import openccsensors.common.sensors.TargetRetriever;

public class SignSensorInterface implements ISensorInterface {

	private TargetRetriever retriever = new TargetRetriever();

	public SignSensorInterface() {
		retriever.registerTarget(new ITargetWrapper() {
			@Override
			public ISensorTarget createNew(Object entity, int sx, int sy, int sz) {
				if (entity instanceof TileEntitySign)
				{
					return new SignPostTarget((TileEntity) entity);
				}
				return null;
			}
		});

	}

	@Override
	public String getName() {
		return "openccsensors.item.signsensor";
	}

	@Override
	public String[] getMethods() {
		return null;
	}

	@Override
	public Object[] callMethod(ISensorAccess sensor, int methodID, Object[] args) throws Exception {
		return null;
	}

	@Override
	public Map getBasicTarget(ISensorAccess sensor, World world, int x, int y, int z)
			throws Exception {

		return TargetHelper.getBasicInformationForTargets(
				retriever.getAdjacentTiles(world, x, y, z, 5), world);

	}

	@Override
	public Map getTargetDetails(ISensorAccess sensor, World world, int x, int y, int z, String target)
			throws Exception {

		return TargetHelper.getDetailedInformationForTarget(target,
				retriever.getAdjacentTiles(world, x, y, z, 5), world);

	}

	@Override
	public int getId() {
		return 23;
	}

	@Override
	public void initRecipes(SensorCard card) {
		GameRegistry.addRecipe(
				new ItemStack(card, 1, this.getId()),
				"rsr",
				"rrr",
				"aaa",
				'r', new ItemStack(Item.redstone),
				'a', new ItemStack(Item.paper),
				's',new ItemStack(Block.signPost));
	}

	@Override
	public boolean isDirectionalEnabled() {
		return false;
	}

}