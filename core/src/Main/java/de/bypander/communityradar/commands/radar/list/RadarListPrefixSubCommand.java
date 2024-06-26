package de.bypander.communityradar.commands.radar.list;

import de.bypander.communityradar.ListManager.ListItem;
import de.bypander.communityradar.ListManager.ListManger;
import net.labymod.api.Laby;
import net.labymod.api.client.chat.command.SubCommand;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.network.server.ServerData;
import net.labymod.api.util.I18n;

public class RadarListPrefixSubCommand extends SubCommand {

  protected RadarListPrefixSubCommand() {
    super("prefix");
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    ServerData data = Laby.references().serverController().getCurrentServerData();
    if (data == null)
      return false;
    if (!data.address().getHost().toLowerCase().contains("griefergames"))
      return false;

    StringBuilder sb = new StringBuilder("§8[§cCommunityRadar§8]§r ");
    if (arguments.length < 2) {
      sb.append(I18n.translate("communityradar.command.missingargument"));
      this.displayMessage(Component.text(sb.toString()));
      return true;
    }

    ListItem list = ListManger.get().getListItem(arguments[0]);
    if (list != null) {
      boolean b = list.setPrefix(arguments[1]);
      if (b) {
        sb.append(I18n.translate("communityradar.command.list.prefix.success").replace("{prefix}", arguments[1]));
        this.displayMessage(Component.text(sb.toString()));
        return true;
      }
    }

    sb.append(I18n.translate("communityradar.command.list.prefix.failed"));
    this.displayMessage(Component.text(sb.toString()));
    return true;
  }
}
