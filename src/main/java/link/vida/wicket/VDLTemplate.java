/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.wicket;

import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

/**
 *
 * @author dcaro
 */
public class VDLTemplate extends WebPage {

    public static final String CONTENT_ID = "contentComponent";
    private final PeersProvider peersProvider = new PeersProvider();

    private Component headerPanel;
    private Component footerPanel;

    public VDLTemplate() {
        add(headerPanel = new HeaderPanel("headerPanel"));
        add(footerPanel = new FooterPanel("footerPanel"));
        add(new Label(CONTENT_ID, "Put your content here"));        

        //https://cwiki.apache.org/confluence/display/WICKET/Simple+Sortable+DataTable+Example
        List<IColumn> columns = new ArrayList<>();
        columns.add(new PropertyColumn(new Model("ID ZMQ"), "id", "id"));
        DefaultDataTable table = new DefaultDataTable("datatable", columns, peersProvider, 10);
        add(table);
    }

}
