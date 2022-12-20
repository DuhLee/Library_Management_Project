package lmp.admin.menu.readingroom.usagelist.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import lmp.admin.menu.readingroom.ReadingRoomPanel;
import lmp.admin.menu.readingroom.usagelist.label.UsageListCheckOutButton;
import lmp.admin.menu.readingroom.usagelist.label.UsageListTitleLabel;

public class UsageListTitlePanel extends JPanel{
	
	BorderLayout borderLayout = new BorderLayout();
	UsageListTitleLabel usageListTitleLabel;
	UsageListCheckOutButton UsageListCheckOutButton;
	
	
	public UsageListTitlePanel(ReadingRoomPanel readingRoomPanel) {
		System.out.println("titlePanel");
		UsageListCheckOutButton = new UsageListCheckOutButton(readingRoomPanel);
		usageListTitleLabel = new UsageListTitleLabel();
		this.setLayout(borderLayout);
		this.add(usageListTitleLabel, "South");
		this.add(UsageListCheckOutButton, "East");
		
	}
}