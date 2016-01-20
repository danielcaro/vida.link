
import org.crsh.cli.Argument
import org.crsh.cli.Command;
import org.crsh.cli.Usage;
import org.crsh.command.CRaSHCommand;
import org.crsh.text.ui.UIBuilder

import org.crsh.cli.Option;
import org.crsh.cli.Required


@Usage("Perform action on Connectors")
class conn extends CRaSHCommand {
    
    @Usage("display list of actions")
    @Command
    Object list() {        
        
        def conns = (context.attributes.beans["link.vida.conn.ConnectorManager"].invokeMethod("getConnectors", [] as Object[]));
        
        def table = new UIBuilder().table(columns: [2,2], rightCellPadding: 1) {
            header(bold: true, fg: black, bg: white) {
                label("name"); label("status")
            }            
            conns.each { connId, connObj ->
                row {                     
                    label(connObj.connectorName); label("Initied")
                }
            };            
        }
        return table;
    }
   
}

