package dmillerw.menu.gui.menu;

import com.mojang.blaze3d.vertex.PoseStack;
import dmillerw.menu.gui.ScreenStack;
import dmillerw.menu.gui.menu.list.GuiControlList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nonnull;

public class PickKeyScreen extends Screen {
    private GuiControlList controlList;

    public PickKeyScreen() {
        super(Component.translatable("mine_menu.keyScreen.title"));
    }

    @Override
    public void init() {
        this.controlList = new GuiControlList(this, this.getMinecraft());
        this.addWidget(this.controlList);
        this.setFocused(this.controlList);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
        if (p_keyPressed_1_ == GLFW.GLFW_KEY_ESCAPE) {
            ScreenStack.pop();
            return true;
        }
        return super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int state) {
        if (state == 0 && this.controlList.mouseReleased(mouseX, mouseY, state)) {
            this.setDragging(false);
            return true;
        } else {
            return super.mouseReleased(mouseX, mouseY, state);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0 && this.controlList.mouseClicked(mouseX, mouseY, mouseButton)) {
            this.setDragging(true);
            this.setFocused(this.controlList);
            return true;
        } else {
            return this.controlList != null && this.controlList.mouseClicked((int) mouseX, (int) mouseY, mouseButton);
        }
    }

    @Override
    public void render(@Nonnull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.controlList.render(matrixStack, mouseX, mouseY, partialTicks);
        this.drawCenteredString(matrixStack, this.font, "Select a Key:", this.width / 2, 8, 16777215);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}